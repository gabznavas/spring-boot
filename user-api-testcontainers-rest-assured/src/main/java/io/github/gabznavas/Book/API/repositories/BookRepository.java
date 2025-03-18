package io.github.gabznavas.Book.API.repositories;

import io.github.gabznavas.Book.API.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
