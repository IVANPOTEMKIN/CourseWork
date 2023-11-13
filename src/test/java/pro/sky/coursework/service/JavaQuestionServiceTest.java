package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework.repository.QuestionRepository;
import pro.sky.coursework.service.Impl.JavaQuestionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.coursework.utils.ExamplesForJavaTests.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository repository;
    @InjectMocks
    private JavaQuestionService service;

    @Test
    void add_1_success() {
        when(repository.add(EXAMPLE_1_JAVA)).thenReturn(EXAMPLE_1_JAVA);

        assertEquals(EXAMPLE_1_JAVA, service.add(EXAMPLE_1_JAVA.getQuestion(), EXAMPLE_1_JAVA.getAnswer()));
    }

    @Test
    void add_2_success() {
        when(repository.add(EXAMPLE_1_JAVA)).thenReturn(EXAMPLE_1_JAVA);

        assertEquals(EXAMPLE_1_JAVA, service.add(EXAMPLE_1_JAVA));
    }

    @Test
    void remove_success() {
        when(repository.remove(EXAMPLE_1_JAVA)).thenReturn(EXAMPLE_1_JAVA);

        assertEquals(EXAMPLE_1_JAVA, service.remove(EXAMPLE_1_JAVA));
    }

    @Test
    void getAll() {
        when(repository.getAll()).thenReturn(getCollectionJava());

        assertTrue(getCollectionJava().containsAll(service.getAll()));
    }

    @Test
    void getRandomQuestion() {
        when(repository.getAll()).thenReturn(getCollectionJava());

        assertTrue(getCollectionJava().contains(service.getRandomQuestion()));
    }
}