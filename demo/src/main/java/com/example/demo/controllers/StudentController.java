package com.example.demo.controllers;

import com.example.demo.data.Student;
import com.example.demo.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public List<Student> showStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @GetMapping("/student/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/add")
    public String addStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/student";
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Integer mark) {
        studentService.updateStudent(id, mark);
    }
}
