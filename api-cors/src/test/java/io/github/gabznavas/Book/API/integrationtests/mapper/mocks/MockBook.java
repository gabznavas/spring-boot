package io.github.gabznavas.Book.API.integrationtests.mapper.mocks;


import io.github.gabznavas.Book.API.integrationtests.dto.BookDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public BookDTO mockDTO() {
        return mockDTO(0);
    }


    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i + 1));
        }
        return books;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO dto = new BookDTO();
        dto.setTitle("Title" + number);
        dto.setAuthor("author" + number);
        dto.setPrice(100.00);
        dto.setLaunch(LocalDate.now());
        dto.setId(number == 0 ? null : number.longValue());
        return dto;
    }

}
