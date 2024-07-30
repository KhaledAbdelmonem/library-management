package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.BookDTO;

import java.util.List;

public interface BookService {

    public List<BookDTO> getAllBooks();

    public BookDTO getBook(Long id) throws Exception;

    public BookDTO saveBook(BookDTO bookDto);

    public String deleteBook(Long id) throws Exception ;

    public BookDTO updateBook(BookDTO bookDto , Long bookId) throws Exception ;

}
