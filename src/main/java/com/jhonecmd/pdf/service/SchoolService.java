package com.jhonecmd.pdf.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.jhonecmd.pdf.model.SchoolEntity;
import com.jhonecmd.pdf.repository.SchoolRepository;
import com.jhonecmd.pdf.repository.StudentRepository;
import com.jhonecmd.pdf.utils.DateUtils;
import com.jhonecmd.pdf.utils.ReportUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    public ByteArrayInputStream report() throws IOException {

        ReportUtils report = ReportUtils.getInstance();

        report.setPageSize(PageSize.A4.rotate());
        report.addParagraph(new Paragraph("School List").setFontSize(28)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER));

        report.addNewLine();

        report.openTable(3);
        report.addTableHeader("NAME", "STUDENTS", "CREATED AT");

        int totalStudents = this.schoolRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SchoolEntity::getName)).map(school -> {
                    int studentsCount = studentRepository.countBySchool(school);
                    report.addTableColumn(school.getName().toUpperCase());
                    report.addTableColumn(studentsCount);
                    report.addTableColumn(DateUtils.format(school.getCreatedAt(), "dd/MM/yyyy HH:mm"));

                    return  studentsCount;
                })
                .mapToInt(Integer::valueOf)
                .sum();

        report.addTableFooter(null, totalStudents, null);

        report.closeTable();
        report.closeDocument();

        return report.getByteArrayInputStream();
    }
}
