package com.jhonecmd.pdf.repository;

import com.jhonecmd.pdf.model.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<SchoolEntity, UUID> {
}
