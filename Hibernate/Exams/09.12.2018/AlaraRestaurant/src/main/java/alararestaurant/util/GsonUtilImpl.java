package alararestaurant.util;

import alararestaurant.util.base.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GsonUtilImpl implements GsonUtil {
    private static final String BASE_PATH = "src/main/resources/";
    private static final String OUTPUT_FOLDER = BASE_PATH + "files/jsons/";

    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    public <T> void saveJSONtoFile(String fileName, T[] items) throws IOException {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FOLDER + fileName +".json")) {
            gson.toJson(items, fileWriter);
        }
    }
}
