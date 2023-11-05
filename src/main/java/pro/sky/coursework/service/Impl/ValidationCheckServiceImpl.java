package pro.sky.coursework.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework.domain.Question;
import pro.sky.coursework.service.ValidationCheckService;

@Service
public class ValidationCheckServiceImpl implements ValidationCheckService {

    @Override
    public boolean validate(Question question) {
        return question != null
                && question.getQuestion() != null
                && question.getAnswer() != null
                && !question.getQuestion().isBlank()
                && !question.getAnswer().isBlank()
                && !question.getQuestion().equals(question.getAnswer());
    }
}