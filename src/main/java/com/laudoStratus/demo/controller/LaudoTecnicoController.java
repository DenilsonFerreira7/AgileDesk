package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.DTO.LaudoTecnicoResponse;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.service.LaudoPreventivaService;
import com.laudoStratus.demo.service.LaudoTecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/laudo")
public class LaudoTecnicoController {

    private final LaudoTecnicoService laudoTecnicoService;
    private final LaudoPreventivaService laudoPreventivaService;

    @PostMapping (value = "/criar",produces = "application/json")
    public ResponseEntity<LaudoTecnico> criarLaudoTecnico(@RequestBody LaudoPreventivaPDFDTO laudoRequest) {
        LaudoTecnico novoLaudo = laudoTecnicoService.criarLaudo(laudoRequest);
        return new ResponseEntity<>(novoLaudo, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LaudoTecnicoResponse> obterLaudoTecnico(@PathVariable Long id) {
        LaudoTecnicoResponse laudoTecnicoResponse = laudoTecnicoService.obterLaudoTecnico(id);
        return new ResponseEntity<>(laudoTecnicoResponse, HttpStatus.OK);
    }

    @GetMapping("/nomeEmpresa/{nomeEmpresa}")
    public ResponseEntity<List<LaudoTecnico>> obterLaudosPorNomeEmpresa(@PathVariable String nomeEmpresa) {
        List<LaudoTecnico> laudos = laudoTecnicoService.obterLaudosPorNomeEmpresa(nomeEmpresa);
        return new ResponseEntity<>(laudos, HttpStatus.OK);
    }

    @GetMapping("/pdfall")
    public ResponseEntity<List<LaudoTecnicoPDFDTO>> getAllLaudoTecnicoPDF() {
        List<LaudoTecnicoPDFDTO> laudosPDF = laudoTecnicoService.getAllLaudoTecnicoPDFDTO();
        return new ResponseEntity<>(laudosPDF, HttpStatus.OK);
    }
    @GetMapping("/pdfPreventivaAll")
    public ResponseEntity<List<LaudoPreventivaPDFDTO>> getAllLaudoPreventivaPDF() {
        List<LaudoPreventivaPDFDTO> laudoPreventivaPDFDTOS = laudoPreventivaService.getAllLaudoTecnicoPDFDTO();
        return new ResponseEntity<>(laudoPreventivaPDFDTOS, HttpStatus.OK);
    }


}