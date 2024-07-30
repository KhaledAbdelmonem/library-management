package com.example.librarymanagementsystem.dto;
import com.example.librarymanagementsystem.entity.Patron;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PatronDTO {
    private Long id;
    @NotBlank
    @NotNull
    private String name;
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String city;
    @NotBlank
    @NotNull
    private String phone;
    @NotBlank
    @NotNull
    private String address;
    @Min(15)
    @Max(70)
    private int age;
    private LocalDateTime createdAt = LocalDateTime.now();




    public static PatronDTO toDto(Patron patron) {
        return PatronDTO.builder()
                .id(patron.getId())
                .name(patron.getName())
                .email(patron.getEmail())
                .city(patron.getCity())
                .phone(patron.getPhone())
                .address(patron.getAddress())
                .age(patron.getAge())
                .createdAt(patron.getCreatedAt())
                .build();
    }
    public static Patron toEntity(PatronDTO patronDto) {
        return Patron.builder()
                .id(patronDto.getId())
                .name(patronDto.getName())
                .email(patronDto.getEmail())
                .city(patronDto.getCity())
                .phone(patronDto.getPhone())
                .address(patronDto.getAddress())
                .age(patronDto.getAge())
                .createdAt(patronDto.getCreatedAt())
                .build();
    }
    public static List<PatronDTO> listToDTO(List<Patron> patronList){
        return patronList.stream().map(PatronDTO::toDto).toList();
    }
}
