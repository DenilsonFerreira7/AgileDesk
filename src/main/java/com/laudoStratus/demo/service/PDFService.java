package com.laudoStratus.demo.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoTecnico;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFService {

    public byte[] generatePDF(LaudoTecnico laudoTecnico) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Caminho para o modelo PDF preenchível
        String modeloPdfPath = "src/main/resources/static/modelo.pdf";
        float x = 395;
        float y = 200;

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(new PdfReader(modeloPdfPath), writer)) {
            Document document = new Document(pdf);

            // Define a fonte e o tamanho da fonte
            PdfFont font = PdfFontFactory.createFont();
            float fontSize = 12;

            document.add(new Paragraph("\n\n\n"));
            // Adicionar título "Laudo Técnico"
            document.add(new Paragraph("Laudo Técnico")
                    .setFont(font)
                    .setFontSize(20)
                    .setFontColor(ColorConstants.BLACK) // Definindo a cor do texto como branco
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n\n\n"));

            // Adicionar informações sobre a empresa e o técnico
            document.add(new Paragraph("Empresa: " + laudoTecnico.getEmpresa().getNomeEmpresa())
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("Telefone: " + laudoTecnico.getEmpresa().getTelefone())
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setTextAlignment(TextAlignment.LEFT));
            // Adicionar data de criação
            document.add(new Paragraph("Data de atendimento: " + laudoTecnico.getDataCriacao())
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph(laudoTecnico.getTecnico().getNomeTecnico())
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setFixedPosition(x, y, 200)); // 200 é a largura do texto (aproximadamente)

            document.add(new Paragraph("\n\n"));

            // Adicionar informações sobre os equipamentos
            document.add(new Paragraph("Equipamentos:")
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setBold()
                    .setTextAlignment(TextAlignment.LEFT));
            List<Equipamento> equipamentos = laudoTecnico.getEquipamentos();
            for (Equipamento equipamento : equipamentos) {
                document.add(new Paragraph("Equipamento: " + equipamento.getNomeEquipamento() +
                        " - Configuração: " + equipamento.getDescricao())
                        .setFont(font)
                        .setFontSize(fontSize)
                        .setTextAlignment(TextAlignment.LEFT));
            }

            document.add(new Paragraph("\n\n\n"));

            // Adicionar a descrição do atendimento
            document.add(new Paragraph("Descrição do Atendimento:")
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setBold()
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph(laudoTecnico.getDescricao())
                    .setFont(font)
                    .setFontSize(fontSize)
                    .setTextAlignment(TextAlignment.LEFT));

            document.add(new Paragraph("\n\n\n"));
            document.close();
        }

        return outputStream.toByteArray();
    }

    public String generatePDFBase64(LaudoTecnico laudoTecnico) throws IOException {
        // Retorna diretamente o array de bytes
        return new String(generatePDF(laudoTecnico));
    }
}
