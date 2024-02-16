package com.laudoStratus.demo.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.laudoStratus.demo.models.LaudoTecnico;
import com.laudoStratus.demo.service.LaudoTecnicoService;
import com.laudoStratus.demo.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PDFController {

    private final PDFService pdfService;
    private final LaudoTecnicoService laudoTecnicoService;
    @GetMapping("/laudo/{id}")
    public ResponseEntity<byte[]> gerarPDFLaudo(@PathVariable Long id) {
        LaudoTecnico laudoTecnico = laudoTecnicoService.buscarLaudoPorId(id);
        byte[] pdfBytes = pdfService.generatePDF(laudoTecnico);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "laudo-tecnico.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

