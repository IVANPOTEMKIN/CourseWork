package pro.sky.coursework.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.QuestionService;
import pro.sky.coursework.service.ValidationCheckService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Random random;
    private final Set<Question> questions;
    private final ValidationCheckService validationCheckService;

    public JavaQuestionService(ValidationCheckService validationCheckService) {
        this.random = new Random();
        this.questions = new HashSet<>();
        this.validationCheckService = validationCheckService;

        Question question_1 = new Question("1*1", "1");
        Question question_2 = new Question("2*2", "4");
        Question question_3 = new Question("3*3", "9");
        Question question_4 = new Question("4*4", "16");
        Question question_5 = new Question("5*5", "25");

        questions.add(question_1);
        questions.add(question_2);
        questions.add(question_3);
        questions.add(question_4);
        questions.add(question_5);
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        validationCheckService.validate(question);

        if (!checkQuestionAdded(questions, question)) {
            questions.add(question);
            return question;
        }
        throw new QuestionAlreadyAddedException();
    }

    @Override
    public Question remove(Question question) {
        validationCheckService.validate(question);

        if (questions.contains(question)) {
            questions.remove(question);
            return question;
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

    private static boolean checkQuestionAdded(Collection<Question> collection, Question question) {
        for (Question el : collection) {
            if (collection.contains(question)
                    || el.getQuestion().equalsIgnoreCase(question.getQuestion()))
                return true;
        }
        return false;
    }
}