package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Override
    public void saveOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repository.getAll();
    }

    @Override
    public void deleteStudent(Long id) {
        //idsi verilen studentı bulalım
        Student student=findStudentById(id);
        repository.delete(student);
    }

    @Override
    public Student findStudentById(Long id) {

        Student student=repository.findById(id).
                orElseThrow(()->new StudentNotFoundException("Student is not found by id: "+id));
        //findById metodunun döndürüdüğü optional içinde student varsa
        //değişkene atar, fakat boş bir optional dönerse orElseThrow ile exception fırlatılır.

        return student;
    }
}