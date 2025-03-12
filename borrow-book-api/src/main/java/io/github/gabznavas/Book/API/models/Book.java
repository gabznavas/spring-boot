package io.github.gabznavas.Book.API.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", length = 180, nullable = false)
    private String author;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate launch;

    @Column(name = "price", nullable = false)
    // TODO: alterar para BigDecimal
    private Double price;

    @Column(name = "title", length = 250, nullable = false)
    private String title;

    @OneToMany
    List<Borrow> borrows = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getLaunch() {
        return launch;
    }

    public void setLaunch(LocalDate launch) {
        this.launch = launch;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(launch, book.launch) && Objects.equals(price, book.price) && Objects.equals(title, book.title) && Objects.equals(borrows, book.borrows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launch, price, title, borrows);
    }
}
