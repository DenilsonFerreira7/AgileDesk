package com.laudoStratus.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaudoTecnicoPDFDTO {
    private Long id;
    private String empresaNome;
    private String tecnicoNome;
    private String descricao;
    private String dataCriacao;
}