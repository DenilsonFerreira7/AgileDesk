package com.laudoStratus.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipamentoSetorDTO {
    private String nomeSetor;
    private Long quantidadeEquipamentos;
}