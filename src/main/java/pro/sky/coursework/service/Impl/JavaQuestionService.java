package pro.sky.coursework.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionInvalideException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Random random = new Random();
    private final Set<Question> questions = new HashSet<>();
    private final ValidationCheckServiceImpl validation;

    public JavaQuestionService(ValidationCheckServiceImpl validation) {
        this.validation = validation;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (validation.validate(question)) {
            questions.add(question);
            return question;
        }
        throw new QuestionInvalideException();
    }

    @Override
    public Question remove(Question question) {
        if (validation.validate(question)) {
            if (questions.contains(question)) {
                questions.remove(question);
                return question;
            }
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> list = List.copyOf(questions);
        int value = random.nextInt(getAll().size());
        return list.get(value);
    }
}
