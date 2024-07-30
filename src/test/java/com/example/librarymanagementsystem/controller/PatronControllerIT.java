package com.example.librarymanagementsystem.controller;


import com.example.librarymanagementsystem.dto.PatronDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatronControllerIT extends LibraryBaseTestIT {
    @Test
    public void canRegisterPatron() throws Exception {

        final String NAME = "John Smith";
        final String EMAIL = "example@test.com";
        final String CITY = "NewYork";
        final String PHONE = "212 555-1234";
        final String ADDRESS = "21 2nd Street";
        final int AGE = 25;

        PatronDTO patronDTO = registerPatron(NAME, EMAIL, CITY, PHONE, ADDRESS, AGE);

        assertEquals(NAME, patronDTO.getName());
        assertEquals(EMAIL, patronDTO.getEmail());
        assertEquals(CITY, patronDTO.getCity());
        assertEquals(PHONE, patronDTO.getPhone());
        assertEquals(ADDRESS, patronDTO.getAddress());
        assertEquals(AGE, patronDTO.getAge());
    }
}
