package com.student.api.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.student.api.Domain.DTO.Course.CourseDTO;
import com.student.api.Domain.DTO.Course.RegisterCourseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonBackReference
    private Set<Student> students = new HashSet<>();

    public Course(RegisterCourseDTO courseDTO) {
        this.name = courseDTO.name();
        this.description = courseDTO.description();
    }
    public void addStudent(Student student) {
        this.students.add(student);
        student.getCourses().add(this);
    }

    public void update(CourseDTO updateCourseDTO) {
        this.name = updateCourseDTO.name();
        this.description = updateCourseDTO.description();
    }
}
