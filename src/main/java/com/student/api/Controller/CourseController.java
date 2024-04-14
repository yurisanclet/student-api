package com.student.api.Controller;

import com.student.api.Domain.DTO.Course.CourseDTO;
import com.student.api.Domain.DTO.Course.RegisterCourseDTO;
import com.student.api.Domain.Entity.Course;
import com.student.api.Service.CourseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin("http://localhost:8000")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping()
    public ResponseEntity<String> registerCourse(@RequestBody @Validated RegisterCourseDTO course) {
        courseService.registerCourse(course);
        return ResponseEntity.ok("Course registered successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCourse(@PathVariable String id, @RequestBody @Validated CourseDTO course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.ok("Course updated successfully");
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> partiallyUpdateCourse(@PathVariable String id, @RequestBody @Validated CourseDTO course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.ok("Course updated successfully");
    }
}
