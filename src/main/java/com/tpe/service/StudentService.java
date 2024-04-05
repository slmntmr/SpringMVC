package com.tpe.service;

import com.tpe.domain.Student;

import java.util.List;

public interface StudentService {
    void saveOrUpdateStudent(Student student);

    List<Student> getAllStudent();

    void deleteStudent(Long id);

    Student findStudentById(Long id);
}
