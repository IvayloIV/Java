package app.services.base;

import app.domain.dto.PersonDto;

public interface PersonService {
    PersonDto getPersonById(long id);

    void saveToDb(PersonDto personDto);
}
