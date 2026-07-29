package com.jhonecmd.pdf.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String  name;
    private String email;
    private LocalDate birthday;

    @ManyToOne
    private  SchoolEntity school;


    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
