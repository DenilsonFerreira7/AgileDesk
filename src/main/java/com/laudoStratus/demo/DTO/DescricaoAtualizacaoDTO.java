package com.laudoStratus.demo.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DescricaoAtualizacaoDTO {

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;

    private String atualizadoPor;
}