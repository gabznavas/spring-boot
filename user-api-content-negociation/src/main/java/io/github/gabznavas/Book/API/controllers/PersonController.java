package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.data.dto.v2.PersonDTOV2;
import io.github.gabznavas.Book.API.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> create(@RequestBody final PersonDTO dto) {
        final PersonDTO dtoCreated = personService.create(dto);
        addHateoas(dtoCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCreated);
    }


    @PostMapping(
            value = "/v2",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTOV2> createV2(@RequestBody PersonDTOV2 dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createV2(dto));
    }

    @PutMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody() PersonDTO dto) {
        personService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        final PersonDTO dto = personService.findById(id);
        addHateoas(dto);
        return ResponseEntity.ok(dto);
    }


    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    public ResponseEntity<List<PersonDTO>> findAll() {
        final List<PersonDTO> dtos = personService.findAll();
        dtos.forEach(this::addHateoas);
        return ResponseEntity.ok(dtos);
    }

    private void addHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
