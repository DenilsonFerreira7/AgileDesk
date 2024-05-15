package com.laudoStratus.demo.validacao.laudoVal;

import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.LaudoTecnicoRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LaudoTecnicoValidation {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private LaudoTecnicoRepository laudoTecnicoRepository;

    public void validateLaudoRequest(LaudoPreventivaPDFDTO laudoRequest) {
        validateEmpresa(laudoRequest.getEmpresaId());
        validateTecnico(laudoRequest.getTecnicoId());
    }

    private void validateEmpresa(Long empresaId) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(empresaId);
        if (empresaOptional.isEmpty()) {
            throw new IllegalArgumentException(MessageNotFoundException.EmpresaNaoEncontrada(empresaId));
        }
    }

    private void validateTecnico(Long tecnicoId) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(tecnicoId);
        if (tecnicoOptional.isEmpty()) {
            throw new IllegalArgumentException(MessageNotFoundException.TecnicoNaoEncontrado(tecnicoId));
        }

    }
    public void validarIdNotNull(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do laudo não pode ser nulo.");
        }
    }

    public LaudoTecnico validarLaudoExistente(Long id) {
        Optional<LaudoTecnico> laudoTecnicoOptional = laudoTecnicoRepository.findById(id);
        if (laudoTecnicoOptional.isPresent()) {
            return laudoTecnicoOptional.get();
        } else {
            throw new RuntimeException(MessageNotFoundException.LaudoIdNull(id));
        }
    }

}