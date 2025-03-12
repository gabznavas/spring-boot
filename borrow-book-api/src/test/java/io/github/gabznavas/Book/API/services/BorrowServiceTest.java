package io.github.gabznavas.Book.API.services;


import io.github.gabznavas.Book.API.data.dto.v1.BorrowDTO;
import io.github.gabznavas.Book.API.models.Book;
import io.github.gabznavas.Book.API.models.Borrow;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.repositories.BookRepository;
import io.github.gabznavas.Book.API.repositories.BorrowRepository;
import io.github.gabznavas.Book.API.repositories.PersonRepository;
import io.github.gabznavas.Book.API.unittests.mapper.mocks.MockBook;
import io.github.gabznavas.Book.API.unittests.mapper.mocks.MockBorrow;
import io.github.gabznavas.Book.API.unittests.mapper.mocks.MockPerson;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {
    // TODO: fazer os tests

    private MockBorrow inputBorrow;
    private MockPerson inputPerson;
    private MockBook inputBook;

    @InjectMocks
    private BorrowService borrowService;

    @Mock
    private BorrowRepository borrowRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach()
    void setUp() {
        inputBorrow = new MockBorrow();
        inputPerson = new MockPerson();
        inputBook = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void borrow() {
        final Long personId = 1L;
        final Person person = inputPerson.mockEntity(personId.intValue());

        final Long bookId = 1L;
        final Book book = inputBook.mockEntity(bookId.intValue());

        final Long borrowId = 1L;
        final Borrow borrow = inputBorrow.mockEntity(borrowId.intValue());

        when(borrowRepository.findByPersonIdAndBookId(personId, bookId))
                .thenReturn(Optional.empty());

        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        when(borrowRepository.save(any(Borrow.class))).thenReturn(borrow);

        final BorrowDTO dto = inputBorrow.mockDTO(borrowId.intValue());

        final BorrowDTO result = borrowService.borrow(dto);

        // not nulls
        assertNotNull(result);
        assertNotNull(result.getBorrowAt());
        assertNotNull(result.getId());
        assertNotNull(result.getBookId());
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        // data
        final LocalDate now = LocalDate.now();
        assertEquals(borrowId, result.getId());
        assertEquals(bookId, result.getBookId());
        assertEquals(personId, result.getPersonId());
        assertEquals(now.getYear(), result.getBorrowAt().getYear());
        assertEquals(now.getMonth(), result.getBorrowAt().getMonth());
        assertEquals(now.getDayOfMonth(), result.getBorrowAt().getDayOfMonth());

        // hateoas
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("borrow")
                                && link.getHref().endsWith("api/borrow/v1")
                                && link.getType().equals("POST")
                )
        );
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("findAll")
                                && link.getHref().endsWith("api/borrow/v1")
                                && link.getType().equals("GET")
                )
        );
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("findById")
                                && link.getHref().endsWith("api/borrow/v1/" + borrowId)
                                && link.getType().equals("GET")
                )
        );
    }

    @Test
    void findAll() {
        final List<Borrow> borrows = inputBorrow.mockEntityList();
        when(borrowRepository.findAll()).thenReturn(borrows);
        final List<BorrowDTO> results = borrowService.findAll();

        assertNotNull(results);
        assertEquals(14, results.size());

        for (int i = 0; i < 14; i++) {
            final Long borrowId = (long) i;
            final Long bookId = (long) i;
            final Long personId = (long) i;
            final BorrowDTO result = results.get(i);

            // not nulls
            assertNotNull(result.getBorrowAt());
            assertNotNull(result.getId());
            assertNotNull(result.getBookId());
            assertNotNull(result.getPersonId());
            assertNotNull(result.getLinks());

            // data
            final LocalDate now = LocalDate.now();
            assertEquals(borrowId, result.getId());
            assertEquals(bookId, result.getBookId());
            assertEquals(personId, result.getPersonId());
            assertEquals(now.getYear(), result.getBorrowAt().getYear());
            assertEquals(now.getMonth(), result.getBorrowAt().getMonth());
            assertEquals(now.getDayOfMonth(), result.getBorrowAt().getDayOfMonth());

            // hateoas
            assertTrue(
                    result.getLinks().stream().anyMatch(link ->
                            link.getRel()
                                    .value().equals("borrow")
                                    && link.getHref().endsWith("api/borrow/v1")
                                    && link.getType().equals("POST")
                    )
            );
            assertTrue(
                    result.getLinks().stream().anyMatch(link ->
                            link.getRel()
                                    .value().equals("findAll")
                                    && link.getHref().endsWith("api/borrow/v1")
                                    && link.getType().equals("GET")
                    )
            );
            assertTrue(
                    result.getLinks().stream().anyMatch(link ->
                            link.getRel()
                                    .value().equals("findById")
                                    && link.getHref().endsWith("api/borrow/v1/" + borrowId)
                                    && link.getType().equals("GET")
                    )
            );
        }
    }

    @Test
    void findById() {
        final Long borrowId = 1L;
        final Long bookId = 1L;
        final Long personId = 1L;
        final Borrow borrow = inputBorrow.mockEntity(borrowId.intValue());
        when(borrowRepository.findById(anyLong())).thenReturn(Optional.of(borrow));
        final BorrowDTO result = borrowService.findById(borrowId);

        // not nulls
        assertNotNull(result.getBorrowAt());
        assertNotNull(result.getId());
        assertNotNull(result.getBookId());
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        // data
        final LocalDate now = LocalDate.now();
        assertEquals(borrowId, result.getId());
        assertEquals(bookId, result.getBookId());
        assertEquals(personId, result.getPersonId());
        assertEquals(now.getYear(), result.getBorrowAt().getYear());
        assertEquals(now.getMonth(), result.getBorrowAt().getMonth());
        assertEquals(now.getDayOfMonth(), result.getBorrowAt().getDayOfMonth());

        // hateoas
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("borrow")
                                && link.getHref().endsWith("api/borrow/v1")
                                && link.getType().equals("POST")
                )
        );
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("findAll")
                                && link.getHref().endsWith("api/borrow/v1")
                                && link.getType().equals("GET")
                )
        );
        assertTrue(
                result.getLinks().stream().anyMatch(link ->
                        link.getRel()
                                .value().equals("findById")
                                && link.getHref().endsWith("api/borrow/v1/" + borrowId)
                                && link.getType().equals("GET")
                )
        );
    }
}
