package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.dto.StudentDTO;
import com.example.demo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final  StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(StudentDTO studentDTO) {
        Student saveSt = new Student();
        saveSt.setName(studentDTO.getName());
        saveSt.setEmail(studentDTO.getEmail());
        saveSt.setScore(studentDTO.getScore());
        saveSt.setClassName(studentDTO.getClassName());
        saveSt.setDateOfBirth(studentDTO.getDateOfBirth());
        return this.studentRepository.save(saveSt);
    }

    public Student updateStudent(int id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setScore(studentDTO.getScore());
        existingStudent.setClassName(studentDTO.getClassName());
        existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());

        return studentRepository.save(existingStudent);
    }

    public ResponseEntity<String> deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully.");
    }

    public ResponseEntity<Student> getStudentById(int id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return  ResponseEntity.ok(student);
    }

    public List<Student> getAllStudents() {
List<Student> students =this.studentRepository.findAll();
        return students;  }
}
