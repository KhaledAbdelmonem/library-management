package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.PatronDTO;
import com.example.librarymanagementsystem.service.impl.PatronServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @Autowired
    private PatronServiceImpl patronService;

    @GetMapping("/")
    public List<PatronDTO> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public PatronDTO getPatron(@PathVariable Long id) throws Exception {
        return patronService.getPatron(id);
    }

    @PostMapping("/")
    public PatronDTO savePatron(@RequestBody @Valid PatronDTO patronDTO) {
        return patronService.savePatron(patronDTO);
    }

    @DeleteMapping("/{id}")
    public String deletePatron(@PathVariable Long id) throws Exception {
        return patronService.deletePatron(id);
    }

    @PutMapping("/{id}")
    public PatronDTO updatePatron(@RequestBody PatronDTO patronDTO, @PathVariable Long id) throws Exception {
        return patronService.updatePatron(patronDTO, id);
    }
}
