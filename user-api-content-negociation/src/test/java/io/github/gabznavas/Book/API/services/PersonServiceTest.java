package io.github.gabznavas.Book.API.services;

import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.repositories.PersonRepository;
import io.github.gabznavas.Book.API.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

// ira criar os objetos por classe
// se tiver outra classe, vai criar outras instâncias
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

// usar o mockito
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        // abrir o mock do mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        final Long personId = 1L;
        Person person = input.mockEntity(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        PersonDTO result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAddress());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        // hateoas
        // self
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("self")
                        && link.getHref().endsWith("api/person/v1/" + personId)
                        && link.getType().equals("GET")
        );
        // update
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("update")
                        && link.getHref().endsWith("api/person/v1/" + personId)
                        && link.getType().equals("PUT")
        );
        // delete
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/" + personId)
                        && link.getType().equals("DELETE")
        );
        // findAll
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("self")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("GET")
        );
        // create
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("create")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("POST")
        );
        // length
        assertEquals(5, result.getLinks().stream().toList().size());

        // assert data person
        assertEquals(personId, person.getId());
        assertEquals("First Name Test" + personId, person.getFirstName());
        assertEquals("Last Name Test" + personId, person.getLastName());
        assertEquals("Female", person.getGender());
        assertEquals("Address Test" + personId, person.getAddress());
    }

    @Test
    void create() {
        final Long personId = 1L;
        Person person = input.mockEntity(personId.intValue());

        when(personRepository.save(person)).thenReturn(person);

        PersonDTO dto = input.mockDTO(personId.intValue());
        PersonDTO result = personService.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAddress());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        // hateoas
        // self
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("self")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("POST")
        );
        // update
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("update")
                        && link.getHref().endsWith("api/person/v1/" + personId)
                        && link.getType().equals("PUT")
        );
        // delete
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("delete")
                        && link.getHref().endsWith("api/person/v1/" + personId)
                        && link.getType().equals("DELETE")
        );
        // findAll
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("self")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("GET")
        );
        // create
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("findById")
                        && link.getHref().endsWith("api/person/v1" + personId)
                        && link.getType().equals("GET")
        );
        // length
        assertEquals(5, result.getLinks().stream().toList().size());

        // assert data person
        assertEquals(personId, person.getId());
        assertEquals("First Name Test" + personId, person.getFirstName());
        assertEquals("Last Name Test" + personId, person.getLastName());
        assertEquals("Female", person.getGender());
        assertEquals("Address Test" + personId, person.getAddress());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}