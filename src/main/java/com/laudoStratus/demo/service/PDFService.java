package com.laudoStratus.demo.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.laudoStratus.demo.models.LaudoTecnico;
import org.springframework.stereotype.Service;

@Service
public class PDFService {

    public byte[] generatePDF(LaudoTecnico laudoTecnico) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer)) {
            Document document = new Document(pdf);

            // Define a fonte e o tamanho da fonte
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            float fontSize = 14;

            document.add(new Paragraph("\n\n\n"));

            // Adiciona um título
            Paragraph title = new Paragraph("Laudo Técnico")
                    .setFont(font)
                    .setFontSize(26)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            document.add(new Paragraph("\n\n\n"));
            document.add(new Paragraph("\n\n\n"));

            // Adiciona informações do laudo técnico
            Paragraph empresa = new Paragraph("Empresa: " + laudoTecnico.getEmpresa().getNomeEmpresa())
                    .setFont(font)
                    .setFontSize(fontSize);
            document.add(empresa);

            Paragraph tecnico = new Paragraph("Técnico: " + laudoTecnico.getTecnico().getNomeTecnico())
                    .setFont(font)
                    .setFontSize(fontSize);
            document.add(tecnico);


            Paragraph descricao = new Paragraph("Descrição: " + laudoTecnico.getDescricao())
                    .setFont(font)
                    .setFontSize(fontSize);
            document.add(descricao);

            Paragraph dataCriacao = new Paragraph("Data de Criação: " + laudoTecnico.getDataCriacao())
                    .setFont(font)
                    .setFontSize(fontSize);
            document.add(dataCriacao);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}