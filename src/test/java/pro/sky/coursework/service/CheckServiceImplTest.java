package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
import pro.sky.coursework.exception.QuestionInvalideInputException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.Impl.CheckServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework.utils.ExamplesForMathTests.*;
import static pro.sky.coursework.utils.ExamplesForJavaTests.*;

class CheckServiceImplTest {

    private final CheckService service = new CheckServiceImpl();

    public static Stream<Arguments> provideParamsForTests_1() {
        return Stream.of(
                Arguments.of(new Question(null, EXAMPLE_1_JAVA.getAnswer())),
                Arguments.of(new Question(EXAMPLE_1_JAVA.getQuestion(), null)),
                Arguments.of(new Question(" ", EXAMPLE_1_JAVA.getAnswer())),
                Arguments.of(new Question(EXAMPLE_1_JAVA.getQuestion(), " ")),
                Arguments.of(new Question("1", "1"))
        );
    }

    public static Stream<Arguments> provideParamsForTests_2() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_MATH),
                Arguments.of(new Question(EXAMPLE_1_MATH.getQuestion(), "0"))
        );
    }

    @Test
    void validateCheck_success() {
        assertFalse(service.validateCheck(EXAMPLE_1_JAVA));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests_1")
    void validate_QuestionInvalideException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual = assertThrows(
                QuestionInvalideInputException.class,
                () -> service.validateCheck(question)
        );

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void isQuestionAlreadyAdded_success() {
        assertFalse(service.isQuestionAlreadyAdded(getCollectionMath(), EXAMPLE_0_MATH));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests_2")
    void isQuestionAlreadyAdded_QuestionAlreadyAddedException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Данный вопрос уже был добавлен!</b>";

        Exception actual = assertThrows(
                QuestionAlreadyAddedException.class,
                () -> service.isQuestionAlreadyAdded(getCollectionMath(), question)
        );

        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void isNotQuestionContains_success() {
        assertFalse(service.isNotQuestionContains(getCollectionMath(), EXAMPLE_1_MATH));
    }

    @Test
    public void isNotQuestionContains_QuestionNotFoundException() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String expected = status.value() + " <b>Данный вопрос не найден!</b>";

        Exception actual = assertThrows(
                QuestionNotFoundException.class,
                () -> service.isNotQuestionContains(getCollectionJava(), EXAMPLE_0_JAVA)
        );

        assertEquals(expected, actual.getMessage());
    }
}