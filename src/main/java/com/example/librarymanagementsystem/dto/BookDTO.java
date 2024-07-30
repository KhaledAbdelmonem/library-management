package com.example.librarymanagementsystem.dto;

import com.example.librarymanagementsystem.entity.Book;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookDTO {
    private Long id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String author;
    @NotBlank
    private String isbn;
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate publisher_date;
    private boolean borrowed;
    private LocalDateTime createdAt = LocalDateTime.now();


    public static BookDTO toDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publisher_date(book.getPublisher_date())
                .borrowed(book.isBorrowed())
                .createdAt(book.getCreatedAt())
                .build();
    }

    public static Book toEntity(BookDTO bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .publisher_date(bookDto.getPublisher_date())
                .borrowed(bookDto.isBorrowed())
                .createdAt(bookDto.getCreatedAt())
                .build();
    }

    public static List<BookDTO> listToDTO(List<Book> bookList) {
        return bookList.stream().map(BookDTO::toDto).toList();
    }

}