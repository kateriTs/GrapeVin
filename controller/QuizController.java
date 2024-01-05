package gf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @PostMapping("/submit")
    public String submitQuiz() {
        // Υλοποίηση αποθήκευσης απαντήσεων
        return "QuizResults";
    }
}

