package pro.sky.coursework.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {

    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @ExceptionHandler
    public String handleException(HttpStatusCodeException e) {
        return String.format("Code: %S. Error: %S", e.getStatusCode(), e.getMessage());
    }

    @GetMapping
    public Collection<Question> getQuestion(@RequestParam int amount) {
        return service.getQuestions(amount);
    }
}