package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.dto.BorrowingRecordDTO;
import com.example.librarymanagementsystem.dto.PatronDTO;
import com.example.librarymanagementsystem.repository.BookRepo;
import com.example.librarymanagementsystem.repository.BorrowingRecordRepo;
import com.example.librarymanagementsystem.repository.PatronRepo;
import com.example.librarymanagementsystem.service.impl.BookServiceImpl;
import com.example.librarymanagementsystem.service.impl.BorrowingRecordServiceImpl;
import com.example.librarymanagementsystem.service.impl.PatronServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class LibraryBaseTestIT {
    private final String BOOKS_PATH = "/books/";
    private final String PATRONS_PATH = "/patrons/";
    private final String BORROWING_BOOK = "/borrow/";
    private final String RETURN_BOOK = "/return/";
    @LocalServerPort
    private final int port = 8080;
    @Autowired
    protected BookServiceImpl bookService;
    @Autowired
    protected PatronServiceImpl patronService;
    @Autowired
    protected BorrowingRecordServiceImpl borrowingRecordService;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PatronRepo patronRepo;
    @Autowired
    private BorrowingRecordRepo borrowingRecordRepo;

    @Before
    public void clean() {
        bookRepo.deleteAll();
        patronRepo.deleteAll();
        borrowingRecordRepo.deleteAll();
    }

    protected BookDTO registerBook(String author, String title, String isbn, LocalDate date) {
        BookDTO bookDTO = BookDTO.builder().title(title).author(author).isbn(isbn).publisher_date(date).createdAt(LocalDateTime.now()).borrowed(false).build();
        return performCall(HttpMethod.POST, bookDTO, BOOKS_PATH, BookDTO.class);
    }

    protected PatronDTO registerPatron(String name, String email, String city, String phone, String address, int age) {
        PatronDTO patronDTO = PatronDTO.builder().name(name).email(email).city(city).phone(phone).address(address).age(age).createdAt(LocalDateTime.now()).build();
        return performCall(HttpMethod.POST, patronDTO, PATRONS_PATH, PatronDTO.class);
    }

    protected String bookBorrow(long bookId, long patronId) throws Exception {
        return performCall(HttpMethod.POST, "", BORROWING_BOOK + "/" + bookId + "/patron/" + patronId, String.class);

    }

    protected String returnBook(Long bookId, Long patronId, LocalDate returnDate) throws Exception {
        BorrowingRecordDTO borrowingRecordDTO = BorrowingRecordDTO.builder().returnDate(returnDate).build();
        return performCall(HttpMethod.PUT, borrowingRecordDTO, RETURN_BOOK + "/" + bookId + "/patron/" + patronId, String.class);
    }


    protected <I, O> O performCall(HttpMethod httpMethod, I input, String path, Class<O> response) {
        HttpEntity<I> httpEntity = new HttpEntity<>(input);
        ResponseEntity<O> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api" + path, httpMethod, httpEntity, response);
        return responseEntity.getBody();
    }
}
