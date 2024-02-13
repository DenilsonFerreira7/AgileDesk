package com.laudoStratus.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipamentoResponse {
    private Long id;
    private String nomeEquipamento;
    private String descricao;
    private EmpresaResponse empresa;
}

