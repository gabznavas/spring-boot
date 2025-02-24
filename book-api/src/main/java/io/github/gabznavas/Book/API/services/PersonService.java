package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person create(Person person) {
        logger.info("Create one Person!");
        person.setId(counter.incrementAndGet());
        return person;
    }

    public Person update(String id, Person person) {
        logger.info("Updating all People!");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting all People!");
    }


    public Person findById(String id) {
        logger.info("Finding one Person!");
        return mockPerson();
    }

    public List<Person> findAll() {
        logger.info("Finding all People!");
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            people.add(mockPerson());
        }
        return people;
    }

    private Person mockPerson() {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabz");
        person.setLastName("Navas");
        person.setAddress("Rua das Galinhas, 123 - São Paulo - Brasil");
        person.setGender("Male");
        return person;
    }

}
