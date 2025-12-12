package com.example.demo.repository;


import com.example.demo.domain.Card;
import com.example.demo.domain.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardDetail,Long> {
}
