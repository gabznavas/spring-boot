package io.github.gabznavas.Book.API.mapper.custom;

import io.github.gabznavas.Book.API.data.dto.v1.BorrowDTO;
import io.github.gabznavas.Book.API.models.Borrow;

public class BorrowMapper {

    public static BorrowDTO convertEntityToDto(Borrow borrow) {
        BorrowDTO dto = new BorrowDTO();
        dto.setId(borrow.getId());
        dto.setBorrowAt(borrow.getBorrowAt());
        dto.setBookId(borrow.getBook().getId());
        dto.setPersonId(borrow.getPerson().getId());
        return dto;
    }
}
