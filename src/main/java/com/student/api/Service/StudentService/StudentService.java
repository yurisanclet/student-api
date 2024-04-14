package com.student.api.Service.StudentService;

import com.student.api.Domain.DTO.Student.RegisterStudentDTO;
import com.student.api.Domain.DTO.Student.StudentDTO;
import com.student.api.Domain.DTO.Student.StudentWithCoursesDTO;
import com.student.api.Domain.DTO.Student.StudentWithoutCoursesDTO;
import com.student.api.Domain.Entity.Student;

import java.util.List;

public interface StudentService {
     void registerStudent(RegisterStudentDTO studentDTO);
     void updateStudent(String id, StudentDTO updateStudentDTO);
     void deleteStudent(String id);
     void enrollStudent(String studentId, List<String> courseId);
     List<StudentWithoutCoursesDTO> getAllStudentsWithoutCourses();
     StudentWithCoursesDTO getStudentWithCourses(String id);
}
