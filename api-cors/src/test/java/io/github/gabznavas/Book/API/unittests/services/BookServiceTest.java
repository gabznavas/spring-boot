package io.github.gabznavas.Book.API.unittests.services;

import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.models.Book;
import io.github.gabznavas.Book.API.repositories.BookRepository;
import io.github.gabznavas.Book.API.unittests.services.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
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

        // not nulls
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());

        // assertions data
        LocalDate today = LocalDate.now();
        assertEquals(bookId.longValue(), result.getId());
        assertEquals("author" + bookId, result.getAuthor());
        assertEquals("Title" + bookId, result.getTitle());
        assertEquals(today.getYear(), result.getLaunch().getYear());
        assertEquals(today.getMonth(), result.getLaunch().getMonth());
        assertEquals(today.getDayOfMonth(), result.getLaunch().getDayOfMonth());
        assertEquals(100, result.getPrice());

        // hateoas
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/v1/book")
                        && link.getType().equals("POST")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findById")
                        && link.getHref().endsWith(String.format("/api/v1/book/%d", bookId))
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("api/v1/book")
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("PUT")
        ));
        result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("DELETE")
        );
        assertEquals(5, result.getLinks().stream().count());
    }

    @Test
    void findAll() {
        List<Book> books = input.mockEntityList();
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> results = bookService.findAll();

        assertNotNull(results);
        for (int i = 0; i < results.size(); i++) {
            final int bookId = i + 1;
            BookDTO result = results.get(i);

            // not nulls
            assertNotNull(result.getId());
            assertNotNull(result.getAuthor());
            assertNotNull(result.getLaunch());
            assertNotNull(result.getPrice());
            assertNotNull(result.getTitle());
            assertNotNull(result.getLinks());

            // assertions data
            LocalDate today = LocalDate.now();
            assertEquals(i + 1, result.getId());
            assertEquals("author" + (i + 1), result.getAuthor());
            assertEquals("Title" + (i + 1), result.getTitle());
            assertEquals(today.getYear(), result.getLaunch().getYear());
            assertEquals(today.getMonth(), result.getLaunch().getMonth());
            assertEquals(today.getDayOfMonth(), result.getLaunch().getDayOfMonth());
            assertEquals(100, result.getPrice());

            result.getLinks().forEach(link -> {
                System.out.println(link.getRel().value());
                System.out.println(link.getHref());
                System.out.println(link.getType());
            });

            // hateoas
            assertTrue(result.getLinks().stream().anyMatch(link ->
                    link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/v1/book")
                            && link.getType().equals("POST")
            ));
            assertTrue(result.getLinks().stream().anyMatch(link ->
                    link.getRel().value().equals("findById")
                            && link.getHref().endsWith(String.format("/api/v1/book/%d", bookId))
                            && link.getType().equals("GET")
            ));
            assertTrue(result.getLinks().stream().anyMatch(link ->
                    link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("api/v1/book")
                            && link.getType().equals("GET")
            ));
            assertTrue(result.getLinks().stream().anyMatch(link ->
                    link.getRel().value().equals("update")
                            && link.getHref().endsWith("api/v1/book/" + bookId)
                            && link.getType().equals("PUT")
            ));
            result.getLinks().stream().anyMatch(link ->
                    link.getRel().value().equals("delete")
                            && link.getHref().endsWith("api/v1/book/" + bookId)
                            && link.getType().equals("DELETE")
            );
            assertEquals(5, result.getLinks().stream().count());
        }
    }

    @Test
    void create() {
        Long bookId = 1L;

        Book book = input.mockEntity(bookId.intValue());
        when(bookRepository.save(book)).thenReturn(book);

        BookDTO dto = input.mockDTO(bookId.intValue());

        BookDTO result = bookService.create(dto);

        // not nulls
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());

        // assertions data
        LocalDate today = LocalDate.now();
        assertEquals(bookId, result.getId());
        assertEquals("author" + bookId, result.getAuthor());
        assertEquals("Title" + bookId, result.getTitle());
        assertEquals(today.getYear(), result.getLaunch().getYear());
        assertEquals(today.getMonth(), result.getLaunch().getMonth());
        assertEquals(today.getDayOfMonth(), result.getLaunch().getDayOfMonth());
        assertEquals(100, result.getPrice());

        // hateoas
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/v1/book")
                        && link.getType().equals("POST")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findById")
                        && link.getHref().endsWith(String.format("/api/v1/book/%d", bookId))
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("api/v1/book")
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("PUT")
        ));
        result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("DELETE")
        );
        assertEquals(5, result.getLinks().stream().count());
    }

    @Test
    void update() {
        Long bookId = 1L;

        Book book = input.mockEntity(bookId.intValue());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        BookDTO dto = input.mockDTO(bookId.intValue());
        BookDTO result = bookService.update(bookId, dto);

        // not nulls
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLinks());

        // assertions data
        LocalDate today = LocalDate.now();
        assertEquals(bookId, result.getId());
        assertEquals("author" + bookId, result.getAuthor());
        assertEquals("Title" + bookId, result.getTitle());
        assertEquals(today.getYear(), result.getLaunch().getYear());
        assertEquals(today.getMonth(), result.getLaunch().getMonth());
        assertEquals(today.getDayOfMonth(), result.getLaunch().getDayOfMonth());
        assertEquals(100, result.getPrice());

        // hateoas
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/v1/book")
                        && link.getType().equals("POST")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findById")
                        && link.getHref().endsWith(String.format("/api/v1/book/%d", bookId))
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("api/v1/book")
                        && link.getType().equals("GET")
        ));
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("PUT")
        ));
        result.getLinks().stream().anyMatch(link ->
                link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/v1/book/" + bookId)
                        && link.getType().equals("DELETE")
        );
        assertEquals(5, result.getLinks().stream().count());
    }

    @Test
    void delete() {
        Long bookId = 1L;

        Book book = input.mockEntity(bookId.intValue());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        bookService.delete(bookId);

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }
}
