package com.example.Askdoc_webapp_backend.QNA;

import com.example.Askdoc_webapp_backend.Doctor.Doctor;
import com.example.Askdoc_webapp_backend.Doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer addAnswer(Long questionId, Long doctorId, String answerText) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + questionId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorId));

        Answer answer = new Answer(doctor,answerText);
        answer.setQuestion(question);

        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}
