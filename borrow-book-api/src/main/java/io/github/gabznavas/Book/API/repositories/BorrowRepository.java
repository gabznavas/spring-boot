package io.github.gabznavas.Book.API.repositories;

import io.github.gabznavas.Book.API.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Optional<Borrow> findByPersonIdAndBookId(Long personId, Long bookId);
}
