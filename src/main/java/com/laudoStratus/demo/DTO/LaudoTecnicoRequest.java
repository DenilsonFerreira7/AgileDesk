package com.laudoStratus.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class LaudoTecnicoRequest {

    private Long empresaId;
    private List<Long> equipamentoIds;
    private Long tecnicoId;
    private String descricao;
}