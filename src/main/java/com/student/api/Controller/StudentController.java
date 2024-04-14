package com.student.api.Controller;

import com.student.api.Domain.DTO.Student.RegisterStudentDTO;
import com.student.api.Domain.DTO.Student.StudentDTO;
import com.student.api.Domain.DTO.Student.StudentWithCoursesDTO;
import com.student.api.Domain.DTO.Student.StudentWithoutCoursesDTO;
import com.student.api.Domain.Entity.Student;
import com.student.api.Service.StudentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:8000")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/students-without-courses")
    public List<StudentWithoutCoursesDTO> getAllStudentsWithoutCourses() {
        return studentService.getAllStudentsWithoutCourses();
    }

    @GetMapping("/student-with-courses/{id}")
    public StudentWithCoursesDTO getAllStudentsWithCourses(@PathVariable String id) {
        return studentService.getStudentWithCourses(id);
    }
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody @Validated RegisterStudentDTO registeredStudent) {
        studentService.registerStudent(registeredStudent);
        return ResponseEntity.ok("Student registered successfully");
    }
    @PostMapping("/enroll/{studentId}")
    public ResponseEntity<String> enrollStudent(@PathVariable String studentId, @RequestBody List<String> courses) {
        studentService.enrollStudent(studentId, courses);
        return ResponseEntity.ok("Student enrolled successfully");
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudentRegister(@PathVariable String id, @RequestBody @Validated StudentDTO student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok("Student updated successfully");
    }
    @PatchMapping("{id}")
    public ResponseEntity<String> updateStudent(@PathVariable String id, @RequestBody @Validated StudentDTO student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok("Student updated successfully");
    }
}
