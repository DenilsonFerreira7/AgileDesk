package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.exceptions.MessageNotFoundException;
import com.laudoStratus.demo.mapper.LaudoPreventivaPDFMapper;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.EquipamentoRepository;
import com.laudoStratus.demo.repository.LaudoPreventivaRepository;
import com.laudoStratus.demo.repository.TecnicoRepository;
import com.laudoStratus.demo.validacao.laudoVal.LaudoPreventivaValidator;
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
    private final LaudoPreventivaValidator laudoPreventivaValidator;

    public LaudoPreventiva criarLaudo(LaudoPreventivaPDFDTO laudoRequest) {
        laudoPreventivaValidator.validarSolicitacaoLaudo(laudoRequest);

        Optional<Empresa> empresaOptional = empresaRepository.findById(laudoRequest.getEmpresaId());
        Empresa empresa = empresaOptional.orElseThrow(() -> MessageNotFoundException.EmpresaNaoEncontrada(laudoRequest.getEmpresaId()));

        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(laudoRequest.getTecnicoId());
        Tecnico tecnico = tecnicoOptional.orElseThrow(() -> MessageNotFoundException.TecnicoNaoEncontrado(laudoRequest.getTecnicoId()));

        List<Long> equipamentoIds = laudoRequest.getEquipamentoIds();
        laudoPreventivaValidator.validarEquipamentosExistentes(equipamentoIds);

        List<Equipamento> equipamentos = equipamentoRepository.findAllById(equipamentoIds);

        LaudoPreventiva laudoPreventiva = new LaudoPreventiva();
        laudoPreventiva.setEmpresa(empresa);
        laudoPreventiva.setTecnico(tecnico);
        laudoPreventiva.setEquipamentos(equipamentos);
        laudoPreventiva.setDescricao(laudoRequest.getDescricao());
        laudoPreventiva.setDataCriacao(LocalDate.now());

        return laudoPreventivaRepository.save(laudoPreventiva);
    }

    public LaudoPreventiva buscarLaudoPorId(Long id) {
        return laudoPreventivaRepository.findById(id)
                .orElseThrow(() -> MessageNotFoundException.LaudoIdNull(id));
    }

    public List<LaudoPreventivaPDFDTO> getAllLaudoTecnicoPDFDTO() {
        List<LaudoPreventiva> laudos = laudoPreventivaRepository.findAll();
        return laudos.stream()
                .map(LaudoPreventivaPDFMapper::mapLaudoPreventivaToDTO)
                .collect(Collectors.toList());
    }
}