package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.dto.PatronDTO;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class borrowBookControllerIT extends LibraryBaseTestIT{
    @Test
    public void canBorrowBook() throws Exception {

       BookDTO bookDTO = registerBook("Marijn Haverbeke","Eloquent JavaScript, Third Edition","9781593279509",LocalDate.now());
       PatronDTO patronDTO = registerPatron("John Smith","example@test.com","NewYork","212 555-1234","21 2nd Street",25);

        final Long bookId = bookDTO.getId();
        final Long patronId = patronDTO.getId();

        String retString = bookBorrow (bookId,patronId);

        assertEquals("Book borrowed Successfully", retString);

    }
    @Test
    public void canReturnBook() throws Exception {

        BookDTO bookDTO = registerBook("Marijn Haverbeke","Eloquent JavaScript, Third Edition","9781593279509",LocalDate.now());
        PatronDTO patronDTO = registerPatron("John Smith","example@test.com","NewYork","212 555-1234","21 2nd Street",25);

        final Long bookId = bookDTO.getId();
        final Long patronId = patronDTO.getId();

        bookBorrow(bookId,patronId);

        final LocalDate borrowDate = LocalDate.of(2020,1,1);

        String retString = returnBook (bookId,patronId,borrowDate);

        assertEquals("Book is Returned Successfully", retString);

    }
}
