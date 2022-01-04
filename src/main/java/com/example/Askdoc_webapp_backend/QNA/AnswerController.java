package com.example.Askdoc_webapp_backend.QNA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/{doctorId}/answer-question/{questionId}")
    public ResponseEntity<Answer> answerQuestion(
            @PathVariable Long doctorId,
            @PathVariable Long questionId,
            @RequestBody String answerText) {

        Answer answer = answerService.addAnswer(questionId, doctorId, answerText);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestion(@PathVariable Long questionId) {
        List<Answer> answers = answerService.getAnswersByQuestion(questionId);
        return ResponseEntity.ok(answers);
    }
}
