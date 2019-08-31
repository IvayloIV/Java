package app.services;

import app.domain.dto.PersonDto;
import app.domain.model.Person;
import app.repository.PersonRepository;
import app.services.base.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public PersonDto getPersonById(long id) {
        Person person = this.personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not exist."));

        return this.modelMapper.map(person, PersonDto.class);
    }

    @Override
    public void saveToDb(PersonDto personDto) {
        Person person = this.modelMapper.map(personDto, Person.class);
        person.getPhoneNumbers().forEach(p -> p.setPerson(person));
        this.personRepository.saveAndFlush(person);
    }
}
