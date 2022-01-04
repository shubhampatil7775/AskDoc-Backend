package com.example.Askdoc_webapp_backend.QNA;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);

    List<Question> findByUserId(Long userId);
}

