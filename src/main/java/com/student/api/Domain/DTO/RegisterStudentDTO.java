package com.student.api.Domain.DTO;

public record RegisterStudentDTO(
        String name,
        String surname,
        Long registrationNumber
) {}
