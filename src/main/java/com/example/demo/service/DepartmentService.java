package com.example.demo.service;

import com.example.demo.domain.Department;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        this.departmentRepository.save(department);
        return department;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = this.departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setName(department.getName());
            departmentDTO.setDescription(department.getDescription());
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = this.departmentRepository.findById(id);
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        return departmentDTO;
    }
}
