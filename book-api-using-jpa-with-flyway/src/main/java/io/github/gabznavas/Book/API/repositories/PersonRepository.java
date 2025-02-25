package io.github.gabznavas.Book.API.repositories;

import io.github.gabznavas.Book.API.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
