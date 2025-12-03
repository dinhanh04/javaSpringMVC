package com.example.demo.repository;

import com.example.demo.domain.CustomerCTV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCTVRepository extends JpaRepository<CustomerCTV, Integer> {
    Optional<CustomerCTV> findByEmail(String email);
}



