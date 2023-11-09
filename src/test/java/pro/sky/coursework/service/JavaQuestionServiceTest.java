package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
import pro.sky.coursework.exception.QuestionInvalideException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.Impl.JavaQuestionService;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pro.sky.coursework.service.utils.QuestionExamples.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private ValidationCheckService validationCheckService;
    @InjectMocks
    private JavaQuestionService questionService;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(EXAMPLE_1),
                Arguments.of(new Question(EXAMPLE_1.getQuestion(), "0"))
        );
    }

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

    @Test()
    void add_2_QuestionInvalideException() {
        when(validationCheckService.validate(any())).thenThrow(QuestionInvalideException.class);

        assertThrows(QuestionInvalideException.class, () -> questionService.add(EXAMPLE_1));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void add_2_QuestionAlreadyAddedException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Данный вопрос уже был добавлен!</b>";

        Exception actual = assertThrows(
                QuestionAlreadyAddedException.class,
                () -> questionService.add(question)
        );

        assertEquals(expected, actual.getMessage());
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
        when(validationCheckService.validate(any())).thenThrow(QuestionInvalideException.class);

        assertThrows(QuestionInvalideException.class, () -> questionService.remove(EXAMPLE_1));
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