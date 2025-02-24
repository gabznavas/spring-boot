package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.models.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Finding one Person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabz");
        person.setLastName("Navas");
        person.setAddress("Rua das Galinhas, 123 - São Paulo - Brasil");
        person.setGender("Male");
        return person;
    }

}
