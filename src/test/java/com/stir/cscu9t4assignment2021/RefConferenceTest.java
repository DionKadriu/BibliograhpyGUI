/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2835267
 */
public class RefConferenceTest {

    public RefConferenceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getVenue method, of class RefConference.
     */
    @Test
    public void testGetVenue() {
        System.out.println("getVenue");
        String []authors={""};
        RefConference instance = new RefConference("The Old Man and the Sea",authors ,"1000.0"
                ,"Charles Scribner's Sons",2006,"Some Venue","Kosovo");
        String expResult = "Some Venue";
        String result = instance.getVenue();
        assertEquals(expResult, result);

    }
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String []authors={""};
        RefConference instance = new RefConference("The Old Man and the Sea",authors ,"1000.0"
                ,"Charles Scribner's Sons",2006,"Some Venue","Kosovo");
        String expResult = "Kosovo";
        String result = instance.getLocation();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCitation method, of class RefConference.
     */
    @Test
    public void testGetCitation() {
        System.out.println("getCitation");
        String []authors={"Author"};
        RefConference instance = new RefConference("The Old Man and the Sea",authors ,"1000.0"
                ,"Charles Scribner's Sons",2006,"Some Venue","Kosovo",2,2,2000);
        String expResult = "The Old Man and the Sea, (2006) Author, Charles Scribner's Sons, Some Venue in Kosovo doi:1000.0,  02/02/2000 \n";
        String result = instance.getCitation();
        assertEquals(expResult, result);

    }

}
