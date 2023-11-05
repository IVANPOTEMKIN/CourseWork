package pro.sky.coursework.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionLimitException;
import pro.sky.coursework.service.ExaminerService;
import pro.sky.coursework.service.QuestionService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService service;

    public ExaminerServiceImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int size = service.getAll().size();

        if (size < amount) {
            throw new QuestionLimitException();
        }

        Set<Question> questions = new HashSet<>();

        while (questions.size() < amount) {
            questions.add(service.getRandomQuestion());
        }
        return Collections.unmodifiableCollection(questions);
    }
}