package com.laudoStratus.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponse {
    private Long empresaId;
    private String nomeEmpresa;
    private String endereco;
    private String telefone;
}
