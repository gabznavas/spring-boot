package io.github.gabznavas.Book.API.services;

import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.exceptions.RequiredObjectIsNullException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        assertEquals(personId, result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
        assertEquals(person.getAddress(), result.getAddress());
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
        assertEquals(personId, result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
        assertEquals(person.getAddress(), result.getAddress());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personService.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void update() {
        final Long personId = 1L;
        final Person person = input.mockEntity(personId.intValue());
        final PersonDTO dto = input.mockDTO(personId.intValue());

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);

        final PersonDTO result = personService.update(personId, dto);

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
                        .value().equals("create")
                        && link.getHref().endsWith("api/person/v1")
                        && link.getType().equals("POST")
        );
        // update
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("self")
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
        assertEquals(personId, result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
        assertEquals(person.getAddress(), result.getAddress());
    }

    @Test
    void testUpdateCreateWithNullPerson() {
        // id null
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personService.update(null, input.mockDTO());
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        // personDto null
        exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personService.update(1L, null);
        });
        expectedMessage = "It is not allowed to persist a null object";
        actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void delete() {
        final Long personId = 1L;
        final Person person = input.mockEntity(personId.intValue());
        final PersonDTO dto = input.mockDTO(personId.intValue());

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        personService.delete(personId);

        verify(personRepository, times(1)).findById(anyLong());
        verify(personRepository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    void findAll() {
        List<Person> people = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(people);
        List<PersonDTO> peopleResult = personService.findAll();

        assertNotNull(peopleResult);
        assertEquals(people.size(), peopleResult.size());

        for (int i = 0; i < people.size(); i++) {
            final PersonDTO result = peopleResult.get(i);
            final Person person = people.get(i);

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
                            .value().equals("findById")
                            && link.getHref().endsWith("api/person/v1/" + person.getId())
                            && link.getType().equals("GET")
            );
            // update
            result.getLinks().stream().anyMatch(link ->
                    link.getRel()
                            .value().equals("update")
                            && link.getHref().endsWith("api/person/v1/" + person.getId())
                            && link.getType().equals("PUT")
            );
            // delete
            result.getLinks().stream().anyMatch(link ->
                    link.getRel()
                            .value().equals("delete")
                            && link.getHref().endsWith("api/person/v1/" + person.getId())
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
            assertEquals(person.getId(), result.getId());
            assertEquals(person.getFirstName(), result.getFirstName());
            assertEquals(person.getLastName(), result.getLastName());
            assertEquals(person.getGender(), result.getGender());
            assertEquals(person.getAddress(), result.getAddress());
        }
    }
}