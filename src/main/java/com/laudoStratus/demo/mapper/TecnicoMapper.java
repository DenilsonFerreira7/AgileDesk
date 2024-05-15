package com.laudoStratus.demo.mapper;

import com.laudoStratus.demo.DTO.TecnicoResponse;
import com.laudoStratus.demo.models.Tecnico;

public class TecnicoMapper {

    public static TecnicoResponse mapTecnicoToTecnicoResponse(Tecnico tecnico) {
        return new TecnicoResponse(
                tecnico.getTecnicoId(),
                tecnico.getNomeTecnico(),
                tecnico.getCargoTecnico(),
                tecnico.getEmail(),
                tecnico.getTelefone(),
                tecnico.getFotoPerfil()
        );
    }
}