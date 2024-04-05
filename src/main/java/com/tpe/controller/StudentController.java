package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//requestler burada karşılanıp ilgili metodlarla eşleştirilecek
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/.....
//class:tüm metodlar için geçerlidir.
//method:sadece ilgili metod için geçerli olur.
public class StudentController {

    @Autowired
    private StudentService service;


    //controller---> ModelAndView objesi veya sadece String data tipi döndürülebilir.

    //http:localhost:8080/SpringMvc/students/hi + GET
    //@RequestMapping("/students")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi, ");
        mav.addObject("messagebody","I'm a Student Management System App");
        mav.setViewName("hi");
        return mav;
    }

    //view resolver : /WEB-INF/views/hi.jsp dosyasını bulur ve içine mav içindeki datayı bind eder.
    //ve sonucu istemciye gönderir.


    //1-tüm öğrencileri listeleme
    //http://localhost:8080/SpringMvc/students + GET
    @GetMapping
    public ModelAndView listStudents(){

        List<Student> allStudent=service.getAllStudent();
        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudent);
        mav.setViewName("students");//WEB-INF/views/students.jsp
        return mav;
    }

    //2-öğrenci ekleme
    //http://localhost:8080/SpringMvc/students/new + GET
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student){
        return "studentForm";
    }

    //ModelAttribute annatasyonu view katmanı ile controller arasında
    //model ın transferini sağlar.


    //2-a-http://localhost:8080/SpringMvc/students/saveStudent + POST
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "studentForm";
        }

        service.saveOrUpdateStudent(student);
        return "redirect:/students";
    }

    //3-öğrenciyi güncelleme
    //http://localhost:8080/SpringMvc/students/update?id=1 + GET
    @GetMapping("/update")
    public ModelAndView sendFormForUpdate(@RequestParam("id") Long identity){
        Student foundStudent=service.findStudentById(identity);

        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }

    //4-öğrenciyi silme
    //http://localhost:8080/SpringMvc/students/delete/1 + GET
    @GetMapping("/delete/{stdid}")
    public String deleteStudent(@PathVariable("stdid") Long identity){

        service.deleteStudent(identity);
        return "redirect:/students";
    }
    //redirect: Spring MVC tarafından gelen request "redirect: /path" ile
    //farklı bir requeste(/path url ile ) yeniden yönlendirilir.

    //@ExceptionHandler:try-catch bloğunun mantığıyla çalışır.
    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }




}