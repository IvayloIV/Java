package Harvesting_Fields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new HashMap<>();
        map.put("public", 1);
        map.put("private", 2);
        map.put("protected", 4);

		Class richSoilLandClass = RichSoilLand.class;
		Field[] fields = richSoilLandClass.getDeclaredFields();
		String accessModifier = "";

		while (!(accessModifier = bufferedReader.readLine()).equals("HARVEST")) {
			for (Field field : fields) {
				int modifierInt = field.getModifiers();
				String type = field.getType().getSimpleName();
				String name = field.getName();

				if (accessModifier.equals("all") ||
                        map.get(accessModifier) == modifierInt) {
					System.out.println(String.format("%s %s %s",
							Modifier.toString(modifierInt),
							type,
							name));
				}
			}
		}
	}
}
