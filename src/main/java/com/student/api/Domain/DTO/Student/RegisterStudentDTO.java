package com.student.api.Domain.DTO.Student;

public record RegisterStudentDTO(
        String name,
        String surname,
        Long registrationNumber
) {}
