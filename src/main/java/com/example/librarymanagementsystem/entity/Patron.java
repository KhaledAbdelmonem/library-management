package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String city;
    private String phone;
    private String address;
    private int age;
    @Column(name = "Created", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecords;


}
