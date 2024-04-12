package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.DTO.LaudoTecnicoRequest;
import com.laudoStratus.demo.mapper.LaudoTecnicoPDFMapper;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.service.LaudoPreventivaService;
import com.laudoStratus.demo.service.LaudoTecnicoService;
import com.laudoStratus.demo.service.PDFService;
import com.laudoStratus.demo.service.PDFServicePreventiva;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PDFController {

    private final PDFService pdfService;
    private final PDFServicePreventiva pdfServicePreventiva;
    private final LaudoTecnicoService laudoTecnicoService;
    private final LaudoPreventivaService laudoPreventivaService;




    @PostMapping("/laudoTecnico")
    public ResponseEntity<byte[]> cadastrarLaudoTecnicoEObterPDF(@RequestBody LaudoTecnicoRequest laudoRequest) {
        // Crie o laudo técnico utilizando o serviço LaudoTecnicoService
        LaudoTecnico laudoTecnico = laudoTecnicoService.criarLaudo(laudoRequest);

        // Gere o PDF do laudo técnico utilizando o serviço PDFService
        byte[] pdfBytes = pdfService.generatePDF(laudoTecnico);

        // Defina o nome do PDF com base nos dados do laudo técnico
        String nomeEmpresa = laudoTecnico.getEmpresa().getNomeEmpresa();
        Long laudoId = laudoTecnico.getId();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nomePDF = nomeEmpresa + "_laudo" + laudoId + "_" + timestamp + ".pdf";

        // Defina o cabeçalho Content-Disposition para indicar o nome do arquivo PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=" + nomePDF);

        // Retorne o PDF no corpo da resposta HTTP
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @PostMapping("/laudoPreventiva")
    public ResponseEntity<byte[]> cadastrarLaudoPreventivaEObterPDF(@RequestBody LaudoTecnicoRequest laudoRequest) {
        LaudoPreventiva laudoPreventiva = laudoPreventivaService.criarLaudo(laudoRequest);
        byte[] pdfBytes = pdfServicePreventiva.generatePDF(laudoPreventiva);

        String nomeEmpresa = laudoPreventiva.getEmpresa().getNomeEmpresa();
        Long laudoId = laudoPreventiva.getId();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nomePDF = nomeEmpresa + "_laudo_preventiva_" + laudoId + "_" + timestamp + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=" + nomePDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    private String criarLinkPDF(LaudoTecnico laudoTecnico) {
        LaudoTecnicoPDFDTO pdfDTO = LaudoTecnicoPDFMapper.mapLaudoTecnicoToLaudoTecnicoPDFDTO(laudoTecnico);

        return "/pdf/laudo/" + laudoTecnico.getId() + "?empresa=" + pdfDTO.getEmpresaNome();
    }



}
