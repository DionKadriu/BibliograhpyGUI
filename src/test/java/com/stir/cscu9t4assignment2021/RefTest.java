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

import javax.management.StandardEmitterMBean;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2835267
 *
 *
 *
 *
 */
public class RefTest {

    public RefTest() {
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
     * Test of getTitle method, of class Ref.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Ref instance = new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006);
        String expResult = "The Old Man and the Sea";
        String result = instance.getTitle();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAuthors method, of class Ref.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        Ref instance =  new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006);
        String []expResult = {"Author"};
        String []result = instance.getAuthors();
        assertEquals(expResult, result);


    }

    /**
     * Test of getPubYear method, of class Ref.
     */
    @Test
    public void testGetPubYear() {
        System.out.println("getPubYear");
        Ref instance =  new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006);
        int expResult = 2006;
        int result = instance.getPubyear();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPublisher method, of class Ref.
     */
    @Test
    public void testGetPublisher() {
        System.out.println("getPublisher");
        Ref instance =  new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006);
        String expResult = "Charles Scribner's Sons";
        String result = instance.getPublisher();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDoi method, of class Ref.
     */
    @Test
    public void testGetDoi() {
        System.out.println("getDoi");
        Ref instance =  new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006);
        String expResult = "1000.0";
        String result = instance.getDoi();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDateAdded method, of class Ref.
     */
    @Test
    public void testGetDateAdded() {
        System.out.println("getDateAdded");

        Ref instance = new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006,21,11,2001);
        Date expResult = new Date(2001-1900, Calendar.NOVEMBER,21);
        Date result = instance.getDateAdded();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCitation method, of class Ref.
     */
    @Test
    public void testGetCitation() {
        System.out.println("getCitation");
        Ref instance =  new Ref("The Old Man and the Sea",new String[]{"Author"},"1000.0","Charles Scribner's Sons",2006,2,2,2012);
        String expResult = "Ernest Hemingway, \"The Old Man and the Sea\" , Charles Scribner's Sons, 2006  02/02/2012 ";
        String result = instance.getCitation();
        assertEquals(expResult, result);

    }

}
