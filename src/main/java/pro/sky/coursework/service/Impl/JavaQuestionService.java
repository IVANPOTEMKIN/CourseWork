package pro.sky.coursework.service.Impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.repository.QuestionRepository;
import pro.sky.coursework.service.QuestionService;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service("javaService")
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository repository;
    private final Random random;

    public JavaQuestionService(@Qualifier("javaRep") QuestionRepository repository) {
        this.repository = repository;
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        repository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        repository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> collection = List.copyOf(repository.getAll());
        int value = random.nextInt(getAll().size());
        return collection.get(value);
    }
}