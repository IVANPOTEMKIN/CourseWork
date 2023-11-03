package pro.sky.coursework.service;

import pro.sky.coursework.domain.Question;

public interface ValidationCheckService {
    boolean validate(Question question);
}