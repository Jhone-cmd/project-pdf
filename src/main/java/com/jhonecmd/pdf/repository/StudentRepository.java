package com.jhonecmd.pdf.repository;

import com.jhonecmd.pdf.model.SchoolEntity;
import com.jhonecmd.pdf.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    int countBySchool(SchoolEntity school);
}
