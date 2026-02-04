package com.example.demo.controller;

import com.example.demo.domain.Department;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentRepository departmentRepository, DepartmentService departmentService) {
        this.departmentRepository = departmentRepository;
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = this.departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = this.departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = this.departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
}
