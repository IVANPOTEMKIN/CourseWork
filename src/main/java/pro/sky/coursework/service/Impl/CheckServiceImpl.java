package pro.sky.coursework.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.exception.QuestionAlreadyAddedException;
import pro.sky.coursework.exception.QuestionInvalideInputException;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.CheckService;

import java.util.Collection;

@Service
public class CheckServiceImpl implements CheckService {
    @Override
    public boolean validateCheck(Question question) {
        if (question.getQuestion() == null
                || question.getAnswer() == null
                || question.getQuestion().isBlank()
                || question.getAnswer().isBlank()
                || question.getQuestion().equalsIgnoreCase(question.getAnswer()))
            throw new QuestionInvalideInputException();
        return false;
    }

    @Override
    public boolean isQuestionAlreadyAdded(Collection<Question> questions, Question question) {
        for (Question element : questions) {
            if (questions.contains(question)
                    || element.getQuestion().equalsIgnoreCase(question.getQuestion()))
                throw new QuestionAlreadyAddedException();
        }
        return false;
    }

    @Override
    public boolean isNotQuestionContains(Collection<Question> questions, Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        return false;
    }
}