package pro.sky.coursework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends HttpStatusCodeException {
    public QuestionNotFoundException() {
        super(HttpStatus.NOT_FOUND, "<b>Данный вопрос не найден!</b>");
    }
}