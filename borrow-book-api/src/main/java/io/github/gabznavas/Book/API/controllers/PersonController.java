package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.controllers.docs.PersonControllerDocs;
import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {

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
    @Override
    public ResponseEntity<PersonDTO> create(@RequestBody final PersonDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(dto));
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
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody() PersonDTO dto) {
        personService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Override
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
    @Override
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    @Override
    public ResponseEntity<List<PersonDTO>> findAll() {
        final List<PersonDTO> dtos = personService.findAll();
        return ResponseEntity.ok(dtos);
    }
}
