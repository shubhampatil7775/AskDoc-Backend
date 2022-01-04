package com.example.Askdoc_webapp_backend.QNA;

import com.example.Askdoc_webapp_backend.User.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String category;
    private Boolean answered;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAnswered() {
        return answered;
    }

}
