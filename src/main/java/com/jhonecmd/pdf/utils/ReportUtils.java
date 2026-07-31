package com.jhonecmd.pdf.utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ReportUtils {

    private Table table;
    private final ByteArrayOutputStream baos;
    private final PdfDocument pdfDocument;
    private final Document document;


    private ReportUtils() {
        this.baos = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.baos));
        this.document = new Document(pdfDocument);
    }

    public static  ReportUtils getInstance() {
        return  new ReportUtils();
    }

    public void setPageSize(PageSize pageSize) {
        this.pdfDocument.setDefaultPageSize(pageSize);
    }

    public void addParagraph(Paragraph paragraph) {
        this.document.add(paragraph);
    }

    public void addNewLine() {
        this.document.add(new Paragraph("\n"));
    }

    public void openTable(int numColumns) {
        this.table = new Table(numColumns)
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);
    }

    public void addTableHeader(String... headers) {
        for(String header: headers) {
            this.table.addHeaderCell(header);
        }
    }

    public void addTableColumn(Object object) {
        this.table.addCell(object.toString());
    }

    public void addTableFooter(Object... footers) throws IOException {
        for(Object footer: footers) {
                       String text = (footer == null) ? "" : footer.toString();

            Paragraph p = new Paragraph(text).setFontSize(13).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
            this.table.addFooterCell(p);
        }

        this.table.getFooter().setBackgroundColor(ColorConstants.LIGHT_GRAY);
    }

    public void closeTable() {
        this.document.add(this.table);
    }

    public void closeDocument() {
        this.document.close();
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.baos.toByteArray());
    }

}
