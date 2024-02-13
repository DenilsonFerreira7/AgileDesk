package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.*;
import com.laudoStratus.demo.mapper.EmpresaMapper;
import com.laudoStratus.demo.mapper.EquipamentoMapper;
import com.laudoStratus.demo.mapper.TecnicoMapper;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.LaudoTecnicoRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LaudoTecnicoService {

    private final LaudoTecnicoRepository laudoTecnicoRepository;
    private final EmpresaRepository empresaRepository;
    private final TecnicoRepository tecnicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public LaudoTecnico criarLaudo(LaudoTecnicoRequest laudoRequest) {
        Optional<Empresa> empresaOptional = Optional.ofNullable(empresaRepository.findByNomeEmpresa(laudoRequest.getNomeEmpresa()));
        if (empresaOptional.isEmpty()) {
            throw new IllegalArgumentException("Empresa não encontrada: " + laudoRequest.getNomeEmpresa());
        }
        Empresa empresa = empresaOptional.get();

        Optional<Tecnico> tecnicoOptional = Optional.ofNullable(tecnicoRepository.findByNomeTecnico(laudoRequest.getNomeTecnico()));
        if (tecnicoOptional.isEmpty()) {
            throw new IllegalArgumentException("Técnico não encontrado: " + laudoRequest.getNomeTecnico());
        }
        Tecnico tecnico = tecnicoOptional.get();

        List<Equipamento> equipamentos = equipamentoRepository.findAllById(laudoRequest.getIdsEquipamentos());

        LaudoTecnico laudoTecnico = new LaudoTecnico();
        laudoTecnico.setEmpresa(empresa);
        laudoTecnico.setEquipamentos(equipamentos);
        laudoTecnico.setTecnico(tecnico);
        laudoTecnico.setDescricao(laudoRequest.getDescricao());
        laudoTecnico.setDataCriacao(LocalDate.now());

        return laudoTecnicoRepository.save(laudoTecnico);
    }

    public LaudoTecnico buscarLaudoPorId(Long id) {
        return laudoTecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laudo técnico não encontrado com o ID: " + id));
    }

    public LaudoTecnicoResponse obterLaudoTecnico(Long id) {
        Optional<LaudoTecnico> laudoTecnicoOptional = laudoTecnicoRepository.findById(id);
        if (laudoTecnicoOptional.isPresent()) {
            LaudoTecnico laudoTecnico = laudoTecnicoOptional.get();
            EmpresaResponse empresaResponse = EmpresaMapper.mapEmpresaToEmpresaResponse(laudoTecnico.getEmpresa());
            List<EquipamentoResponse> equipamentosResponse = laudoTecnico.getEquipamentos().stream()
                    .map(EquipamentoMapper::mapEquipamentoToEquipamentoResponse)
                    .collect(Collectors.toList());
            TecnicoResponse tecnicoResponse = TecnicoMapper.mapTecnicoToTecnicoResponse(laudoTecnico.getTecnico());
            return new LaudoTecnicoResponse(
                    laudoTecnico.getId(),
                    empresaResponse,
                    equipamentosResponse,
                    tecnicoResponse,
                    laudoTecnico.getDescricao(),
                    laudoTecnico.getDataCriacao()
            );
        } else {
            throw new RuntimeException("Laudo técnico não encontrado");
        }
    }
}