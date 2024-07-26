package com.laudoStratus.demo.mapper;

import com.laudoStratus.demo.DTO.EquipamentoSetorDTO;
import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.LaudoTecnico;

import java.util.List;
import java.util.stream.Collectors;

public class LaudoPreventivaPDFMapper {

    public static LaudoPreventivaPDFDTO mapLaudoPreventivaToDTO(LaudoPreventiva laudoPreventiva) {
        List<EquipamentoSetorDTO> equipamentosPorSetor = laudoPreventiva.getEquipamentos().stream()
                .map(equipamento -> new EquipamentoSetorDTO(
                        equipamento.getSetor(), // Obtendo o nome do setor
                        1L // Contando apenas um equipamento por conjunto
                ))
                .collect(Collectors.toList());

        return new LaudoPreventivaPDFDTO(
                laudoPreventiva.getIdLaudoPrev(),
                laudoPreventiva.getEmpresa().getIdEmpresa(), // Alterado para obter o ID da empresa
                laudoPreventiva.getEmpresa().getNomeEmpresa(), // Obtendo o nome da empresa
                laudoPreventiva.getEquipamentos().stream().map(Equipamento::getIdEquipamento).collect(Collectors.toList()),
                laudoPreventiva.getTecnico().getIdTecnico(), // Alterado para obter o ID do técnico
                laudoPreventiva.getTecnico().getNomeTecnico(), // Obtendo o nome do técnico
                laudoPreventiva.getDescricao(),
                equipamentosPorSetor,
                laudoPreventiva.getDataCriacao()
        );
    }
}