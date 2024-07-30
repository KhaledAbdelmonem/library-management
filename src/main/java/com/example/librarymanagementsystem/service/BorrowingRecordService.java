package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.BorrowingRecordDTO;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BorrowingRecordService {

    public String borrowBook(Long bookId,Long patronId) throws Exception;

    public String returnBook (Long bookId,Long patronId,BorrowingRecordDTO recordDTO) throws Exception;

}
