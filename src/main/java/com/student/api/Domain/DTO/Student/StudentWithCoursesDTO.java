package com.student.api.Domain.DTO.Student;

import com.student.api.Domain.Entity.Course;

import java.util.Set;

public record StudentWithCoursesDTO (
        String id,
        String name,
        String surname,
        Long registrationNumber,
        Set<Course> courses){}
