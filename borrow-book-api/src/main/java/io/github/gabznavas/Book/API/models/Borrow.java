package io.github.gabznavas.Book.API.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrow_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate borrowAt;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "books_id")
    private Book book;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(id, borrow.id) && Objects.equals(borrowAt, borrow.borrowAt) && Objects.equals(person, borrow.person) && Objects.equals(book, borrow.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowAt, person, book);
    }
}
