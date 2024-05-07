package com.laudoStratus.demo.validacao.laudoVal;

import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LaudoPreventivaValidator {

    private final EmpresaRepository empresaRepository;
    private final TecnicoRepository tecnicoRepository;
    private final EquipamentoRepository equipamentoRepository;


    public void validarSolicitacaoLaudo(LaudoPreventivaPDFDTO laudoRequest) {
        Objects.requireNonNull(laudoRequest, "O objeto de solicitação do laudo não pode ser nulo.");
        Objects.requireNonNull(laudoRequest.getEmpresaId(), "O ID da empresa não pode ser nulo.");
        Objects.requireNonNull(laudoRequest.getTecnicoId(), "O ID do técnico não pode ser nulo.");
        Objects.requireNonNull(laudoRequest.getEquipamentoIds(), "A lista de IDs de equipamentos não pode ser nula.");
        if (laudoRequest.getEquipamentoIds().isEmpty()) {
            throw new IllegalArgumentException("A lista de IDs de equipamentos não pode estar vazia.");
        }
    }

    public void validarEmpresaExistente(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw MessageNotFoundException.EmpresaNaoEncontrada(empresaId);
        }
    }

    public void validarTecnicoExistente(Long tecnicoId) {
        if (!tecnicoRepository.existsById(tecnicoId)) {
            throw MessageNotFoundException.TecnicoNaoEncontrado(tecnicoId);
        }
    }

    public void validarEquipamentosExistentes(List<Long> equipamentoIds) {
        for (Long equipamentoId : equipamentoIds) {
            if (!equipamentoRepository.existsById(equipamentoId)) {
                throw MessageNotFoundException.EquipamentoNaoEncontrado(equipamentoId);
            }
        }
    }
}
