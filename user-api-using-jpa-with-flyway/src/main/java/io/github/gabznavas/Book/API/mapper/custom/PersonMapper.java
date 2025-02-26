package io.github.gabznavas.Book.API.mapper.custom;

import io.github.gabznavas.Book.API.data.dto.v2.PersonDTOV2;
import io.github.gabznavas.Book.API.models.Person;

import java.util.Date;

public class PersonMapper {

    public static PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDay(new Date());
        return dto;
    }


    public static Person convertDtoToEntity(PersonDTOV2 dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setGender(dto.getGender());
        return person;
    }
}
