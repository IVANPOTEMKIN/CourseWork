package pro.sky.coursework.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaController {

    private final QuestionService questionService;

    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ExceptionHandler
    public String handleException(HttpStatusCodeException e) {
        return String.format("Code: %S. Error: %S", e.getStatusCode(), e.getMessage());
    }

    @GetMapping
    public Collection<Question> getQuestion() {
        return questionService.getAll();
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }
}