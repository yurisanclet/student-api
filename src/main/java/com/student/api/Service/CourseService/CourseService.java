package com.student.api.Service.CourseService;

import com.student.api.Domain.DTO.Course.CourseDTO;
import com.student.api.Domain.DTO.Course.RegisterCourseDTO;
import com.student.api.Domain.Entity.Course;

import java.util.List;
public interface CourseService {
    void registerCourse(RegisterCourseDTO courseDTO);
    void updateCourse(String id, CourseDTO updateCourseDTO);
    void deleteCourse(String id);
    List<Course> getAllCourses();
}
