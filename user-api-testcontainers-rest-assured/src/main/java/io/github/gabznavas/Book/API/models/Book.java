package io.github.gabznavas.Book.API.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Book() {
    }

    public Book(Long id, String author, LocalDate launch, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launch = launch;
        this.price = price;
        this.title = title;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(launch, book.launch) && Objects.equals(price, book.price) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launch, price, title);
    }
}
