package com.student.api.Repository;

import com.student.api.Domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByRegistrationNumber(Long registrationNumber);
    Optional<Student> findById(String id);
    void deleteById(String id);
}
