package com.student.api.Domain.Entity;

import com.student.api.Domain.DTO.RegisterStudentDTO;
import com.student.api.Domain.DTO.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "registration_number", nullable = false, unique = true)
    private Long registrationNumber;

    public Student(RegisterStudentDTO studentDTO) {
        this.name = studentDTO.name();
        this.surname = studentDTO.surname();
        this.registrationNumber = studentDTO.registrationNumber();
    }

    public void update(StudentDTO studentDTO) {
        this.name = studentDTO.name();
        this.surname = studentDTO.surname();
        this.registrationNumber = studentDTO.registrationNumber();
    }
}
