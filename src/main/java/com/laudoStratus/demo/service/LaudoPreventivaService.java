package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.LaudoTecnicoRequest;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.LaudoPreventivaRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LaudoPreventivaService {

    private final LaudoPreventivaRepository laudoPreventivaRepository;
    private final EmpresaRepository empresaRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final TecnicoRepository tecnicoRepository;

    public LaudoPreventiva criarLaudo(LaudoTecnicoRequest laudoRequest) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(laudoRequest.getEmpresaId());
        if (empresaOptional.isEmpty()) {
            throw new IllegalArgumentException("Empresa não encontrada com o ID: " + laudoRequest.getEmpresaId());
        }
        Empresa empresa = empresaOptional.get();

        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(laudoRequest.getTecnicoId());
        if (tecnicoOptional.isEmpty()) {
            throw new IllegalArgumentException("Técnico não encontrado com o ID: " + laudoRequest.getTecnicoId());
        }
        Tecnico tecnico = tecnicoOptional.get();

        List<Equipamento> equipamentos = equipamentoRepository.findAllById(laudoRequest.getEquipamentoIds());

        LaudoPreventiva laudoPreventiva = new LaudoPreventiva();
        laudoPreventiva.setEmpresa(empresa);
        laudoPreventiva.setTecnico(tecnico);
        laudoPreventiva.setEquipamentos(equipamentos);
        laudoPreventiva.setDescricao(laudoRequest.getDescricao());
        laudoPreventiva.setDataCriacao(LocalDate.now());

        return laudoPreventivaRepository.save(laudoPreventiva);
    }
}
