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
public class RefJournalTest {

    public RefJournalTest() {
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
     * Test of getJournal method, of class RefJournal.
     */
    @Test
    public void testGetJournal() {
        System.out.println("getJournal");
        String[]authors={"Author"};
        RefJournal instance =  new RefJournal("The Old Man and the Sea",authors,2006
                ,"Charles Scribner's Sons","500.6","Journal",1,1);

        String expResult = "Journal";
        String result = instance.getJournal();
        assertEquals(expResult, result);

    }
    /**
     * Test of getType method, of class RefJournal.
     */
    @Test
    void getType() {
        System.out.println("getType");
        String[]authors={"Author"};
        RefJournal instance =  new RefJournal("The Old Man and the Sea",authors,2006
                ,"Charles Scribner's Sons","500.6","Journal",1,1);

        String expResult = "RefJournal";
        String result = instance.getType();
        assertEquals(expResult, result);
    }
    /**
     * Test of getVolume method, of class RefJournal.
     */
    @Test
    void getVolume() {
        System.out.println("getJournal");
        String[]authors={"Author"};
        RefJournal instance =  new RefJournal("The Old Man and the Sea",authors,2006
                ,"Charles Scribner's Sons","500.6","Journal",1,1);

        int expResult = 1;
        int result = instance.getVolume();
        assertEquals(expResult, result);
    }
    /**
     * Test of getIssue method, of class RefJournal.
     */
    @Test
    void getIssue() {
        System.out.println("getJournal");
        String[]authors={"Author"};
        RefJournal instance =  new RefJournal("The Old Man and the Sea",authors,2006
                ,"Charles Scribner's Sons","500.6","Journal",1,5);

        int expResult = 5;
        int result = instance.getIssue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCitation method, of class RefJournal.
     */

    @Test
    public void testGetCitation() {
        System.out.println("getCitation");
        String[]authors={"Author"};
        RefJournal instance =  new RefJournal("Title",authors,2006
                ,"Publisher","500.3","Journal",1,1,2,2,2002);
        String expResult = "Title, (2006) , Author, Publisher, \"Journal\", vol.1 no.1 doi:500.3  02/02/2002 \n";
        String result = instance.getCitation();
        assertEquals(expResult, result);

    }


}
