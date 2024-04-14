package com.student.api.Repository;

import com.student.api.Domain.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository  extends JpaRepository<Course, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Course c WHERE lower(c.name) = lower(:name)")
    boolean existsByName(@Param("name") String name);
    Optional<Course> findCourseById(String id);
    void deleteById(String id);
    List<Course> findAllByNameIn(List<String> names);
}
