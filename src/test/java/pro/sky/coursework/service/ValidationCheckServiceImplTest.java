package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionInvalideException;
import pro.sky.coursework.service.Impl.ValidationCheckServiceImpl;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework.service.utils.QuestionExamples.*;

class ValidationCheckServiceImplTest {

    private final ValidationCheckService service = new ValidationCheckServiceImpl();

    public static Stream<Arguments> provideParamsForTests() {
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
    void validate_success() {
        boolean actual = service.validate(EXAMPLE_1);
        assertTrue(actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void validate_QuestionInvalideException(Question question) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Некорректный запрос!</b>";

        Exception actual = assertThrows(
                QuestionInvalideException.class,
                () -> service.validate(question)
        );

        assertEquals(expected, actual.getMessage());
    }
}