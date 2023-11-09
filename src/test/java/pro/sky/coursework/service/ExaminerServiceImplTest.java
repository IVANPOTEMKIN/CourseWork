package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionLimitException;
import pro.sky.coursework.service.Impl.ExaminerServiceImpl;
import pro.sky.coursework.service.Impl.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework.service.utils.QuestionExamples.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_success() {
        Collection<Question> actual = examinerService.getQuestions(questionService.getAll().size());

        assertTrue(getCollection().containsAll(actual));
    }

    @Test
    void getQuestions_QuestionLimitException() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String expected = status.value() + " <b>Запрошено большее количество вопросов, чем хранится в сервисе!</b>";

        Exception actual = assertThrows(
                QuestionLimitException.class,
                () -> examinerService.getQuestions(Integer.MAX_VALUE)
        );

        assertEquals(expected, actual.getMessage());
    }
}