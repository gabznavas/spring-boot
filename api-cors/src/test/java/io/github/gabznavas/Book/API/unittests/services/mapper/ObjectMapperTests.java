package io.github.gabznavas.Book.API.unittests.services.mapper;

import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.unittests.services.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseListObjects;
import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ObjectMapperTests {
    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToDTOTest() {
        PersonDTO output = parseObject(inputObject.mockEntity(), PersonDTO.class);
        assertEquals(null, output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<PersonDTO> outputList = parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(1L), outputZero.getId());
        assertEquals("First Name Test1", outputZero.getFirstName());
        assertEquals("Last Name Test1", outputZero.getLastName());
        assertEquals("Address Test1", outputZero.getAddress());
        assertEquals("Female", outputZero.getGender());

        PersonDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(8L), outputSeven.getId());
        assertEquals("First Name Test8", outputSeven.getFirstName());
        assertEquals("Last Name Test8", outputSeven.getLastName());
        assertEquals("Address Test8", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        PersonDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(13L), outputTwelve.getId());
        assertEquals("First Name Test13", outputTwelve.getFirstName());
        assertEquals("Last Name Test13", outputTwelve.getLastName());
        assertEquals("Address Test13", outputTwelve.getAddress());
        assertEquals("Female", outputTwelve.getGender());
    }

    @Test
    public void parseDTOToEntityTest() {
        Person output = parseObject(inputObject.mockDTO(), Person.class);
        assertEquals(null, output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Person> outputList = parseListObjects(inputObject.mockDTOList(), Person.class);
        Person outputZero = outputList.get(0);

        assertEquals(Long.valueOf(1), outputZero.getId());
        assertEquals("First Name Test1", outputZero.getFirstName());
        assertEquals("Last Name Test1", outputZero.getLastName());
        assertEquals("Address Test1", outputZero.getAddress());
        assertEquals("Female", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(8L), outputSeven.getId());
        assertEquals("First Name Test8", outputSeven.getFirstName());
        assertEquals("Last Name Test8", outputSeven.getLastName());
        assertEquals("Address Test8", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(13L), outputTwelve.getId());
        assertEquals("First Name Test13", outputTwelve.getFirstName());
        assertEquals("Last Name Test13", outputTwelve.getLastName());
        assertEquals("Address Test13", outputTwelve.getAddress());
        assertEquals("Female", outputTwelve.getGender());
    }
}