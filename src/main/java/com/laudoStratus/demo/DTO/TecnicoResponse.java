package com.laudoStratus.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoResponse {
    private Long tecnicoId;
    private String nomeTecnico;
    private String cargoTecnico;

}