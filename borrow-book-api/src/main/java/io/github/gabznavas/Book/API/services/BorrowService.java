package io.github.gabznavas.Book.API.services;

import io.github.gabznavas.Book.API.controllers.BorrowController;
import io.github.gabznavas.Book.API.data.dto.v1.BorrowDTO;
import io.github.gabznavas.Book.API.mapper.custom.BorrowMapper;
import io.github.gabznavas.Book.API.models.Book;
import io.github.gabznavas.Book.API.models.Borrow;
import io.github.gabznavas.Book.API.models.Person;
import io.github.gabznavas.Book.API.repositories.BookRepository;
import io.github.gabznavas.Book.API.repositories.BorrowRepository;
import io.github.gabznavas.Book.API.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static io.github.gabznavas.Book.API.mapper.custom.BorrowMapper.convertEntityToDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;


    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private BookRepository bookRepository;

    private final Logger logger = LoggerFactory.getLogger(BorrowService.class.getName());

    @Transactional
    public BorrowDTO borrow(BorrowDTO dto) {
        logger.info("Borrowing a Book!");

        final Optional<Borrow> optionalBorrow = borrowRepository
                .findByPersonIdAndBookId(dto.getPersonId(), dto.getBookId());

        if (optionalBorrow.isPresent()) {
            Borrow borrow = optionalBorrow.get();
            throw new RuntimeException(
                    String.format(
                            "Livro já foi emprestado na data de %s",
                            borrow.getBorrowAt()
                    )
            );
        }

        final Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        final Book book = bookRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Borrow newBorrow = new Borrow();
        newBorrow.setPerson(person);
        newBorrow.setBook(book);
        newBorrow.setBorrowAt(LocalDate.now());

        newBorrow = borrowRepository.save(newBorrow);

        final BorrowDTO dtoResult = convertEntityToDto(newBorrow);

        addHateoas(dtoResult);

        return dtoResult;
    }

    public List<BorrowDTO> findAll() {
        final List<Borrow> borrows = borrowRepository.findAll();
        final List<BorrowDTO> borrowDTOS = borrows.stream().map(BorrowMapper::convertEntityToDto).toList();
        borrowDTOS.forEach(this::addHateoas);
        return borrowDTOS;
    }

    public BorrowDTO findById(Long id) {
        final Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("emprestimo não encontrado"));
        final BorrowDTO dto = convertEntityToDto(borrow);
        addHateoas(dto);
        return dto;
    }

    private void addHateoas(BorrowDTO dto) {
        dto.add(linkTo(methodOn(BorrowController.class).borrow(dto)).withRel("borrow").withType("POST"));
        dto.add(linkTo(methodOn(BorrowController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BorrowController.class).findById(dto.getId())).withRel("findById").withType("GET"));
    }
}
