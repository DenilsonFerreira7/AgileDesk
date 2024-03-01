package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.LaudoTecnicoPDFDTO;
import com.laudoStratus.demo.mapper.LaudoTecnicoPDFMapper;
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
import java.util.List;
import java.util.stream.Collectors;


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

        String nomeEmpresa = laudoTecnico.getEmpresa().getNomeEmpresa();
        Long laudoId = laudoTecnico.getId();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nomePDF = nomeEmpresa + "_laudo" + laudoId + "_" + timestamp + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "inline; filename=" + nomePDF);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping("/laudos/{nomeEmpresa}")
    public ResponseEntity<List<String>> listarLinksPDFsPorEmpresa(@PathVariable String nomeEmpresa) {
        List<LaudoTecnico> laudos = laudoTecnicoService.obterLaudosPorNomeEmpresa(nomeEmpresa);

        List<String> linksPDFs = laudos.stream()
                .map(this::criarLinkPDF)
                .collect(Collectors.toList());

        return new ResponseEntity<>(linksPDFs, HttpStatus.OK);
    }

    private String criarLinkPDF(LaudoTecnico laudoTecnico) {
        LaudoTecnicoPDFDTO pdfDTO = LaudoTecnicoPDFMapper.mapLaudoTecnicoToLaudoTecnicoPDFDTO(laudoTecnico);

        return "/pdf/laudo/" + laudoTecnico.getId() + "?empresa=" + pdfDTO.getEmpresaNome();
    }
}
