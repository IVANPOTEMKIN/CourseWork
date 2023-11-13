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
import pro.sky.coursework.repository.Impl.JavaQuestionRepository;
import pro.sky.coursework.service.CheckService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pro.sky.coursework.utils.ExamplesForJavaTests.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionRepositoryTest {

    @Mock
    private CheckService service;
    @InjectMocks
    private JavaQuestionRepository repository;

    @Test
    public void init_success() {
        Collection<Question> actual = repository.getAll();

        assertTrue(getCollectionJava().containsAll(actual));
    }

    @Test
    public void add_success() {
        Question actual = repository.add(EXAMPLE_0_JAVA);

        assertEquals(EXAMPLE_0_JAVA, actual);
    }

    @Test
    public void add_QuestionInvalideException() {
        when(service.validateCheck(any())).thenThrow(QuestionInvalideInputException.class);

        assertThrows(QuestionInvalideInputException.class, () -> repository.add(EXAMPLE_1_JAVA));
    }

    @Test
    public void add_QuestionAlreadyAddedException() {
        when(service.isQuestionAlreadyAdded(any(), any())).thenThrow(QuestionAlreadyAddedException.class);

        assertThrows(QuestionAlreadyAddedException.class, () -> repository.add(EXAMPLE_1_JAVA));
    }

    @Test
    public void remove_success() {
        Question actual = repository.remove(EXAMPLE_1_JAVA);

        assertEquals(EXAMPLE_1_JAVA, actual);
    }

    @Test
    public void remove_QuestionInvalideException() {
        when(service.validateCheck(any())).thenThrow(QuestionInvalideInputException.class);

        assertThrows(QuestionInvalideInputException.class, () -> repository.remove(EXAMPLE_1_JAVA));
    }

    @Test
    void remove_QuestionNotFoundException() {
        when(service.isNotQuestionContains(any(), any())).thenThrow(QuestionNotFoundException.class);

        assertThrows(QuestionNotFoundException.class, () -> repository.remove(EXAMPLE_1_JAVA));
    }

    @Test
    public void getAll_success() {
        Collection<Question> actual = repository.getAll();

        assertTrue(getCollectionJava().containsAll(actual));
    }
}