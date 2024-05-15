package com.laudoStratus.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoResponse {
    private Long tecnicoId;
    private String nomeTecnico;
    private String cargoTecnico;
    private String email;
    private String telefone;
    private String fotoPerfil;

}