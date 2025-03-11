package io.github.gabznavas.Book.API.services;

import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.models.Book;
import io.github.gabznavas.Book.API.repositories.BookRepository;
import io.github.gabznavas.Book.API.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    // TODO: Criar os tests

    MockBook input;

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        input = new MockBook();

        // abrir os mock do mockito pra essa classe
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test() {
        assertEquals(1, 1);
    }

    @Test
    void findById() {
        final Long bookId = 1L;

        Book book = input.mockEntity(bookId.intValue());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookDTO result = bookService.findById(bookId);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());

        // hateoas
        // self
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("findById")
                        && link.getHref().endsWith("api/book/v1/" + bookId)
                        && link.getType().equals("GET")
        );
        // update
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("update")
                        && link.getHref().endsWith("api/book/v1/" + bookId)
                        && link.getType().equals("PUT")
        );
        // delete
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/" + bookId)
                        && link.getType().equals("DELETE")
        );
        // findAll
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("findAll")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
        );
        // create
        result.getLinks().stream().anyMatch(link ->
                link.getRel()
                        .value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
        );
        assertEquals(5, result.getLinks().stream().count());

        // assertions data
        LocalDate today = LocalDate.now();
        assertEquals(bookId.longValue(), result.getId());
        assertEquals("author" + bookId, result.getAuthor());
        assertEquals("Title" + bookId, result.getTitle());
        assertEquals(today.getYear(), result.getLaunch().getYear());
        assertEquals(today.getMonth(), result.getLaunch().getMonth());
        assertEquals(today.getDayOfMonth(), result.getLaunch().getDayOfMonth());
        assertEquals(100, result.getPrice());
    }
}
