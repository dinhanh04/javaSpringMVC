package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private double score;
    private String className;
    private LocalDate dateOfBirth;
}
