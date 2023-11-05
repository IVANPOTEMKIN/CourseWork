package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionInvalideException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.Impl.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework.service.utils.QuestionExamples.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService questionService = new JavaQuestionService();

    @Test
    void add_1_success() {
        Question actual = questionService.add(EXAMPLE.getQuestion(), EXAMPLE.getAnswer());

        assertEquals(EXAMPLE, actual);
    }

    @Test
    void add_2_success() {
        Question actual = questionService.add(EXAMPLE);

        assertEquals(EXAMPLE, actual);
    }

    @Test
    void add_2_QuestionInvalideException() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual_1 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(null)
        );

        Exception actual_2 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(null, EXAMPLE.getAnswer())
        );

        Exception actual_3 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(EXAMPLE.getQuestion(), null)
        );


        Exception actual_4 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(" ", EXAMPLE.getAnswer())
        );

        Exception actual_5 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(EXAMPLE.getQuestion(), " ")
        );

        Exception actual_6 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add("1", "1")
        );

        assertEquals(expected, actual_1.getMessage());
        assertEquals(expected, actual_2.getMessage());
        assertEquals(expected, actual_3.getMessage());
        assertEquals(expected, actual_4.getMessage());
        assertEquals(expected, actual_5.getMessage());
        assertEquals(expected, actual_6.getMessage());
    }

    @Test
    void remove() {
        Question actual = questionService.remove(EXAMPLE);

        assertEquals(EXAMPLE, actual);
    }

    @Test
    void remove_QuestionNotFoundException() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String expected = status.value() + " <b>Данный вопрос не найден!</b>";

        Question question = questionService.remove(EXAMPLE);

        Exception actual = assertThrows(
                QuestionNotFoundException.class,
                () -> questionService.remove(question)
        );

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void remove_QuestionInvalideException() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual_1 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(null)
        );

        Exception actual_2 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(null, EXAMPLE.getAnswer()))
        );

        Exception actual_3 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(EXAMPLE.getQuestion(), null))
        );


        Exception actual_4 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(" ", EXAMPLE.getAnswer()))
        );

        Exception actual_5 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(EXAMPLE.getQuestion(), " "))
        );

        Exception actual_6 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question("1", "1"))
        );

        assertEquals(expected, actual_1.getMessage());
        assertEquals(expected, actual_2.getMessage());
        assertEquals(expected, actual_3.getMessage());
        assertEquals(expected, actual_4.getMessage());
        assertEquals(expected, actual_5.getMessage());
        assertEquals(expected, actual_6.getMessage());
    }

    @Test
    void getAll() {
        Collection<Question> actual = questionService.getAll();

        assertTrue(getCollection().containsAll(actual));
    }

    @Test
    void getRandomQuestion() {
        Question actual = questionService.getRandomQuestion();

        assertTrue(getCollection().contains(actual));
    }
}