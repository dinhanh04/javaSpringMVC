package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Developer extends Employee {
    private BigDecimal bonusProject;

    @Override
    public BigDecimal calculateSalary() {
        return getBaseSalary().add(bonusProject != null ? bonusProject : BigDecimal.ZERO);
    }
}
