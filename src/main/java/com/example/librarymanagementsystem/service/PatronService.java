package com.example.librarymanagementsystem.service;



import com.example.librarymanagementsystem.dto.PatronDTO;

import java.util.List;

public interface PatronService {
    public List<PatronDTO> getAllPatrons();

    public PatronDTO getPatron(Long id) throws Exception;

    public PatronDTO savePatron(PatronDTO patronDTO);

    public String deletePatron(Long id) throws Exception;

    public PatronDTO updatePatron(PatronDTO patronDTO , Long patronId) throws Exception ;
}
