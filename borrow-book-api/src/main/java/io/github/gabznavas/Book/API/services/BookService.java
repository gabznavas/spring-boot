package io.github.gabznavas.Book.API.services;

import io.github.gabznavas.Book.API.controllers.BookController;
import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.models.Book;
import io.github.gabznavas.Book.API.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseListObjects;
import static io.github.gabznavas.Book.API.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    public BookDTO create(BookDTO book) {
        logger.info("Create one Book!");
        final Book newBook = parseObject(book, Book.class);
        final Book bookCreated = bookRepository.save(newBook);
        final BookDTO dto = parseObject(bookCreated, BookDTO.class);
        addHateoas(dto);
        return dto;
    }

    public BookDTO update(Long id, BookDTO data) {
        logger.info("Update a Book!");
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAuthor(data.getAuthor());
        book.setLaunch(data.getLaunch());
        book.setPrice(data.getPrice());
        book.setTitle(data.getTitle());
        bookRepository.save(book);
        final BookDTO dto = parseObject(book, BookDTO.class);
        addHateoas(dto);
        return dto;
    }

    public void delete(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }

    public BookDTO findById(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        final BookDTO dto = parseObject(book, BookDTO.class);
        addHateoas(dto);
        return dto;
    }

    public List<BookDTO> findAll() {
        final List<Book> books = bookRepository.findAll();
        List<BookDTO> dtos = parseListObjects(books, BookDTO.class);
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    private void addHateoas(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withRel("findById").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).update(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
