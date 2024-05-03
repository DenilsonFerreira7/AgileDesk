package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.mapper.LaudoPreventivaPDFMapper;
import com.laudoStratus.demo.mapper.LaudoTecnicoPDFMapper;
import com.laudoStratus.demo.models.*;
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

    public LaudoPreventiva criarLaudo(LaudoPreventivaPDFDTO laudoRequest) {
        // Obter a empresa pelo ID
        Optional<Empresa> empresaOptional = empresaRepository.findById(laudoRequest.getEmpresaId());
        if (empresaOptional.isEmpty()) {
            throw new IllegalArgumentException("Empresa não encontrada com o ID: " + laudoRequest.getEmpresaId());
        }
        Empresa empresa = empresaOptional.get();
        String nomeEmpresa = empresa.getNomeEmpresa(); // Obtenha o nome da empresa

        // Obter o técnico pelo ID
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(laudoRequest.getTecnicoId());
        if (tecnicoOptional.isEmpty()) {
            throw new IllegalArgumentException("Técnico não encontrado com o ID: " + laudoRequest.getTecnicoId());
        }
        Tecnico tecnico = tecnicoOptional.get();
        String nomeTecnico = tecnico.getNomeTecnico(); // Obtenha o nome do técnico

        // Obter a lista de equipamentos pelos IDs
        List<Equipamento> equipamentos = equipamentoRepository.findAllById(laudoRequest.getEquipamentoIds());

        // Criar o objeto LaudoPreventiva
        LaudoPreventiva laudoPreventiva = new LaudoPreventiva();
        laudoPreventiva.setEmpresa(empresa);
        laudoPreventiva.setTecnico(tecnico);
        laudoPreventiva.setEquipamentos(equipamentos);
        laudoPreventiva.setDescricao(laudoRequest.getDescricao());
        laudoPreventiva.setDataCriacao(LocalDate.now());

        // Salvar o objeto LaudoPreventiva no banco de dados
        return laudoPreventivaRepository.save(laudoPreventiva);
    }

    public LaudoPreventiva buscarLaudoPorId(Long id) {
        return laudoPreventivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laudo preventiva não encontrado com o ID: " + id));
    }
    public List<LaudoPreventivaPDFDTO> getAllLaudoTecnicoPDFDTO() {
        List<LaudoPreventiva> laudos = laudoPreventivaRepository.findAll();
        return laudos.stream()
                .map(LaudoPreventivaPDFMapper::mapLaudoPreventivaToDTO)
                .collect(Collectors.toList());
    }

}
