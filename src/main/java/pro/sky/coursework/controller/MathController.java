package pro.sky.coursework.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/math")
public class MathController {

    private final QuestionService service;

    public MathController(@Qualifier("mathService") QuestionService service) {
        this.service = service;
    }

    @ExceptionHandler
    private String handleException(HttpStatusCodeException e) {
        return String.format("Code: <b>%S</b>. Error: %S", e.getStatusCode(), e.getStatusText());
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}