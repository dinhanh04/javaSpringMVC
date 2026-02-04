package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Tester extends Employee {
    private Integer bugBonus;

    @Override
    public BigDecimal calculateSalary() {
        BigDecimal totalBugBonus = BigDecimal.valueOf(bugBonus != null ? bugBonus : 0)
                .multiply(new BigDecimal("500000"));
        return getBaseSalary().add(totalBugBonus);
    }
}