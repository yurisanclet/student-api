package com.student.api.Service.StudentService;

import com.student.api.Domain.DTO.Student.RegisterStudentDTO;
import com.student.api.Domain.DTO.Student.StudentDTO;
import com.student.api.Domain.DTO.Student.StudentWithCoursesDTO;
import com.student.api.Domain.DTO.Student.StudentWithoutCoursesDTO;
import com.student.api.Domain.Entity.Course;
import com.student.api.Domain.Entity.Student;
import com.student.api.Repository.CourseRepository;
import com.student.api.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@Transactional
public class StudentServiceImplements implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public StudentServiceImplements(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
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
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
        }
        Student student = studentOptional.get();
        student.update(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist");
        } else {
            studentRepository.deleteById(id);
        }
    }

    @Override
    public List<StudentWithoutCoursesDTO> getAllStudentsWithoutCourses() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDtoWithoutCourses)
                .collect(Collectors.toList());
    }

    private StudentWithoutCoursesDTO convertToDtoWithoutCourses(Student student) {
        return new StudentWithoutCoursesDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getRegistrationNumber()
        );
    }

    @Override
    public StudentWithCoursesDTO getStudentWithCourses(String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("No student found with id " + id);
        }
        Student student = studentOptional.get();
        return convertToDtoWithCourses(student);
    }

    private StudentWithCoursesDTO convertToDtoWithCourses(Student student) {
        return new StudentWithCoursesDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getRegistrationNumber(),
                student.getCourses()
        );
    }

    @Override
    public void enrollStudent(String studentId, List<String> courseNames) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
        }
        Student student = studentOptional.get();

        List<Course> courses = courseRepository.findAllByNameIn(courseNames);
        if (courses.size() != courseNames.size()) {
            throw new IllegalArgumentException("Some courses do not exist");
        }

        for (Course course : courses) {
            student.addCourse(course);
        }

        studentRepository.save(student);
    }
}
