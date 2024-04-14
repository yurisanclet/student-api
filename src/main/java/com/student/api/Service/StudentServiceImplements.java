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
import java.util.Optional;

@Service
@Primary
@Transactional
public class StudentServiceImplements implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImplements(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public void registerStudent(RegisterStudentDTO studentDTO) {
        if (studentDTO == null || studentDTO.registrationNumber() == null) {
            throw new IllegalArgumentException("Invalid student data provided");
        }
        Student student = new Student(studentDTO);
        if(studentRepository.existsByRegistrationNumber(studentDTO.registrationNumber())) {
            throw new IllegalArgumentException("Student with registration number " + studentDTO.registrationNumber() + " already exists");
        }
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(String studentId, StudentDTO studentDTO) {
        if(studentDTO == null || studentDTO.registrationNumber() == null) {
            throw new IllegalArgumentException("Invalid student data provided");
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + studentId + " does not exist"));
        student.update(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " does not exist"));
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
