package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
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
        Question actual = questionService.add(EXAMPLE_0.getQuestion(), EXAMPLE_0.getAnswer());

        assertEquals(EXAMPLE_0, actual);
    }

    @Test
    void add_2_success() {
        Question actual = questionService.add(EXAMPLE_0);

        assertEquals(EXAMPLE_0, actual);
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
                () -> questionService.add(null, EXAMPLE_1.getAnswer())
        );

        Exception actual_3 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(EXAMPLE_1.getQuestion(), null)
        );


        Exception actual_4 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(" ", EXAMPLE_1.getAnswer())
        );

        Exception actual_5 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.add(EXAMPLE_1.getQuestion(), " ")
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
    void add_2_QuestionAlreadyAddedException() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Данный вопрос уже был добавлен!</b>";

        Exception actual_1 = assertThrows(
                QuestionAlreadyAddedException.class,
                () -> questionService.add(EXAMPLE_1)
        );

        Exception actual_2 = assertThrows(
                QuestionAlreadyAddedException.class,
                () -> questionService.add(EXAMPLE_1.getQuestion(), "0")
        );

        assertEquals(expected, actual_1.getMessage());
        assertEquals(expected, actual_2.getMessage());
    }

    @Test
    void remove_success() {
        Question actual = questionService.remove(EXAMPLE_1);

        assertEquals(EXAMPLE_1, actual);
    }

    @Test
    void remove_QuestionNotFoundException() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String expected = status.value() + " <b>Данный вопрос не найден!</b>";

        Question question = questionService.remove(EXAMPLE_1);

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
                () -> questionService.remove(new Question(null, EXAMPLE_1.getAnswer()))
        );

        Exception actual_3 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(EXAMPLE_1.getQuestion(), null))
        );


        Exception actual_4 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(" ", EXAMPLE_1.getAnswer()))
        );

        Exception actual_5 = assertThrows(
                QuestionInvalideException.class,
                () -> questionService.remove(new Question(EXAMPLE_1.getQuestion(), " "))
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
    void getAll_success() {
        Collection<Question> actual = questionService.getAll();

        assertTrue(getCollection().containsAll(actual));
    }

    @Test
    void getRandomQuestion_success() {
        Question actual = questionService.getRandomQuestion();

        assertTrue(getCollection().contains(actual));
    }
}