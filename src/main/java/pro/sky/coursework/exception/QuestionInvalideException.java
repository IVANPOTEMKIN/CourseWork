package pro.sky.coursework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionInvalideException extends HttpStatusCodeException {

    public QuestionInvalideException() {
        super(HttpStatus.BAD_REQUEST, "<b>Некорректный запрос!</b>");
    }
}