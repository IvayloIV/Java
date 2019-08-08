package Black_Box_Integer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Class blackBoxInt = BlackBoxInt.class;
        Constructor constructor = blackBoxInt.getDeclaredConstructor();
        constructor.setAccessible(true);

        Object obj = constructor.newInstance();
        Field field = blackBoxInt.getDeclaredField("innerValue");
        field.setAccessible(true);
        String input = "";

        while (!(input = bufferedReader.readLine()).equals("END")) {
            String[] tokens = input.split("_");
            String methodName = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method method = blackBoxInt.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(obj, value);
            System.out.println(field.get(obj));
        }
    }
}
