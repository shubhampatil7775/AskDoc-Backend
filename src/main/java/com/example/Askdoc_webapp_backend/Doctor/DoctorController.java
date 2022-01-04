package com.example.Askdoc_webapp_backend.Doctor;

import com.example.Askdoc_webapp_backend.QNA.Question;
import com.example.Askdoc_webapp_backend.QNA.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final QuestionService questionService;

    public DoctorController(DoctorService doctorService, QuestionService questionService) {
        this.doctorService = doctorService;
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{doctorId}/questions")
    public ResponseEntity<List<Question>> getAllQuestions(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorId));
        List<Question> questions = questionService.getAllQuestions(10);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{doctorId}/questions-by-specialization")
    public ResponseEntity<List<Question>> getQuestionsBySpecialization(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorId));

        List<Question> questions = questionService.getQuestionsByCategory(doctor.getSpecialization(),10);
        return ResponseEntity.ok(questions);
    }

}
