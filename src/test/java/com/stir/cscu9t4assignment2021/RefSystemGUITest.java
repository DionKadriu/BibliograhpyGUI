/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.events.Event;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 2835267
 */
public class RefSystemGUITest {

    public RefSystemGUITest() {
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
     * Test of main method, of class RefSystemGUI.
     * Just tests if the GUI initiates without explicit fail
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        RefSystemGUI.main(args);
    }

    /**
     * Test of actionPerformed method, of class RefSystemGUI.
     * You might want to add an action and a few more test cases with
     * different actions
     */

    @Test
    public void testActionPerformed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("Action not performed");
    }

    /**
     * Test of blankDisplay method, of class TrainingRecordGUI.
     * It just executes the method to see if it doesn't throw an exception
     */
    @Test
    public void testBlankDisplay() {
        System.out.println("blankDisplay");
        RefSystemGUI instance = new RefSystemGUI();
        instance.blankDisplay();
    }


    /**
     * Test to see if all display requirements have been met
     */
    @Test
    public void testButtonsPresentInGUI() throws IllegalAccessException, IllegalArgumentException {
        System.out.println("Check if you have added the buttons");
        RefSystemGUI instance = new RefSystemGUI();
        Class<?> instanceClass = instance.getClass();
        String[] expectedFields = {"findByJournal", "findByConference", "findByPublisher", "insert", "importButton", "exportButton", "summary"}; // add RemoveEntry when it is ready
        Field fields[] = instanceClass.getDeclaredFields();
        int found = 0;
        for (Field field : fields) {
            if (Arrays.asList(expectedFields).contains(field.getName())) {
                found += 1;
                field.setAccessible(true);
                assertTrue(field.get(instance) instanceof JButton);
            }
        }
        assertEquals(found, expectedFields.length, "Have you added all required buttons?");
    }

    @Test
    public void testJComboBoxPresentInGUI() throws IllegalAccessException, IllegalArgumentException {
        System.out.println("Check if you have added the buttons");
        RefSystemGUI instance = new RefSystemGUI();
        Class<?> instanceClass = instance.getClass();
        String[] expectedFields = {"typePublished"}; // add RemoveEntry when it is ready
        Field fields[] = instanceClass.getDeclaredFields();
        int found = 0;
        for (Field field : fields) {
            if (Arrays.asList(expectedFields).contains(field.getName())) {
                found += 1;
                field.setAccessible(true);
                assertTrue(field.get(instance) instanceof JComboBox);
            }
        }
        assertEquals(found, expectedFields.length, "Have you added all required buttons?");
    }

    @Test
    public void testJtextFieldsPresentInGUI() throws IllegalAccessException, IllegalArgumentException {
        System.out.println("Check if you have added the buttons");
        RefSystemGUI instance = new RefSystemGUI();
        Class<?> instanceClass = instance.getClass();
        String[] expectedFields = {
                "title", "day", "month", "year", "pubYear", "publisherName", "doi", "journalName", "volume", "issue", "conVenueName", "locationCon","bookTitle", "editorName"}; // add RemoveEntry when it is ready
        Field fields[] = instanceClass.getDeclaredFields();
        int found = 0;
        for (Field field : fields) {
            if (Arrays.asList(expectedFields).contains(field.getName())) {
                found += 1;
                field.setAccessible(true);
                assertTrue(field.get(instance) instanceof JTextField);
            }
        }
        assertEquals(found, expectedFields.length, "Have you added all required buttons?");
    }



}
