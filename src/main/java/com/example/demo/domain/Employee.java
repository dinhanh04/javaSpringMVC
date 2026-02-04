package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Tạo bảng riêng cho Dev, Tester, Manager
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String employeeCode;

    @NotBlank
    private String fullName;

    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate birthDate;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal baseSalary;

    private Long departmentId;

    public abstract BigDecimal calculateSalary();
}