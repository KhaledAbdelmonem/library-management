package com.example.librarymanagementsystem.service.impl;


import com.example.librarymanagementsystem.dto.BookDTO;
import com.example.librarymanagementsystem.dto.PatronDTO;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exceptin.MismatchedDataException;
import com.example.librarymanagementsystem.exceptin.ObjectNotFoundException;
import com.example.librarymanagementsystem.repository.PatronRepo;
import com.example.librarymanagementsystem.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatronServiceImpl implements PatronService {
    @Autowired
    private PatronRepo patronRepo;

    public List<PatronDTO> getAllPatrons() {
        return PatronDTO.listToDTO(patronRepo.findAll());
    }

    public PatronDTO getPatron(Long id) throws Exception {
        Patron patron = patronRepo.findById(id).orElse(null);
        if (patron == null) {
            throw new ObjectNotFoundException("Patron not found!");
        }
        return PatronDTO.toDto(patron);
    }

    public PatronDTO savePatron(PatronDTO patronDTO) {
        return PatronDTO.toDto(patronRepo.save(PatronDTO.toEntity(patronDTO)));
    }


    public String deletePatron(Long id) throws Exception {
        Patron patron = patronRepo.findById(id).orElse(null);
        if (patron == null) {
            throw new ObjectNotFoundException("Patron not found!");
        }
        patronRepo.deleteById(id);
        return "Patron deleted";
    }

    @Override
    public PatronDTO updatePatron(PatronDTO patronDTO, Long patronId) throws Exception {
        if (patronDTO.getId() != null && patronDTO.getId() != patronId) {
            throw new MismatchedDataException("Mismatch Patron id!");
        }
        Patron patron = patronRepo.findById(patronId).orElse(null);
        if (patron == null) {
            throw new ObjectNotFoundException("Patron not found!");
        }
        patronDTO.setId(patronId);
        patron = PatronDTO.toEntity(patronDTO);
        patronRepo.save(patron);

        return patronDTO;
    }
}
