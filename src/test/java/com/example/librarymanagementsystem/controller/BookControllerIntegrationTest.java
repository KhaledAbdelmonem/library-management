package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(new BookDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetBook() throws Exception {
        long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);

        when(bookService.getBook(bookId)).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookId));
    }

    @Test
    public void testSaveBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test Book");

        when(bookService.saveBook(any(BookDTO.class))).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books/").contentType(MediaType.APPLICATION_JSON).content("{ \"title\": \"Test Book\" }")).andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        long bookId = 1L;
        String responseMessage = "Book with ID: " + bookId + " has been deleted.";

        when(bookService.deleteBook(bookId)).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(responseMessage));
    }

    @Test
    public void testUpdateBook() throws Exception {
        long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);
        bookDTO.setTitle("Updated Book");

        when(bookService.updateBook(eq(bookDTO), eq(bookId))).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId).contentType(MediaType.APPLICATION_JSON).content("{ \"title\": \"Updated Book\" }")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookId)).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Book"));
    }
}