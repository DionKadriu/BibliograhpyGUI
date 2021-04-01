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

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 2835267
 */
public class RefCollectionTest {

    public RefCollectionTest() {
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
     * Test of addCite method, of class RefCollection.
     */
    @Test
    public void testAddCite() {
        System.out.println("addCite");

        Ref ref = new Ref("The Old Man and the Sea", new String[]{"author"}, "1000.0", "Charles Scribner's Sons", 2006);
        RefCollection instance = new RefCollection();
        instance.addCite(ref);


    }

    /**
     * Test of lookUpByJournal method, of class RefCollection.
     */
    @Test
    public void testLookUpByJournal() {
        System.out.println("lookUpByJournal");
        String journal = "journal";
        RefCollection instance = new RefCollection();
        RefJournal refJournal = new RefJournal("Title", new String[]{"Ernest Hemingway"}, 2006
                , "Publisher", "2006", journal, 1, 1, 2, 2, 2012);
        instance.addCite(refJournal);
        String expResult = "Title, (2006) , Ernest Hemingway, Publisher, \"journal\", vol.1 no.1 doi:2006  02/02/2012 \n";
        String result = instance.lookUpByJournal(journal);
        assertEquals(expResult, result);

    }

    /**
     * Test of loopUpByVenue method, of class RefCollection.
     */
    @Test
    public void testLoopUpByVenue() {
        System.out.println("loopUpByVenue");
        String venue = "Venue";
        RefCollection instance = new RefCollection();
        String[] authors = {"Ernest Hemingway Surname"};

        RefConference refConference = new RefConference("The Old Man and the Sea", new String[]{"Ernest Hemingway Surname"}, "1000.0"
                , "Charles Scribner's Sons", 2006, venue, "Kosovo", 2, 2, 2012);

        instance.addCite(refConference);

        String expResult = "The Old Man and the Sea, (2006) Ernest Hemingway Surname, Charles Scribner's Sons, Venue in Kosovo doi:1000.0,  02/02/2012 \n";
        String result = instance.lookUpByVenue(venue);
        assertEquals(expResult, result);
    }

    /**
     * Test of lookUpByPublisher method, of class RefCollection.
     */
    @Test
    public void testLookUpByPublisher() {
        System.out.println("lookUpByPublisher");
        String publisher = "publisher";
        RefCollection instance = new RefCollection();
        String[] authors = {"Ernest Hemingway"};
        Ref ref = new Ref("The Old Man and the Sea", authors, "1000.0", publisher, 2006, 2, 2, 2003);
        instance.addCite(ref);
        String expResult = "Ref{validator='khsdkasdhk', title='The Old Man and the Sea', authors=[Ernest Hemingway], doi='1000.0', publisher='publisher', pubyear=2006, dateAdded=Sun Feb 02 00:00:00 CET 2003}";
        String result = instance.lookUpByPublisher(publisher);
        assertEquals(expResult, result);

    }

    /**
     * Test of getNumberOfRefs method, of class RefCollection.
     */
    @Test
    public void testGetNumberOfRefs() {
        System.out.println("getNumberOfRefs");
        String type = "";
        RefCollection instance = new RefCollection();
        RefConference refConference = new RefConference("The Old Man and the Sea", new String[]{"Ernest Hemingway Surname"}, "1000.0"
                , "Charles Scribner's Sons", 2006, "venue", "Kosovo", 2, 2, 2012);
        RefJournal refJournal = new RefJournal("Title", new String[]{"Ernest Hemingway"}, 2006
                , "Publisher", "2006", "journal", 1, 1, 2, 2, 2012);
        instance.addCite(refConference);
        instance.addCite(refConference);
        instance.addCite(refJournal);
        int expResult = 2;
        int result = instance.getNumberOfRefs("Conference");
        assertEquals(expResult, result);

    }

    /**
     * Test of exportAll method, of class RefCollection.
     */
    @Test
    public void testExportToTxt() {
        System.out.println("exportAll");
        RefCollection instance = new RefCollection();
        String expResult = "Exported to txt to Test_files/testFileToTxt";
        String output = "The Old Man and the Sea, (2006) Ernest Hemingway Surname, \"The Old Man and the Sea\" , Charles Scribner's Sons, 2006 Venue in Kosovo doi:1000.0,  02/02/2012 \n";
        String result = instance.textFileCreator(output, "Test_files/testFileToTxt");
        assertEquals(expResult, result);
    }

    /**
     * * Test of importMany method, of class RefCollection.
     */
    @Test
    public void testTextFileCreator() throws IOException {
        BufferedReader bufferedReader = null;
        FileDialog fileExplorer = new FileDialog(new JFrame());//opening the fileExplorer to get the file for import
        fileExplorer.setVisible(true);
        File[] f = fileExplorer.getFiles();
        if (f.length > 0) {
            bufferedReader = new BufferedReader(new FileReader(fileExplorer.getFiles()[0].getAbsolutePath()));
        }

        assertNotNull(bufferedReader.readLine());

    }

    @Test
    void xmlCreation() {
        RefCollection refCollection= new RefCollection();
        refCollection.xmlCreation("create.xml");
        String expected="Xml file created";
        String actual = refCollection.xmlCreation("create.xml");
        assertEquals(expected,actual);
    }


    @Test
    void generatedCsvObj() throws FileNotFoundException {
        RefCollection refCollection = new RefCollection();
        String[] onlyJournal = new String[]{"title", "authors", "year",//array for the onlyJournals csv
                "publisher", "doi", "date", "journal", "volume", "issue"};
        String[] onlyConference = new String[]{"title", "authors", "year",//array for the onlyConferences csv
                "publisher", "doi", "date", "venue", "location"};
        String[] onlyBooks = new String[]{"title", "authors", "year",//array for the onlyBooks csv
                "publisher", "doi", "date", "bookTitle", "editor"};
        String testOnlJournal[] = "Title-E-8,Jason Adair,2009,ACM,1205/211,9/2/2021,journal-x,5,2".split(",");
        String testOnlyConference[] = "Title-C-9,Jason Adair,2005,Springer,282/965,1/8/2021,Conference-2,Location-P".split(",");
        String testOnlyBooks[] = "Title-D-0,Jason Adair,2006,ACM,202/1031,8/5/2021,book-a,Mina Mouse".split(",");

        String testAllData[] = "Title-F-4,Saemi Haraldsson,2002,IEEE,1011/1591,2/11/2021,,,,,,book-a,Elsa".split(",");


        RefJournal refJournal = new RefJournal("Title-E-8", new String[]{"Jason Adair"}, 2009, "ACM", "1205/211", "journal-x", 5, 2, 9, 2, 2021);
        RefConference refConference = new RefConference("Title-C-9", new String[]{"Jason Adair"}, "282/965", "Springer", 2005, "Conference-2", "Location-P", 1, 8, 2021);
        RefBookChapter refBookChapter = new RefBookChapter("Title-D-0", new String[]{"Jason Adair"}, "202/1031", "ACM", 2006, "book-a", "Mina Mouse", 8, 5, 2021);
        RefBookChapter refBookChapterAllData = new RefBookChapter("Title-F-4", new String[]{"Saemi Haraldsson"}, "1011/1591", "IEEE", 2002, "book-a", "Elsa", 2, 11, 2021);
        assertEquals(refJournal.getCitation(), refCollection.generatedCsvObj(onlyJournal, testOnlJournal).getCitation());
        assertEquals(refConference.getCitation(), refCollection.generatedCsvObj(onlyConference, testOnlyConference).getCitation());
        assertEquals(refBookChapter.getCitation(), refCollection.generatedCsvObj(onlyBooks, testOnlyBooks).getCitation());
        assertEquals(refBookChapterAllData.getCitation(), refCollection.generatedCsvObj(new String[]{""}, testAllData).getCitation());
    }


    @Test
    void dateValidation() {
        RefCollection dateTest= new RefCollection();
        dateTest.dateValidation("31","2","");
        assertFalse(dateTest.dateValidation("31","2","2013"));
        assertTrue(dateTest.dateValidation("2","1","2013"));
    }
}