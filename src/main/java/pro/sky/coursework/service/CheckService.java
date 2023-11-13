package pro.sky.coursework.service;

import pro.sky.coursework.domain.Question;

import java.util.Collection;

public interface CheckService {
    boolean validateCheck(Question question);

    boolean isQuestionAlreadyAdded(Collection<Question> questions, Question question);

    boolean isNotQuestionContains(Collection<Question> questions, Question question);
}