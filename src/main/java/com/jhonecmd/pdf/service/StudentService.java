package com.jhonecmd.pdf.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.jhonecmd.pdf.model.StudentEntity;
import com.jhonecmd.pdf.repository.StudentRepository;
import com.jhonecmd.pdf.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public ByteArrayOutputStream report() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(baos));
        pdf.setDefaultPageSize(PageSize.A4.rotate());

        Document document = new Document(pdf);

        Paragraph title = new Paragraph("Student List")
                .setFontSize(28)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        document.add(new Paragraph("\n"));

        Table table = new Table(6)
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);

        table.addHeaderCell("NAME");
        table.addHeaderCell("EMAIL");
        table.addHeaderCell("AGE");
        table.addHeaderCell("BIRTHDAY");
        table.addHeaderCell("SCHOOL");
        table.addHeaderCell("CREATED_AT");


        studentRepository.findAll().stream()
                .sorted(Comparator.comparing((StudentEntity student) -> student.getSchool().getName())
                        .thenComparing(StudentEntity::getName))
                .forEach(student -> {
                    table.addCell(student.getName());
                    table.addCell(student.getEmail());
                    table.addCell(String.valueOf(DateUtils.age(student.getBirthday())));
                    table.addCell(DateUtils.format(student.getBirthday(), "dd/MM/yyyy"));
                    table.addCell(student.getSchool().getName());
                    table.addCell(DateUtils.format(student.getCreatedAt(), "dd/MM/yyyy HH:mm"));
                });

        document.add(table);
        document.close();

        return new ByteArrayOutputStream();

    }
}
