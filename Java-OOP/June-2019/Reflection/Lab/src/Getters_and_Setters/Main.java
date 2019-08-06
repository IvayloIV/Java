package Getters_and_Setters;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Class reflection = Reflection.class;
        Method[] methods = reflection.getDeclaredMethods();

        Arrays.sort(methods, (a, b) -> {
            return a.getName().compareTo(b.getName());
        });
        Arrays.stream(methods)
                .filter(a -> a.getName().startsWith("get"))
                .forEach(a -> System.out.println(String.format("%s will return class %s",
                        a.getName(),
                        a.getReturnType().getName())));

        Arrays.stream(methods)
                .filter(a -> a.getName().startsWith("set"))
                .forEach(a -> System.out.println(String.format("%s and will set field of class %s",
                        a.getName(),
                        a.getParameterTypes()[0].getName())));
    }
}
