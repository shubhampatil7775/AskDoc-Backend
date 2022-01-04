package com.example.Askdoc_webapp_backend.User;

import com.example.Askdoc_webapp_backend.QNA.Question;
import com.example.Askdoc_webapp_backend.QNA.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private final QuestionService questionService;


    public UserController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PostMapping("/{userId}/ask-question")
    public ResponseEntity<Question> askQuestion(
            @PathVariable Long userId,
            @RequestBody Question question) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        question.setAnswered(false);
        question.setUser(user);
        Question savedQuestion = questionService.createQuestion(question);

        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping("/{userId}/questions")
    public ResponseEntity<List<Question>> getQuestionsByUser(@PathVariable Long userId) {
        List<Question> questions = questionService.getQuestionsByUser(userId);

        return ResponseEntity.ok(questions);
    }
}

