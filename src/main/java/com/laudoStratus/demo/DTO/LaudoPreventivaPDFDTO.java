package com.laudoStratus.demo.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaudoPreventivaPDFDTO {
    private Long id;
    private Long empresaId;
    private String empresaNome; // Adicione o nome da empresa
    private List<Long> equipamentoIds;
    private Long tecnicoId;
    private String tecnicoNome; // Adicione o nome do técnico
    private String descricao;
    private List<EquipamentoSetorDTO> equipamentosPorSetor;
    private LocalDate dataCriacao;

}