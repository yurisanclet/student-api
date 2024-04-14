CREATE TABLE student_course (
    student_id VARCHAR(36),
    course_id VARCHAR(36),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);
