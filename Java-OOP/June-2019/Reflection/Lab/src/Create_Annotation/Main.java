package Create_Annotation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Class testClass = TestClass.class;
        Subject annotation = (Subject)testClass.getAnnotation(Subject.class);
        Arrays.stream(annotation.categories()).forEach(System.out::println);
    }
}
