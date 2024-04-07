package com.student.api.Controller;

import com.student.api.Domain.DTO.RegisterStudentDTO;
import com.student.api.Domain.DTO.StudentDTO;
import com.student.api.Domain.Entity.Student;
import com.student.api.Service.StudentService;
import org.apache.coyote.Response;
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

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody @Validated RegisterStudentDTO registeredStudent) {
        studentService.registerStudent(registeredStudent);
        return ResponseEntity.ok("Student registered successfully");
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
}
