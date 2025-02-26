package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.controllers.PersonController;
import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.data.dto.v2.PersonDTOV2;
import io.github.gabznavas.Book.API.exceptions.RequiredObjectIsNullException;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonDTO create(PersonDTO person) {
        if (person == null) {
            throw new RequiredObjectIsNullException();
        }
        logger.info("Create one Person!");
        Person newPerson = parseObject(person, Person.class);
        Person personCreated = personRepository.save(newPerson);
        final PersonDTO dto = parseObject(personCreated, PersonDTO.class);
        addHateoas(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Create one Person V2!");
        Person newPerson = convertDtoToEntity(person);
        Person personCreated = personRepository.save(newPerson);
        return convertEntityToDTO(personCreated);
    }

    public PersonDTO update(Long id, PersonDTO data) {
        if (id == null || data == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating all People!");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        person.setFirstName(data.getFirstName());
        person.setLastName(data.getLastName());
        person.setAddress(data.getAddress());
        person.setGender(data.getGender());

        Person personUpdated = personRepository.save(person);
        final PersonDTO dto = parseObject(personUpdated, PersonDTO.class);
        addHateoas(dto);
        return dto;
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
        final PersonDTO dto = parseObject(personFound, PersonDTO.class);
        addHateoas(dto);
        return dto;
    }

    public List<PersonDTO> findAll() {
        List<Person> people = personRepository.findAll().stream().toList();
        final List<PersonDTO> dtos = parseListObjects(people, PersonDTO.class);
        dtos.forEach(this::addHateoas);
        return dtos;
    }


    private void addHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
