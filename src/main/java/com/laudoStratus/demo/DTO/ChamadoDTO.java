package com.laudoStratus.demo.DTO;

import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChamadoDTO {
    private String titulo;
    private String descricao;
    private Long idCategoria;
    private Long idEmpresa;
    private Long idTecnico;
    private Long idUsuario;
}
