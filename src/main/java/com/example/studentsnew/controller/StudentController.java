package com.example.studentsnew.controller;

import com.example.studentsnew.model.Student;
import com.example.studentsnew.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //получаем всех студентов
    @GetMapping
    public Model getAllStudents(Model model) {
        return model.addAttribute("students", studentService.getAll());

    }

    //удаляем студента по кнопке
    @GetMapping("/delete")
    public RedirectView delete(@RequestParam(required = false) Map<String, String> requestParams) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/students");
        int valueID = Integer.parseInt(requestParams.get("id"));
        studentService.deleteStudents(valueID);
        return redirectView;
    }

    //удаляем по id
    @GetMapping("/delete/{id}")
    public RedirectView getDeleteById(@PathVariable int id) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/students");
        studentService.deleteStudents(id);
        return redirectView;
    }

    //создаем модель по id
    @ModelAttribute("student")
    public Student defaultInstance() {
        return new Student();
    }

    //получаем студента из формы для добавления в таблицу
    @PostMapping("")
    public Model addStudentsFrom(@ModelAttribute("student") Student student, Model model) {
        studentService.addStudent(student);
        return model.addAttribute("students", studentService.getAll());
    }

    //апгрейт полей
    @GetMapping("/update")
    public RedirectView UpdateStudent(@RequestParam(required = false) Map<String, String> requestParams) {
        RedirectView redirectView = new RedirectView();
        studentService.updateStudent(requestParams);
        redirectView.setUrl("http://localhost:8080/students");
        return redirectView;
    }


}
