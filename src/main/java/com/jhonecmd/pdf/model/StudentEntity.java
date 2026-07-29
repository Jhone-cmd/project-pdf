package com.jhonecmd.pdf.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String  name;
    private String email;
    private String password;
    private LocalDate birthday;

    @ManyToOne
    private  SchoolEntity school;


    @Column(name = "created_At")
    private LocalDateTime createdAt;

    public StudentEntity(String name, String email, String password, LocalDate birthday, SchoolEntity school) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.school = school;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
