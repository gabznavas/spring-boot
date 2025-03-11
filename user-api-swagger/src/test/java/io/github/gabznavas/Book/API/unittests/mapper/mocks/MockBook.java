package io.github.gabznavas.Book.API.unittests.mapper.mocks;

import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.models.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("Title" + number);
        book.setAuthor("author" + number);
        book.setPrice(100.00);
        book.setLaunch(LocalDate.now());
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO dto = new BookDTO();
        dto.setTitle("Title" + number);
        dto.setAuthor("author" + number);
        dto.setPrice(100.00);
        dto.setLaunch(LocalDate.now());
        dto.setId(number.longValue());
        return dto;
    }

}
