package com.student.api.Service.CourseService;

import com.student.api.Domain.DTO.Course.CourseDTO;
import com.student.api.Domain.DTO.Course.RegisterCourseDTO;
import com.student.api.Domain.Entity.Course;
import com.student.api.Repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImplements implements CourseService {

    private final CourseRepository courseRepository;
    public CourseServiceImplements(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void registerCourse(RegisterCourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        if(courseRepository.existsByName(course.getName())){
            throw new IllegalArgumentException("Course already exists");
        }
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(String id, CourseDTO updateCourseDTO) {
        Optional<Course> optionalCourse = courseRepository.findCourseById(id);
        if (optionalCourse.isEmpty()) {
            throw new IllegalArgumentException("Course with id " + id + " does not exist");
        }
        Course course = optionalCourse.get();
        course.update(updateCourseDTO);
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
