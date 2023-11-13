package pro.sky.coursework.utils;

import pro.sky.coursework.domain.Question;

import java.util.Collection;
import java.util.HashSet;

public class ExamplesForMathTests {
    public final static Collection<Question> COLLECTION = new HashSet<>();
    public static final Question EXAMPLE_0_MATH = new Question("math 0*0", "0");
    public static final Question EXAMPLE_1_MATH = new Question("math 1*1", "1");

    public static Collection<Question> getCollectionMath() {
        COLLECTION.add(EXAMPLE_1_MATH);
        COLLECTION.add(new Question("math 2*2", "4"));
        COLLECTION.add(new Question("math 3*3", "9"));
        COLLECTION.add(new Question("math 4*4", "16"));
        COLLECTION.add(new Question("math 5*5", "25"));
        return COLLECTION;
    }
}