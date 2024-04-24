package com.laudoStratus.demo.mapper;

import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.models.LaudoTecnico;

public class LaudoTecnicoPDFMapper {
    public static LaudoTecnicoPDFDTO mapLaudoTecnicoToLaudoTecnicoPDFDTO(LaudoTecnico laudoTecnico) {
        return new LaudoTecnicoPDFDTO(
                laudoTecnico.getId(), // Adiciona o ID do laudo
                laudoTecnico.getEmpresa().getNomeEmpresa(),
                laudoTecnico.getTecnico().getNomeTecnico(),
                laudoTecnico.getDescricao(),
                laudoTecnico.getDataCriacao().toString()
        );
    }
}