package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") String id) {
        return personService.findById(id);
    }
}
