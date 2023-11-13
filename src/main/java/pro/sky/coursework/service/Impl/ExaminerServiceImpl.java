package pro.sky.coursework.service.Impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionLimitException;
import pro.sky.coursework.service.ExaminerService;
import pro.sky.coursework.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random;

    public ExaminerServiceImpl(@Qualifier("javaService") QuestionService javaQuestionService,
                               @Qualifier("mathService") QuestionService mathQuestionService
    ) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int size = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();

        if (size >= amount) {
            Collection<Question> questions = new HashSet<>();

            while (questions.size() < amount) {

                if (random.nextBoolean()) {
                    questions.add(javaQuestionService.getRandomQuestion());
                } else {
                    questions.add(mathQuestionService.getRandomQuestion());
                }
            }
            return Collections.unmodifiableCollection(questions);
        }
        throw new QuestionLimitException();
    }
}