package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.*;
import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.mapper.EmpresaMapper;
import com.laudoStratus.demo.mapper.EquipamentoMapper;
import com.laudoStratus.demo.mapper.LaudoTecnicoPDFMapper;
import com.laudoStratus.demo.mapper.TecnicoMapper;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.LaudoTecnicoRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import com.laudoStratus.demo.validacao.laudoVal.LaudoTecnicoValidation;
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
    private final LaudoTecnicoValidation laudoTecnicoValidation;

    public LaudoTecnico criarLaudo(LaudoPreventivaPDFDTO laudoRequest) {
        laudoTecnicoValidation.validateLaudoRequest(laudoRequest);

        Optional<Empresa> empresaOptional = empresaRepository.findById(laudoRequest.getEmpresaId());
        Empresa empresa = empresaOptional.orElseThrow(() ->
                new IllegalArgumentException(MessageNotFoundException.EmpresaNaoEncontrada(laudoRequest.getEmpresaId())));

        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(laudoRequest.getTecnicoId());
        Tecnico tecnico = tecnicoOptional.orElseThrow(() ->
                new IllegalArgumentException(MessageNotFoundException.TecnicoNaoEncontrado(laudoRequest.getTecnicoId())));

        List<Equipamento> equipamentos = equipamentoRepository.findAllById(laudoRequest.getEquipamentoIds());

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
                .orElseThrow(() -> new RuntimeException(MessageNotFoundException.LaudoIdNull(id)));
    }


    public LaudoTecnicoResponse obterLaudoTecnico(Long id) {
        laudoTecnicoValidation.validarIdNotNull(id);
        LaudoTecnico laudoTecnico = laudoTecnicoValidation.validarLaudoExistente(id);

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
    }


    public List<LaudoTecnico> obterLaudosPorNomeEmpresa(String nomeEmpresa) {
        return laudoTecnicoRepository.findByEmpresaNomeEmpresaIgnoreCase(nomeEmpresa);
    }

    public List<LaudoTecnicoPDFDTO> getAllLaudoTecnicoPDFDTO() {
        List<LaudoTecnico> laudos = laudoTecnicoRepository.findAll();
        return laudos.stream()
                .map(LaudoTecnicoPDFMapper::mapLaudoTecnicoToLaudoTecnicoPDFDTO)
                .collect(Collectors.toList());
    }

}
