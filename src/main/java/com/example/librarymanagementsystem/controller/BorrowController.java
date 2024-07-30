package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BorrowingRecordDTO;

import com.example.librarymanagementsystem.service.impl.BorrowingRecordServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class BorrowController {

    @Autowired
    BorrowingRecordServiceImpl borrowingService;

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public String borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) throws Exception {
        return borrowingService.borrowBook(bookId, patronId);
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    public String returnBook(@PathVariable Long bookId, @PathVariable Long patronId, @RequestBody @Valid BorrowingRecordDTO recordDTO) throws Exception {
        return borrowingService.returnBook(bookId, patronId, recordDTO);
    }


}
