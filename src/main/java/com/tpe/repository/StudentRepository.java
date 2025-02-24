package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void saveOrUpdate(Student student);

    void save(Student student);

    List<Student> getAll();

    Optional<Student> findById(Long id);//NullPointerException almamak için
    //null yerine boş optional objesi döner.
    void delete(Student student);



}