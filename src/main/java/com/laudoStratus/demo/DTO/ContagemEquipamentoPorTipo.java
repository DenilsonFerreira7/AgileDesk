package com.laudoStratus.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContagemEquipamentoPorTipo {

    private String tipoEquipamento;
    private long quantidade;
}
