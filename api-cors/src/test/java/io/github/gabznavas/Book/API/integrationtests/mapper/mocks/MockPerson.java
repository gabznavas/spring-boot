package io.github.gabznavas.Book.API.integrationtests.mapper.mocks;


import io.github.gabznavas.Book.API.integrationtests.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public PersonDTO mockDTO() {
        return mockDTO(0);
    }


    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i + 1));
        }
        return persons;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        person.setId(number == 0 ? null : number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}