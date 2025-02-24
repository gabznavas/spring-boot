package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.exceptions.ResourceNotFoundException;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person create(Person person) {
        logger.info("Create one Person!");
        Person newPerson = new Person();
        newPerson.setFirstName("Gabz");
        newPerson.setLastName("Navas");
        newPerson.setAddress("Rua das Galinhas, 123 - São Paulo - Brasil");
        newPerson.setGender("Male");
        newPerson = personRepository.save(person);
        return newPerson;
    }

    public Person update(Long id, Person personToUpdate) {
        logger.info("Updating all People!");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        person.setFirstName(personToUpdate.getFirstName());
        person.setLastName(personToUpdate.getLastName());
        person.setAddress(personToUpdate.getAddress());
        person.setGender(personToUpdate.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting all People!");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        personRepository.delete(person);
    }


    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
    }

    public List<Person> findAll() {
        return personRepository.findAll().stream().toList();
    }
}
