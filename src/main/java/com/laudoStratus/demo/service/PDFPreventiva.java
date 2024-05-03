package com.laudoStratus.demo.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Equipamento;
import com.laudoStratus.demo.models.LaudoPreventiva;
import com.laudoStratus.demo.repository.EmpresaRepository;
import com.laudoStratus.demo.repository.LaudoPreventivaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFPreventiva {

    @Autowired
    private LaudoPreventivaRepository laudoPreventivaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private Document document;

    public byte[] generatePDF(LaudoPreventiva laudoPreventiva) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfReader reader = new PdfReader("static/modelo2.pdf");
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(reader, writer)) {
            document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            float fontSize = 14;

            // Adiciona o manipulador de eventos para adicionar o rodapé
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());

            // Páginas existentes no documento PDF modelo
            int numberOfPages = pdf.getNumberOfPages();

            // Adicionando os dados ao PDF modelo
            for (int i = 1; i <= numberOfPages; i++) {
                PdfPage page = pdf.getPage(i);
                document.add(new Paragraph("\n\n\n\n"));

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
                document.add(new Paragraph("\n\n"));

                // Tabela de Especificações do Equipamento
                document.add(createHeader("Especificações do Equipamento"));

                Table specificationsTable = new Table(2);
                specificationsTable.addCell(new Cell().add(new Paragraph("Nome do Equipamento")).setFont(font).setBold());
                specificationsTable.addCell(new Cell().add(new Paragraph("Descrição")).setFont(font).setBold());

                document.add(new Paragraph("\n\n"));

                List<Equipamento> equipamentos = laudoPreventiva.getEquipamentos();
                for (Equipamento equipamento : equipamentos) {
                    specificationsTable.addCell(new Cell().add(new Paragraph(equipamento.getNomeEquipamento())).setFont(font));
                    specificationsTable.addCell(new Cell().add(new Paragraph(equipamento.getDescricao())).setFont(font));
                }

                document.add(specificationsTable);
                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph("\n\n"));


                // Descrição da preventiva
                document.add(createInfoParagraph("\n\nDescrição da Preventiva:"));
                document.add(new Paragraph(laudoPreventiva.getDescricao()).setFont(font).setFontSize(fontSize));
                document.add(new Paragraph("\n\n"));
                // Data de criação
                document.add(createInfoParagraph("Data de Criação: " + laudoPreventiva.getDataCriacao()));

                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    public String generatePDFBase64(LaudoPreventiva laudoPreventiva) {
        return new String(generatePDF(laudoPreventiva));
    }

    // Método para criar o cabeçalho com título centralizado
    private Paragraph createHeader(String title) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        float fontSize = 18;

        Paragraph header = new Paragraph(title)
                .setFont(font)
                .setFontSize(fontSize)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

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

    // Manipulador de eventos para adicionar o rodapé
    private class FooterEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            float x = page.getPageSize().getLeft();
            float y = page.getPageSize().getBottom();
            float w = page.getPageSize().getWidth();
            float h = 50;
            Rectangle rect = new Rectangle(x, y, w, h);

            Color blueColor = new DeviceRgb(18, 144, 203);

            pdfCanvas.saveState()
                    .setFillColor(blueColor)
                    .rectangle(rect)
                    .fillStroke()
                    .restoreState();
        }
    }
}