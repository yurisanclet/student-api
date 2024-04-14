package com.student.api.Domain.DTO.Student;

public record StudentWithoutCoursesDTO(
        String id,
        String name,
        String surname,
        Long registrationNumber) {
}
