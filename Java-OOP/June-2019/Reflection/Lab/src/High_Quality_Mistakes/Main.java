package High_Quality_Mistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Class reflectionClass = Reflection.class;
        Field[] fields = reflectionClass.getDeclaredFields();

        Arrays.stream(fields).sorted((a, b) -> a.getName().compareTo(b.getName()))
                .filter(a -> !Modifier.isPrivate(a.getModifiers()))
                .forEach(a -> System.out.println(a.getName() + " must be private!"));

        Method[] methods = reflectionClass.getDeclaredMethods();

        Arrays.sort(methods, (a, b) -> {
            return a.getName().compareTo(b.getName());
        });
        Arrays.stream(methods)
                .filter(a -> a.getName().startsWith("get") && !Modifier.isPublic(a.getModifiers()))
                .forEach(a -> System.out.println(a.getName() + " have to be public!"));

        Arrays.stream(methods)
                .filter(a -> a.getName().startsWith("set") && !Modifier.isPrivate(a.getModifiers()))
                .forEach(a -> System.out.println(a.getName() + " have to be private!"));
    }
}
