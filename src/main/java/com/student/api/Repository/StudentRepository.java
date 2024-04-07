package com.student.api.Repository;

import com.student.api.Domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByRegistrationNumber(Long registrationNumber);
    Student findById(String id);
    void deleteById(String id);
}
