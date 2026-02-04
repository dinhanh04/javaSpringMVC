package com.example.demo.controller;

import com.example.demo.domain.Question;
import com.example.demo.domain.Quiz;
import com.example.demo.service.QuizService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@Data
public class QuizController {
    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numberOfQuestions, @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<Question>> getQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }
}
