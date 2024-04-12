package com.laudoStratus.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaudoTecnicoResponse {
    private Long id;
    private EmpresaResponse empresa;
    private List<EquipamentoResponse> equipamentos;
    private TecnicoResponse tecnico;
    private String descricao;
    private LocalDate dataCriacao;
}