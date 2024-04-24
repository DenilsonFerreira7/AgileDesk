package com.laudoStratus.demo.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.kernel.colors.ColorConstants;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.models.TipoEquipamento;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.LaudoPreventivaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PDFServicePreventiva {

    @Autowired
    private LaudoPreventivaRepository laudoPreventivaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public byte[] generatePDF(LaudoPreventiva laudoPreventiva) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer)) {
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            float fontSize = 14;

            // Cabeçalho com fundo cinza
            document.add(createHeader("Laudo de Preventiva"));

            // Informações da empresa e técnico
            Long empresaId = laudoPreventiva.getEmpresa().getEmpresaId();
            Empresa empresa = empresaRepository.findById(empresaId).orElse(null);

            if (empresa != null) {
                document.add(createInfoParagraph("Empresa: " + empresa.getNomeEmpresa()));
                // Adicionar endereço da empresa
                document.add(createInfoParagraph("Endereço: " + empresa.getEndereco())); // Adicione o endereço da empresa conforme necessário
            }

            // Adicionar nome do técnico
            document.add(createInfoParagraph("Técnico: " + laudoPreventiva.getTecnico().getNomeTecnico()));

            document.add(new Paragraph("\n\n"));

            // Tabela de equipamentos
            Table equipmentTable = new Table(3);
            equipmentTable.addCell(new Cell().add(new Paragraph("Tipo de Equipamento")).setFont(font).setBold());
            equipmentTable.addCell(new Cell().add(new Paragraph("Setor")).setFont(font).setBold());
            equipmentTable.addCell(new Cell().add(new Paragraph("Quantidade")).setFont(font).setBold());

            List<Object[]> countEquipamentosETipos = laudoPreventivaRepository.countEquipamentosByTipoAndSetor(laudoPreventiva.getId());
            for (Object[] row : countEquipamentosETipos) {
                String tipoEquipamento = (String) row[1];
                String setor = (String) row[2];
                Long quantidade = (Long) row[0];

                equipmentTable.addCell(new Cell().add(new Paragraph(tipoEquipamento)).setFont(font));
                equipmentTable.addCell(new Cell().add(new Paragraph(setor)).setFont(font));
                equipmentTable.addCell(new Cell().add(new Paragraph(String.valueOf(quantidade))).setFont(font));
            }

            document.add(equipmentTable);

            // Tabela de Especificações do Equipamento
            document.add(createHeader("Especificações do Equipamento"));

            Table specificationsTable = new Table(2);
            specificationsTable.addCell(new Cell().add(new Paragraph("Nome do Equipamento")).setFont(font).setBold());
            specificationsTable.addCell(new Cell().add(new Paragraph("Descrição")).setFont(font).setBold());

            List<Equipamento> equipamentos = laudoPreventiva.getEquipamentos();
            for (Equipamento equipamento : equipamentos) {
                specificationsTable.addCell(new Cell().add(new Paragraph(equipamento.getNomeEquipamento())).setFont(font));
                specificationsTable.addCell(new Cell().add(new Paragraph(equipamento.getDescricao())).setFont(font));
            }

            document.add(specificationsTable);

            // Descrição da preventiva
            document.add(createInfoParagraph("\n\nDescrição da Preventiva:"));
            document.add(new Paragraph(laudoPreventiva.getDescricao()).setFont(font).setFontSize(fontSize));
            document.add(new Paragraph("\n\n"));

            // Data de criação
            document.add(createInfoParagraph("Data de Criação: " + laudoPreventiva.getDataCriacao()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    public String generatePDFBase64(LaudoPreventiva laudoPreventiva) {
        return new String(generatePDF(laudoPreventiva));
    }

    // Método para criar o cabeçalho com fundo cinza e título centralizado
    private Paragraph createHeader(String title) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        float fontSize = 18;

        Paragraph header = new Paragraph(title)
                .setFont(font)
                .setFontSize(fontSize)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

        // Adicionar fundo cinza ao cabeçalho
        header.setBackgroundColor(ColorConstants.LIGHT_GRAY); // Corrigido para utilizar ColorConstants

        return header;
    }

    // Método para criar parágrafos de informações
    private Paragraph createInfoParagraph(String text) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        float fontSize = 14;

        return new Paragraph(text)
                .setFont(font)
                .setFontSize(fontSize);
    }
}
