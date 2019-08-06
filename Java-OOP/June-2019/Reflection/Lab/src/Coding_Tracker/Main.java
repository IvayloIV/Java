package Coding_Tracker;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Class tracker = Tracker.class;
        Method method = tracker.getDeclaredMethod("printMethodsByAuthor");
        Author declaredAnnotations = method.getAnnotation(Author.class);
        System.out.println(declaredAnnotations.name());
    }
}
