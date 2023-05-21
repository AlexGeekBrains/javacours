package com.example.demo.services;

import com.example.demo.data.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Long id, Integer mark) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setMark(mark);
        studentRepository.save(student);
    }
}
