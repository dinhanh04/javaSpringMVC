package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.dto.StudentDTO;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("create" )
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        return this.studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/delete/{id}" )
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        return this.studentService.deleteStudent(id);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return this.studentService.getStudentById(id);
    }

    @GetMapping("/all" )
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
