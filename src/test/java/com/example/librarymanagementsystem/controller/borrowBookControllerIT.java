package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.dto.PatronDTO;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class borrowBookControllerIT extends BookBorrowBaseTestIT{
    @Test
    public void canBorrowBook() throws Exception {

       bookService.saveBook(new BookDTO(null,"Eloquent JavaScript, Third Edition","Marijn Haverbeke","9781593279509",LocalDate.of(2022,4,12),false, LocalDateTime.now()));
       patronService.savePatron(new PatronDTO(null,"John Smith","example@test.com","NewYork","212 555-1234","21 2nd Street",25,LocalDateTime.now()));

        final Long bookId = 1L;
        final Long patronId = 1L;


        String retString = bookBorrow (bookId,patronId);
        // get user from db

        assertEquals("Book borrowed Successfully", retString);

    }
    @Test
    public void canReturnBook() throws Exception {

        canBorrowBook();
        final Long bookId = 1L;
        final Long patronId = 1L;
        final LocalDate borrowDate = LocalDate.of(2020,1,1);

        String retString = returnBook (bookId,patronId,borrowDate);
        // get user from db

        assertEquals("Book returned Successfully", retString);

    }
}
