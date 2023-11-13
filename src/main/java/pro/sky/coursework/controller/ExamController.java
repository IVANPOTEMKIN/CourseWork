package pro.sky.coursework.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @ExceptionHandler
    private String handleException(HttpStatusCodeException e) {
        return String.format("Code: <b>%S</b>. Error: %S", e.getStatusCode(), e.getStatusText());
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return service.getQuestions(amount);
    }
}