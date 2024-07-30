package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.dto.PatronDTO;
import com.example.librarymanagementsystem.dto.BorrowingRecordDTO;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exceptin.ObjectNotFoundException;
import com.example.librarymanagementsystem.repository.BorrowingRecordRepo;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    PatronServiceImpl patronService;
    @Autowired
    BorrowingRecordRepo borrowingRecordRepo;

    @Override
    @Transactional
    public String borrowBook(Long bookId, Long patronId) throws Exception {

        BookDTO bookDto = bookService.getBook(bookId);
        PatronDTO patronDto = patronService.getPatron(patronId);
        if (bookDto.isBorrowed()) {
            throw new ObjectNotFoundException("Book is already borrowed!");
        }
        bookDto.setBorrowed(true);
        bookService.saveBook(bookDto);

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(BookDTO.toEntity(bookDto));

        borrowingRecord.setPatron(PatronDTO.toEntity(patronDto));
        borrowingRecord.setCreatedAt(LocalDateTime.now());
        borrowingRecordRepo.save(borrowingRecord);

        return "Book borrowed Successfully";
    }

    @Transactional
    @Override
    public String returnBook(Long bookId, Long patronId, BorrowingRecordDTO borrowDTO) throws Exception {

        BookDTO bookDto = bookService.getBook(bookId);
        PatronDTO patronDto = patronService.getPatron(patronId);
        BorrowingRecord borrowingRecord = borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(BookDTO.toEntity(bookDto), PatronDTO.toEntity(patronDto)).orElse(null);
        if (borrowingRecord == null) {
            throw new ObjectNotFoundException("Book is not borrowed");
        }
        bookDto.setBorrowed(false);
        bookService.saveBook(bookDto);

        borrowingRecord.setReturnDate(borrowDTO.getReturnDate());
        borrowingRecordRepo.save(borrowingRecord);

        return "Book is Returned Successfully";
    }


}
