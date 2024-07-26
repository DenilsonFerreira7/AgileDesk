package com.laudoStratus.demo.mapper;

import com.laudoStratus.demo.DTO.EquipamentoResponse;
import com.laudoStratus.demo.models.Equipamento;

public class EquipamentoMapper {

    public static EquipamentoResponse mapEquipamentoToEquipamentoResponse(Equipamento equipamento) {
        return new EquipamentoResponse(
                equipamento.getIdEquipamento(),
                equipamento.getNomeEquipamento(),
                equipamento.getDescricao(),
                EmpresaMapper.mapEmpresaToEmpresaResponse(equipamento.getEmpresa())
        );
    }
}