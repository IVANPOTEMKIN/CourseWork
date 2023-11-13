package pro.sky.coursework.repository.Impl;

import org.springframework.stereotype.Repository;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.repository.QuestionRepository;
import pro.sky.coursework.service.CheckService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Repository("mathRep")
public class MathQuestionRepository implements QuestionRepository {

    private final Collection<Question> questions;
    private final CheckService checkService;

    public MathQuestionRepository(CheckService checkService) {
        this.questions = new HashSet<>();
        this.checkService = checkService;
    }

    @Override
    @PostConstruct
    public void init() {
        Question question_1 = new Question("java 1*1", "1");
        Question question_2 = new Question("java 2*2", "4");
        Question question_3 = new Question("java 3*3", "9");
        Question question_4 = new Question("java 4*4", "16");
        Question question_5 = new Question("java 5*5", "25");

        questions.add(question_1);
        questions.add(question_2);
        questions.add(question_3);
        questions.add(question_4);
        questions.add(question_5);
    }

    @Override
    public Question add(Question question) {
        checkService.validateCheck(question);
        checkService.isQuestionAlreadyAdded(questions, question);
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        checkService.validateCheck(question);
        checkService.isNotQuestionContains(questions, question);
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}