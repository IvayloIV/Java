package app.terminal;

import app.domain.dto.PersonDto;
import app.services.base.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class Terminal implements CommandLineRunner {

    private final PersonService personService;
    private final Gson gson;
    private final String filePath = "src/main/resources/people.json";

    @Autowired
    public Terminal(PersonService personService) {
        this.personService = personService;
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... strings) throws Exception {
        //parseToJSON();
        parseFromJSON();
    }

    private void parseFromJSON() throws FileNotFoundException {
        PersonDto personDto = this.gson.fromJson(new FileReader(filePath), PersonDto.class);
        this.personService.saveToDb(personDto);
    }

    private void parseToJSON() {
        PersonDto person = this.personService.getPersonById(9L);
        String content = this.gson.toJson(person);
        System.out.println(content);
    }
}
