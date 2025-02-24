package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.data.dto.PersonDTO;
import io.github.gabznavas.Book.API.exceptions.ResourceNotFoundException;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseListObjects;
import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonDTO create(PersonDTO person) {
        logger.info("Create one Person!");
        Person newPerson = new Person();
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());
        Person personCreated = personRepository.save(newPerson);
        return parseObject(personCreated, PersonDTO.class);
    }

    public PersonDTO update(Long id, PersonDTO dto) {
        logger.info("Updating all People!");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setGender(dto.getGender());

        Person personUpdated = personRepository.save(person);
        return parseObject(personUpdated, PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting all People!");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        personRepository.delete(person);
    }


    public PersonDTO findById(Long id) {
        Person personFound = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
        return parseObject(personFound, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        List<Person> people = personRepository.findAll().stream().toList();
        return parseListObjects(people, PersonDTO.class);
    }
}
