package pro.sky.coursework.utils;

import pro.sky.coursework.domain.Question;

import java.util.Collection;
import java.util.HashSet;

public class ExamplesForJavaTests {
    public final static Collection<Question> COLLECTION = new HashSet<>();
    public static final Question EXAMPLE_0_JAVA = new Question("java 0*0", "0");
    public static final Question EXAMPLE_1_JAVA = new Question("java 1*1", "1");

    public static Collection<Question> getCollectionJava() {
        COLLECTION.add(EXAMPLE_1_JAVA);
        COLLECTION.add(new Question("java 2*2", "4"));
        COLLECTION.add(new Question("java 3*3", "9"));
        COLLECTION.add(new Question("java 4*4", "16"));
        COLLECTION.add(new Question("java 5*5", "25"));
        return COLLECTION;
    }
}