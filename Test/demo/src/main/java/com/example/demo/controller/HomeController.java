package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Student;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index() {
        return "index"; // trả về index.html trong templates
    }

    @GetMapping("/demo")
    public String demoPage(Model model) {
        Student student = new Student(1, "Nguyễn Văn A");
        model.addAttribute("student", student);
        model.addAttribute("message", "Welcome Thymeleaf!");
        return "demo";
    }
}
