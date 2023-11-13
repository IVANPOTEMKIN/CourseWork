package pro.sky.coursework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework.repository.QuestionRepository;
import pro.sky.coursework.service.Impl.MathQuestionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.coursework.utils.ExamplesForMathTests.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository repository;
    @InjectMocks
    private MathQuestionService service;

    @Test
    void add_1_success() {
        when(repository.add(EXAMPLE_1_MATH)).thenReturn(EXAMPLE_1_MATH);

        assertEquals(EXAMPLE_1_MATH, service.add(EXAMPLE_1_MATH.getQuestion(), EXAMPLE_1_MATH.getAnswer()));
    }

    @Test
    void add_2_success() {
        when(repository.add(EXAMPLE_1_MATH)).thenReturn(EXAMPLE_1_MATH);

        assertEquals(EXAMPLE_1_MATH, service.add(EXAMPLE_1_MATH));
    }

    @Test
    void remove_success() {
        when(repository.remove(EXAMPLE_1_MATH)).thenReturn(EXAMPLE_1_MATH);

        assertEquals(EXAMPLE_1_MATH, service.remove(EXAMPLE_1_MATH));
    }

    @Test
    void getAll() {
        when(repository.getAll()).thenReturn(getCollectionMath());

        assertTrue(getCollectionMath().containsAll(service.getAll()));
    }

    @Test
    void getRandomQuestion() {
        when(repository.getAll()).thenReturn(getCollectionMath());

        assertTrue(getCollectionMath().contains(service.getRandomQuestion()));
    }
}