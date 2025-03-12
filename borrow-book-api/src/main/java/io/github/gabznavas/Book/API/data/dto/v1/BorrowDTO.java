package io.github.gabznavas.Book.API.data.dto.v1;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BorrowDTO extends RepresentationModel<BookDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate borrowAt;
    private Long personId;
    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(LocalDate borrowAt) {
        this.borrowAt = borrowAt;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BorrowDTO borrowDTO = (BorrowDTO) o;
        return Objects.equals(id, borrowDTO.id) && Objects.equals(borrowAt, borrowDTO.borrowAt) && Objects.equals(personId, borrowDTO.personId) && Objects.equals(bookId, borrowDTO.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowAt, personId, bookId);
    }
}
