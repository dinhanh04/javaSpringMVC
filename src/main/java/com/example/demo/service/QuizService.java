package com.example.demo.service;

import com.example.demo.domain.Question;
import com.example.demo.domain.Quiz;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuizRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;


    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
        List<Question> questions = questionRepository.findByCategoryLimit(category, numberOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return ResponseEntity.ok("Quiz created with ID: ");

    }

    public ResponseEntity<List<Question>> getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = new ArrayList<>();
        for (Question question : quiz.get().getQuestions()) {
            questions.add(question);
        }
        return ResponseEntity.ok(questions);

    }
}
