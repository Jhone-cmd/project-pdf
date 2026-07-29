package com.jhonecmd.pdf.seeds;

import com.jhonecmd.pdf.model.SchoolEntity;
import com.jhonecmd.pdf.model.StudentEntity;
import com.jhonecmd.pdf.repository.SchoolRepository;
import com.jhonecmd.pdf.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SeedsRunner {

    @Bean
    CommandLineRunner run(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        return args -> {
            SchoolEntity ignite = new SchoolEntity("Ignite");
            SchoolEntity discover = new SchoolEntity("Discover");
            SchoolEntity expertsClub = new SchoolEntity("ExpertsClub");

            schoolRepository.save(ignite);
            schoolRepository.save(discover);
            schoolRepository.save(expertsClub);

            studentRepository.save(new StudentEntity("Pedro", "pedro@gmail.com", "123456", LocalDate.of(1992, 8, 28), discover));
            studentRepository.save(new StudentEntity("Marcos", "marcos@gmail.com", "123456", LocalDate.of(1990, 2, 11), discover));
            studentRepository.save(new StudentEntity("Paulo", "paulo@gmail.com", "123456", LocalDate.of(1988, 3, 12), discover));
            studentRepository.save(new StudentEntity("Vinicius", "vinicios@gmail.com", "123456", LocalDate.of(1996, 7, 18), discover));

            studentRepository.save(new StudentEntity("Jorge", "jorge@gmail.com", "123456", LocalDate.of(2000, 9, 21), ignite));
            studentRepository.save(new StudentEntity("Aline", "aline@gmail.com", "123456", LocalDate.of(1997, 10, 27), ignite));
            studentRepository.save(new StudentEntity("Paula", "paula@gmail.com", "123456", LocalDate.of(1995, 11, 2), ignite));

            studentRepository.save(new StudentEntity("Ana", "ana@gmail.com", "123456", LocalDate.of(1993, 3, 1), expertsClub));
            studentRepository.save(new StudentEntity("Marcia", "marcia@gmail.com", "123456", LocalDate.of(1964, 8, 9), expertsClub));
            studentRepository.save(new StudentEntity("Marta", "marta@gmail.com", "123456", LocalDate.of(1996, 1, 5), expertsClub));
            studentRepository.save(new StudentEntity("Yuri", "yuri@gmail.com", "123456", LocalDate.of(2001, 3, 25), expertsClub));
            studentRepository.save(new StudentEntity("Rodrigo", "rodrigo@gmail.com", "123456", LocalDate.of(2003, 5, 19), expertsClub));
            studentRepository.save(new StudentEntity("Marcelo", "marcelo@gmail.com", "123456", LocalDate.of(1995, 7, 11), expertsClub));
        };
    }
}
