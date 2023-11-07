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
import static pro.sky.coursework.service.utils.QuestionExamples.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private ValidationCheckService validationService;
    @InjectMocks
    private JavaQuestionService questionService;

    public static Stream<Arguments> provideParamsForTests_1() {
        return Stream.of(
                Arguments.of(EXAMPLE_1),
                Arguments.of(new Question(EXAMPLE_1.getQuestion(), "0"))
        );
    }

    public static Stream<Arguments> provideParamsForTests_2() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(new Question(null, EXAMPLE_1.getAnswer())),
                Arguments.of(new Question(EXAMPLE_1.getQuestion(), null)),
                Arguments.of(new Question(" ", EXAMPLE_1.getAnswer())),
                Arguments.of(new Question(EXAMPLE_1.getQuestion(), " ")),
                Arguments.of(new Question("1", "1"))
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

    @ParameterizedTest
    @MethodSource("provideParamsForTests_2")
    void add_2_QuestionInvalideException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual = assertThrows(
                QuestionInvalideException.class,
                () -> validationService.validate(questionService.add(question))
        );

        assertEquals(expected, actual.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests_1")
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

    @ParameterizedTest
    @MethodSource("provideParamsForTests_2")
    void remove_QuestionInvalideException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual = assertThrows(
                QuestionInvalideException.class,
                () -> validationService.validate(questionService.remove(question))
        );

        assertEquals(expected, actual.getMessage());
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