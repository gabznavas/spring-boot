package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.controllers.docs.BookControllerDocs;
import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.services.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController implements BookControllerDocs {
    @Autowired
    private BookService bookService;

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
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(dto));
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
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PutMapping(
            value = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BookDTO dto) {
        bookService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
