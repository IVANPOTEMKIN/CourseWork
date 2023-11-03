package pro.sky.coursework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionLimitException extends HttpStatusCodeException {

    public QuestionLimitException() {
        super(HttpStatus.BAD_REQUEST, "<b>Запрошено большее количество вопросов, чем хранится в сервисе!</b>");
    }
}