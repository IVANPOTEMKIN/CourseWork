package pro.sky.coursework.service.utils;

import pro.sky.coursework.domain.Question;

import java.util.Collection;
import java.util.HashSet;

public class QuestionExamples {

    public final static Collection<Question> COLLECTION = new HashSet<>();
    public static final Question EXAMPLE = new Question("1*1", "1");

    public static Collection<Question> getCollection() {
        COLLECTION.add(EXAMPLE);
        COLLECTION.add(new Question("2*2", "4"));
        COLLECTION.add(new Question("3*3", "9"));
        COLLECTION.add(new Question("4*4", "16"));
        COLLECTION.add(new Question("5*5", "25"));
        return COLLECTION;
    }
}