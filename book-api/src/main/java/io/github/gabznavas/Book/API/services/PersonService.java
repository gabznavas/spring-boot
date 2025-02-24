package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private List<Person> people = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person create(Person person) {
        logger.info("Create one Person!");
        Person newPerson = new Person();
        newPerson.setId(counter.incrementAndGet());
        newPerson.setFirstName("Gabz");
        newPerson.setLastName("Navas");
        newPerson.setAddress("Rua das Galinhas, 123 - São Paulo - Brasil");
        newPerson.setGender("Male");
        people.add(newPerson);
        return newPerson;
    }

    public Person update(String id, Person personToUpdate) {
        logger.info("Updating all People!");
        Person personFound = null;
        int index = -1;

        for(int i=0 ; i < people.size(); i++) {
            if(people.get(i).getId() == Long.parseLong(id)) {
                personFound = people.get(i);
                index = i;
                break;
            }
        }
        if(index == 0) {
            throw new RuntimeException("Person not found!");
        }
        personFound.setFirstName(personToUpdate.getFirstName());
        personFound.setLastName(personToUpdate.getLastName());
        personFound.setAddress(personToUpdate.getAddress());
        personFound.setGender(personToUpdate.getGender());

        people.set(index, personFound);

        return personFound;
    }

    public void delete(String id) {
        logger.info("Deleting all People!");
        int index = -1;
        for(int i=0 ; i < people.size(); i++) {
            if(people.get(i).getId() == Long.parseLong(id)) {
                index = i;
                break;
            }
        }
        if(index == 0) {
            throw new RuntimeException("Person not found!");
        }
        people.remove(index);
    }


    public Person findById(String id) {
        logger.info("Finding one Person!");
        Person personFound = null;
        for(Person person : people) {
            if(person.getId() == Long.parseLong(id)) {
                personFound = person;
                break;
            }
        }
        if(personFound == null) {
            throw new RuntimeException("Person not found!");
        }
        return personFound;
    }

    public List<Person> findAll() {
        logger.info("Finding all People!");
        return people.stream().toList();
    }
}
