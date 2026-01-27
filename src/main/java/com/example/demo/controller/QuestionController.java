package com.example.demo.controller;


import com.example.demo.domain.Question;
import com.example.demo.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getall")
    public List<Question> getAllQuestions() {
        return questionService.getallQuestions();
    }

    @GetMapping("/category/{categoryName}")
    public List<Question> getQuestionsByCategory(@PathVariable String categoryName) {
        return questionService.getByCategory(categoryName);
    }

    @PostMapping("add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
