package com.student.api.Domain.DTO.Student;

import java.util.UUID;

public record StudentDTO (
    String id,
    String name,
    String surname,
    Long registrationNumber
){}
