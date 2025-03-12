package io.github.gabznavas.Book.API.unittests.mapper.mocks;

import io.github.gabznavas.Book.API.data.dto.v1.BorrowDTO;
import io.github.gabznavas.Book.API.models.Borrow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBorrow {

    private MockBook inputBook;
    private MockPerson inputPerson;

    public MockBorrow() {
        this.inputBook = new MockBook();
        this.inputPerson = new MockPerson();
    }

    public Borrow mockEntity() {
        return mockEntity(0);
    }

    public BorrowDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Borrow> mockEntityList() {
        List<Borrow> borrows = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            borrows.add(mockEntity(i));
        }
        return borrows;
    }

    public List<BorrowDTO> mockDTOList() {
        List<BorrowDTO> borrowDTOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            borrowDTOS.add(mockDTO(i));
        }
        return borrowDTOS;
    }

    public Borrow mockEntity(Integer number) {
        Borrow borrow = new Borrow();
        borrow.setId(number.longValue());
        borrow.setBorrowAt(LocalDate.now());
        borrow.setPerson(inputPerson.mockEntity(number));
        borrow.setBook(inputBook.mockEntity(number));
        return borrow;
    }

    public BorrowDTO mockDTO(Integer number) {
        BorrowDTO dto = new BorrowDTO();
        dto.setId(number.longValue());
        dto.setBorrowAt(LocalDate.now());
        dto.setPersonId(inputPerson.mockEntity(number).getId());
        dto.setBookId(inputBook.mockEntity(number).getId());
        return dto;
    }
}
