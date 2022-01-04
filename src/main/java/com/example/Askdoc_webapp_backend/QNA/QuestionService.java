package com.example.Askdoc_webapp_backend.QNA;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions(int limit) {
        return questionRepository.findAll().stream().limit(limit).toList();
    }

    public List<Question> getQuestionsByCategory(String specialization, int limit) {
        return questionRepository.findByCategory(specialization).stream().limit(limit).toList();
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByUser(Long userId) {
        return questionRepository.findByUserId(userId);
    }
}
