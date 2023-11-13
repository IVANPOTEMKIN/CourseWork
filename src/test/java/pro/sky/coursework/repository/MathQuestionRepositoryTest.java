package pro.sky.coursework.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
import pro.sky.coursework.exception.QuestionInvalideInputException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.repository.Impl.MathQuestionRepository;
import pro.sky.coursework.service.CheckService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pro.sky.coursework.utils.ExamplesForMathTests.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionRepositoryTest {

    @Mock
    private CheckService service;
    @InjectMocks
    private MathQuestionRepository repository;

    @Test
    public void init_success() {
        Collection<Question> actual = repository.getAll();

        assertTrue(getCollectionMath().containsAll(actual));
    }

    @Test
    public void add_success() {
        Question actual = repository.add(EXAMPLE_0_MATH);

        assertEquals(EXAMPLE_0_MATH, actual);
    }

    @Test
    public void add_QuestionInvalideException() {
        when(service.validateCheck(any())).thenThrow(QuestionInvalideInputException.class);

        assertThrows(QuestionInvalideInputException.class, () -> repository.add(EXAMPLE_1_MATH));
    }

    @Test
    public void add_QuestionAlreadyAddedException() {
        when(service.isQuestionAlreadyAdded(any(), any())).thenThrow(QuestionAlreadyAddedException.class);

        assertThrows(QuestionAlreadyAddedException.class, () -> repository.add(EXAMPLE_1_MATH));
    }

    @Test
    public void remove_success() {
        Question actual = repository.remove(EXAMPLE_1_MATH);

        assertEquals(EXAMPLE_1_MATH, actual);
    }

    @Test
    public void remove_QuestionInvalideException() {
        when(service.validateCheck(any())).thenThrow(QuestionInvalideInputException.class);

        assertThrows(QuestionInvalideInputException.class, () -> repository.remove(EXAMPLE_1_MATH));
    }

    @Test
    void remove_QuestionNotFoundException() {
        when(service.isNotQuestionContains(any(), any())).thenThrow(QuestionNotFoundException.class);

        assertThrows(QuestionNotFoundException.class, () -> repository.remove(EXAMPLE_1_MATH));
    }

    @Test
    public void getAll_success() {
        Collection<Question> actual = repository.getAll();

        assertTrue(getCollectionMath().containsAll(actual));
    }
}