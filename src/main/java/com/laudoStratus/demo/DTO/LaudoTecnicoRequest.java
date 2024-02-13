package com.laudoStratus.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class LaudoTecnicoRequest {

    private String nomeEmpresa;
    private List<Long> idsEquipamentos;
    private String nomeTecnico;
    private String descricao;
}
