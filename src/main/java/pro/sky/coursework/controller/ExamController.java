package pro.sky.coursework.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @ExceptionHandler
    public String handleException(HttpStatusCodeException e) {
        return String.format("Code: %S. Error: %S", e.getStatusCode(), e.getMessage());
    }

    @GetMapping("/get")
    public Collection<Question> getQuestion(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }
}