package com.example.librarymanagementsystem.dto;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BorrowingRecordDTO {

    private Long id;
    private BookDTO bookDto;
    private PatronDTO patronDto;
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    @NotNull
    private LocalDate returnDate;
    private LocalDateTime createdAt;


    public static BorrowingRecordDTO toDto(BorrowingRecord borrowingRecord) {
        return BorrowingRecordDTO.builder()
                .id(borrowingRecord.getId())
                .bookDto(BookDTO.toDto(borrowingRecord.getBook()))
                .patronDto(PatronDTO.toDto(borrowingRecord.getPatron()))
                .returnDate(borrowingRecord.getReturnDate())
                .createdAt(borrowingRecord.getCreatedAt())
                .build();
    }
    public static BorrowingRecord toEntity(BorrowingRecordDTO borrowingRecordDTO) {
        return BorrowingRecord.builder()
                .id(borrowingRecordDTO.getId())
                .book(BookDTO.toEntity(borrowingRecordDTO.getBookDto()))
                .patron(PatronDTO.toEntity(borrowingRecordDTO.getPatronDto()))
                .returnDate(borrowingRecordDTO.getReturnDate())
                .createdAt(borrowingRecordDTO.getCreatedAt())
                .build();
    }
    public static List<BorrowingRecordDTO> listToDTO(List<BorrowingRecord> borrowingRecords){
        return borrowingRecords.stream().map(BorrowingRecordDTO::toDto).toList();
    }

}
