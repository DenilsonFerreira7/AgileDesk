package com.laudoStratus.demo;

import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.validacao.empresaVal.EmpresaValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EmpresaValidationServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaValidationService empresaValidationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidateCadastroWithNullEmpresa() {
        assertThrows(MessageNotFoundException.class, () -> empresaValidationService.validateCadastro(null));
    }

    @Test
    void testValidateCadastroWithEmptyNome() {
        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa("");
        assertThrows(MessageNotFoundException.class, () -> empresaValidationService.validateCadastro(empresa));
    }

    @Test
    void testValidateCadastroWithExistingNome() {
        String nomeEmpresa = "Empresa Teste";
        when(empresaRepository.findByNomeEmpresa(nomeEmpresa)).thenReturn(new Empresa());
        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa(nomeEmpresa);
        assertThrows(MessageNotFoundException.class, () -> empresaValidationService.validateCadastro(empresa));
    }

    @Test
    void testValidateObterEmpresaPorIdWithNullId() {
        assertThrows(MessageNotFoundException.class, () -> empresaValidationService.validateObterEmpresaPorId(null));
    }

    @Test
    void testValidateObterEmpresaPorIdWithNonExistingId() {
        Long id = 1L;
        when(empresaRepository.existsById(id)).thenReturn(false);
        assertThrows(MessageNotFoundException.class, () -> empresaValidationService.validateObterEmpresaPorId(id));
    }
}
