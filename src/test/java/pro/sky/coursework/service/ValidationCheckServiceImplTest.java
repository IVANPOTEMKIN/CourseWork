package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.Impl.ValidationCheckServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework.service.utils.QuestionExamples.*;

class ValidationCheckServiceImplTest {

    private final ValidationCheckService service = new ValidationCheckServiceImpl();

    @Test
    void validate_success() {
        boolean expected = false;

        boolean actual_1 = service.validate(null);
        boolean actual_2 = service.validate(new Question(null, EXAMPLE.getAnswer()));
        boolean actual_3 = service.validate(new Question(EXAMPLE.getQuestion(), null));
        boolean actual_4 = service.validate(new Question(" ", EXAMPLE.getAnswer()));
        boolean actual_5 = service.validate(new Question(EXAMPLE.getQuestion(), " "));
        boolean actual_6 = service.validate(new Question("1", "1"));

        assertEquals(expected, actual_1);
        assertEquals(expected, actual_2);
        assertEquals(expected, actual_3);
        assertEquals(expected, actual_4);
        assertEquals(expected, actual_5);
        assertEquals(expected, actual_6);
    }
}