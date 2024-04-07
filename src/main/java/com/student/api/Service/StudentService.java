package com.student.api.Service;

import com.student.api.Domain.DTO.RegisterStudentDTO;
import com.student.api.Domain.DTO.StudentDTO;
import com.student.api.Domain.Entity.Student;

import java.util.List;

public interface StudentService {
     void registerStudent(RegisterStudentDTO studentDTO);
     void updateStudent(String id, StudentDTO updateStudentDTO);
     void deleteStudent(String id);
     List<Student> getAllStudents();
}
