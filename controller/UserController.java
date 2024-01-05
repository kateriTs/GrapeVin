package gf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser() {
        // Υλοποίηση αποθήκευσης νέου χρήστη
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser() {
        // Υλοποίηση σύνδεσης χρήστη
        return "redirect:/my-page";
    }
}
