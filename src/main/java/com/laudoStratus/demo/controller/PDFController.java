package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.LaudoPreventivaPDFDTO;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.service.LaudoPreventivaService;
import com.laudoStratus.demo.service.LaudoTecnicoService;
import com.laudoStratus.demo.service.PDFLaudoTecnico;
import com.laudoStratus.demo.service.PDFPreventiva;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PDFController {

    private final PDFLaudoTecnico pdfLaudoTecnico;
    private final PDFPreventiva pdfPreventiva;
    private final LaudoTecnicoService laudoTecnicoService;
    private final LaudoPreventivaService laudoPreventivaService;


    @PostMapping("/laudoTecnico")
    public ResponseEntity<byte[]> cadastrarLaudoTecnicoEObterPDF(@RequestBody LaudoPreventivaPDFDTO laudoRequest) throws IOException {
        // Crie o laudo técnico utilizando o serviço LaudoTecnicoService
        LaudoTecnico laudoTecnico = laudoTecnicoService.criarLaudo(laudoRequest);

        // Gere o PDF do laudo técnico utilizando o serviço PDFService
        byte[] pdfBytes = pdfLaudoTecnico.generatePDF(laudoTecnico);

        // Defina o nome do PDF com base nos dados do laudo técnico
        String nomeEmpresa = laudoTecnico.getEmpresa().getNomeEmpresa();
        Long laudoId = laudoTecnico.getIdLaudoTec();
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
    public ResponseEntity<byte[]> cadastrarLaudoPreventivaEObterPDF(@RequestBody LaudoPreventivaPDFDTO laudoRequest) {
        LaudoPreventiva laudoPreventiva = laudoPreventivaService.criarLaudo(laudoRequest);
        byte[] pdfBytes = pdfPreventiva.generatePDF(laudoPreventiva);

        String nomeEmpresa = laudoPreventiva.getEmpresa().getNomeEmpresa();
        Long laudoId = laudoPreventiva.getIdLaudoPrev();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nomePDF = nomeEmpresa + "_laudo_preventiva_" + laudoId + "_" + timestamp + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=" + nomePDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping(value = "/laudo/{id}", produces = "application/pdf")
    public ResponseEntity<byte[]> gerarPdfPorId(@PathVariable Long id) throws IOException {
        LaudoTecnico laudoTecnico = laudoTecnicoService.buscarLaudoPorId(id);

        byte[] pdfBytes = pdfLaudoTecnico.generatePDF(laudoTecnico);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/laudoPreventiva/{id}", produces = "application/pdf")
    public ResponseEntity<byte[]> gerarPdfPorIdPreventiva(@PathVariable Long id) throws IOException {
        LaudoPreventiva laudoPreventiva = laudoPreventivaService.buscarLaudoPorId(id);

        byte[] pdfBytes = pdfPreventiva.generatePDF(laudoPreventiva);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
