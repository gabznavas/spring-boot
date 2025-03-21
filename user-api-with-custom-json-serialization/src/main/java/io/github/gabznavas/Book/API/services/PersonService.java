package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.data.dto.v2.PersonDTOV2;
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
import static io.github.gabznavas.Book.API.mapper.custom.PersonMapper.convertDtoToEntity;
import static io.github.gabznavas.Book.API.mapper.custom.PersonMapper.convertEntityToDTO;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonDTO create(PersonDTO person) {
        logger.info("Create one Person!");
        Person newPerson = parseObject(person, Person.class);
        Person personCreated = personRepository.save(newPerson);
        return parseObject(personCreated, PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Create one Person V2!");
        Person newPerson = convertDtoToEntity(person);
        Person personCreated = personRepository.save(newPerson);
        return convertEntityToDTO(personCreated);
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
