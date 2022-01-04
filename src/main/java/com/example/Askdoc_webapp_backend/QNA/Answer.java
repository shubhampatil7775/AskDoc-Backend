package com.example.Askdoc_webapp_backend.QNA;

import com.example.Askdoc_webapp_backend.Doctor.Doctor;
import jakarta.persistence.*;


@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(length = 2000)
    private String answerText;

    public Answer(Doctor doctor, String answerText) {
        this.doctor = doctor;
        this.answerText = answerText;
    }

    public Answer() {

    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
