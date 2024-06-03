package com.example.backendreactinicial.services;

import com.example.backendreactinicial.entities.Instrumento;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayInputStream generateInstrumentoPdf(Instrumento instrumento) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A4);

            // Agregar título
            document.add(new Paragraph(instrumento.getInstrumento())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(24)
                    .setTextAlignment(TextAlignment.CENTER));

            // Agregar imagen
            Image img = new Image(ImageDataFactory.create(instrumento.getImagen()));
            document.add(img.setWidth(UnitValue.createPercentValue(50)));

            // Agregar detalles
            document.add(new Paragraph("Marca: " + instrumento.getMarca())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(16));
            document.add(new Paragraph("Modelo: " + instrumento.getModelo())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(16));
            document.add(new Paragraph("Precio: $" + instrumento.getPrecio())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(16));
            document.add(new Paragraph("Costo de Envio: " + instrumento.getCostoEnvio())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(16));
            document.add(new Paragraph("Descripción: " + instrumento.getDescripcion())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(16));

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

