package com.student.api.Service;

import com.student.api.Domain.DTO.RegisterStudentDTO;
import com.student.api.Domain.DTO.StudentDTO;
import com.student.api.Domain.Entity.Student;
import com.student.api.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@Transactional
public class StudentServiceImplements implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void registerStudent(RegisterStudentDTO studentDTO) {
        Student student = new Student(studentDTO);

        if(studentRepository.existsByRegistrationNumber(student.getRegistrationNumber())) {
            throw new IllegalArgumentException("Student with registration number " + student.getRegistrationNumber() + " already exists");
        }

        studentRepository.save(student);
    }

    @Override
    public void updateStudent(String studentId, StudentDTO studentDTO) {
        try {
            Student student = studentRepository.findById(studentId);
            if (student == null) {
                throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
            }
            student.update(studentDTO);
            studentRepository.save(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        if(studentRepository.findById(id) == null) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist");
        } else {
            studentRepository.deleteById(id);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
