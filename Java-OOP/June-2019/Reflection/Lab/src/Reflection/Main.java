package Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reflection = Reflection.class;
        System.out.println("class " + reflection.getName());
        System.out.println("class " + reflection.getSuperclass().getName());
        Arrays.stream(reflection.getInterfaces())
                .forEach(a -> System.out.println("interface " + a.getName()));

        Object refInstance = reflection.getDeclaredConstructor().newInstance();
        System.out.println(refInstance);
    }
}
