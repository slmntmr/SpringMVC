package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
@Repository//componentın db ye erişimi olan classlar için özelleşmiş halidir
public class StudentRepositoryImpl implements StudentRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Student student) {
        Session session =sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        session.saveOrUpdate(student);//db de varsa update eder, yeni objeyi insert eder

        tx.commit();
        session.close();
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public List<Student> getAll() {
        Session session =sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        List<Student> allStudents=session.createQuery("FROM Student",Student.class).getResultList();

        tx.commit();
        session.close();

        return allStudents;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session =sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        Student student=session.get(Student.class,id);//id:1,2,3,4,5-->student=std12345
        //id:6---->student=null-->student.getFirstName();-->NullPointerExcp.

        Optional<Student> optional=Optional.ofNullable(student);

        tx.commit();
        session.close();

        return optional;
    }

    @Override
    public void delete(Student student) {
        Session session =sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        session.delete(student);

        tx.commit();
        session.close();

    }
}