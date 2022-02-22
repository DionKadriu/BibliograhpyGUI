/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

/**
 * @author Dion Kadriu
 * This is the GUI class
 */
public class RefSystemGUI extends JFrame implements ActionListener {

    private AuthorsGUI authorsGUI;

    //-------------JTextFields-----------------//
    private JTextField authors = new JTextField(15);
    private JTextField title = new JTextField(15);// The title of the book
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField pubYear = new JTextField();
    private JTextField publisherName = new JTextField(12);
    private JTextField doi = new JTextField(10);
    private JTextField journalName = new JTextField(7);
    private JTextField volume = new JTextField(3);
    private JTextField issue = new JTextField(3);
    private JTextField conVenueName = new JTextField(7);
    private JTextField locationCon = new JTextField(7);
    private JTextField bookTitle = new JTextField(7);
    private JTextField editorName = new JTextField(7);


    private JTextArea outputArea = new JTextArea(20, 50);

    //------------------JLabels--------------------//
    private JLabel labAuthor = new JLabel("Author");
    private JLabel labt = new JLabel("Title:");
    private JLabel labd = new JLabel("Publish year:");
    private JLabel labNoP = new JLabel("Name of Publisher");
    private JLabel labDoi = new JLabel("DOI");
    private JLabel labdd = new JLabel("Day");
    private JLabel labmm = new JLabel("Month");
    private JLabel labyy = new JLabel("Year");
    private JLabel labNoJor = new JLabel("Journal Name:");
    private JLabel labVi = new JLabel("Volume:");
    private JLabel labIssue = new JLabel("Issue:");
    private JLabel labNoCon = new JLabel("Conference Venue:");
    private JLabel labLocation = new JLabel("Location:");
    private JLabel labBt = new JLabel("Book title:");
    private JLabel labEn = new JLabel("Editor Name:");

    //------------------JComboBox--------------------//
    private final String a_typePublished[] = new String[]{"Journal", "Conference Paper", "Book Chapters"};
    private JComboBox<String> typePublished = new JComboBox<>(a_typePublished);

    //---------------JButtons--------------//
    private JButton edit = new JButton("Edit");
    private JButton insert = new JButton("Insert", new ImageIcon("src/Icons/insertPic.png"));
    private JButton importButton = new JButton("Import file", new ImageIcon("src/Icons/fileFinder.png"));
    private final JButton exportButton = new JButton("Export all", new ImageIcon("src/Icons/export.png"));
    private final JButton summary = new JButton("Summary", new ImageIcon("src/Icons/summaryIcon .png"));
    private JButton findByJournal = new JButton("Find By Journal"
            , new ImageIcon("src/Icons/journal.png"));
    private JButton findByConference = new JButton("Find By Venue"
            , new ImageIcon("src/Icons/conference.png"));
    private final JButton findByPublisher = new JButton("Find By Publisher"
            , new ImageIcon("src/Icons/publisher.png"));

    private RefCollection bibliography = new RefCollection();

    public static void main(String[] args) {

        MyFrameAnimation animation = new MyFrameAnimation();

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RefSystemGUI applic = new RefSystemGUI();
        animation.setVisible(false);
    }


    public RefSystemGUI() {

        super("Bibliography");
        authorsGUI = new AuthorsGUI();
        authorsGUI.setVisible(false);
        setLayout(null);
        setSize(1300, 450);
        setLocationRelativeTo(null);// to put the location in the middle
        setEditB(false);

        edit.setBounds(60, 40, 60, 20);
        labAuthor.setBounds(5, 40, 150, 20);
        authors.setBounds(125, 40, 150, 20);

        add(labAuthor);
        add(authors);
        add(edit);

        labt.setBounds(5, 80, 150, 20);
        title.setBounds(120, 80, 150, 20);
        add(labt);
        add(title);

        labd.setBounds(5, 120, 150, 20);
        pubYear.setBounds(120, 120, 150, 20);
        add(labd);
        add(pubYear);
        labNoP.setBounds(5, 160, 150, 20);
        publisherName.setBounds(120, 160, 150, 20);
        add(labNoP);
        add(publisherName);

        labDoi.setBounds(5, 200, 150, 20);
        doi.setBounds(120, 200, 150, 20);
        add(labDoi);
        add(doi);

        findByJournal.setBounds(500, 320, 150, 30);
        add(findByJournal);
        findByJournal.addActionListener(this);
        findByConference.setBounds(670, 320, 150, 30);
        add(findByConference);
        findByConference.addActionListener(this);
        findByPublisher.setBounds(840, 320, 160, 30);
        add(findByPublisher);
        findByPublisher.addActionListener(this);

        importButton.setBounds(1070, 10, 120, 30);
        exportButton.setBounds(1070, 80, 120, 30);
        summary.setBounds(1070, 150, 120, 30);


        add(importButton);
        add(exportButton);
        add(summary);
        importButton.addActionListener(this);
        exportButton.addActionListener(this);
        summary.addActionListener(this);
        insert.setBounds(300, 320, 120, 30);
        add(insert);
        insert.addActionListener(this);


        outputArea.setBounds(300, 10, 720, 300);
        add(outputArea);
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBounds(300, 10, 720, 300);
        add(scroll);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        add(labdd);
        add(day);
        labdd.setBounds(5, 320, 35, 20);
        day.setBounds(35, 320, 30, 20);

        add(labmm);
        add(month);
        labmm.setBounds(85, 320, 40, 20);
        month.setBounds(125, 320, 30, 20);

        add(labyy);
        add(year);
        year.setBounds(200, 320, 40, 20);
        labyy.setBounds(170, 320, 35, 20);


        outputArea.setVisible(true);

        setVisible(true);

        edit.addActionListener(e -> authorsGUI.setVisible(true));

        authorsGUI.getOk().addActionListener(e -> {
            authors.setText(String.join(";", authorsGUI.getAuthors()));//needs to be fixed
            authorsGUI.setVisible(false);
        });

        authorsGUI.getCancel().addActionListener(e -> authors.setText(""));

        typePublished.setBounds(5, 5, 200, 20);
        add(typePublished);

        typePublished.setSelectedIndex(-1);
        typePublished.setRenderer(new DefaultListCellRenderer() { //rendering the dropdown in order to not see the first title
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {

                if (index == -1 && value == null) {//when the file is at position -1 the dropdown text is set to Bibliography type
                    setText("Bibliography Type");
                } else {
                    setText(value.toString());
                }
                if (isSelected) {
                    setBackground(Color.PINK); //setting the color of the selected field to pink
                } else {
                    setBackground(Color.lightGray); // setting the background of the possibilities to lightGray
                }
                return this;
            }
        });

        typePublished.addActionListener(e -> { //adding the action listener to make the GUI add the fields of each selection type
            setEditB(true);
            switch (typePublished.getSelectedIndex()) {

                case 0:


                    addingFields(labNoJor, journalName, labVi, volume);
                    removeFields(labNoCon, conVenueName, labLocation, locationCon);
                    removeFields(labBt, bookTitle, labEn, editorName);
                    volume.setBounds(65, 280, 50, 20);
                    labIssue.setBounds(135, 280, 60, 20);
                    issue.setBounds(180, 280, 40, 20);
                    add(labIssue);
                    add(issue);
                    repaint(); //repainting the screen after adding the new fields

                    break;
                case 1:

                    addingFields(labNoCon, conVenueName, labLocation, locationCon);
                    removeFields(labBt, bookTitle, labEn, editorName);
                    removeFields(labNoJor, journalName, labVi, volume);
                    remove(issue);
                    remove(labIssue);

                    repaint();//repainting the screen after adding the new fields
                    break;
                case 2:

                    addingFields(labBt, bookTitle, labEn, editorName);
                    removeFields(labNoJor, journalName, labVi, volume);
                    removeFields(labNoCon, conVenueName, labLocation, locationCon);
                    remove(issue);
                    remove(labIssue);
                    repaint();//repainting the screen after adding the new fields
                    break;
            }
        });

    }

    /**
     * method to add the fields for the values
     *
     * @param lab1
     * @param textField1
     * @param lab2
     * @param textfield2
     */
    private void addingFields(JLabel lab1, JTextField textField1, JLabel lab2, JTextField textfield2) { //method to add the new fields for different types based on the label and textFields sent
        lab1.setBounds(5, 240, 150, 20);
        textField1.setBounds(120, 240, 150, 20);
        add(lab1);
        add(textField1);
        lab2.setBounds(5, 280, 150, 20);
        textfield2.setBounds(120, 280, 150, 20);
        add(lab2);
        add(textfield2);

    }

    /**
     * method to remove the Fields not needed for a specific journal
     */

    public void removeFields(JLabel lab1, JTextField textField1, JLabel lab2, JTextField textfield2) {//method to remove the new fields based on the new types

        remove(lab1);
        remove(textField1);

        remove(lab2);
        remove(textfield2);

    }

    public String insertReference(String what) {
        Date date = new Date();

        String message = "Record added";
        //setting the object parameters based on the GUI textfields
        String titleText = title.getText();
        String[] authorText = authors.getText().split(";");
        String doiText = doi.getText();
        String publisherText = publisherName.getText();
        int d, m, y;
        int py;
        try {

            py = Integer.parseInt(pubYear.getText());
            if (py < 1600 && py > date.getYear() + 1900) { //if the publication year something else from ints or not between the specified range show exception
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return "Publish year cannot be outside the range of the today's date and 1600";
        } catch (IllegalArgumentException npe) {
            return "Publisher year cannot be text";
        }

        try {
            if (day.getText().equals("") || month.getText().equals("") || year.getText().equals("")) {
                outputArea.setForeground(Color.RED);
                throw new IllegalArgumentException();//throwing an exception if one of the textfields is empty
            }

            d = Integer.parseInt(day.getText());
            m = Integer.parseInt(month.getText());
            y = Integer.parseInt(year.getText());

            if (!bibliography.dateValidation(Integer.toString(d), Integer.toString(m), Integer.toString(y))) {//calling the method to check the date based on the string value
                outputArea.setForeground(Color.RED);
                return "The date is invalid";
            }

        } catch (IllegalArgumentException s) {//when one of the fields for date is empty return todays date
            d = 0;
            m = 0;
            y = 0;
        }

        switch (typePublished.getSelectedIndex()) {
            case 0://creating the refJournal object and adding it
                String nJ = journalName.getText();
                int vi, i;
                try {
                    vi = Integer.parseInt(volume.getText());
                    i = Integer.parseInt(issue.getText());
                } catch (IllegalArgumentException ex) {
                    outputArea.setForeground(Color.RED);
                    return "Volume and issue should be numbers";
                }
                RefJournal refJournal =
                        new RefJournal(titleText, authorText, py, publisherText, doiText, nJ, vi, i, d, m, y);
                what = "Journal";
                bibliography.addCite(refJournal);
                System.out.println(refJournal.toString());
                break;
            case 1://creating the refConference object and adding it
                String cvn = conVenueName.getText();
                String loc = locationCon.getText();

                RefConference refConference =
                        new RefConference(titleText, authorText, doiText, publisherText, py, cvn, loc, d, m, y);

                what = "Conference";
                bibliography.addCite(refConference);
                System.out.println(refConference.toString());
                break;
            case 2:////creating the refBook object and adding it

                String b = bookTitle.getText();
                String e = editorName.getText();
                RefBookChapter refBookChapter =
                        new RefBookChapter(titleText, authorText, doiText, publisherText, py, b, e, d, m, y);
                what = "Book Chapter";
                System.out.println(refBookChapter.toString());
                bibliography.addCite(refBookChapter);
                break;
        }
        System.out.println("Adding a " + what);
        return message;
    }


    public void actionPerformed(ActionEvent event) {//method for the buttons action listeners
        String message = "";
        if (event.getSource() == insert) {//insert button call
            message = insertReference("generic");
        }
        if (event.getSource() == importButton) {
            FileDialog fileExplorer = new FileDialog(new JFrame());//opening the fileExplorer to get the file for import
            fileExplorer.setVisible(true);
            File[] f = fileExplorer.getFiles();
            if (f.length > 0) {
                System.out.println(fileExplorer.getFiles()[0].getAbsolutePath());
                message = bibliography.importCsv(fileExplorer.getFiles()[0].getAbsolutePath());//getting the file absolute path
            }
//            }
        }

        if (event.getSource() == exportButton) {
            String[] choices = new String[]{"Export to text file", "Export to XML file"};
            JPanel jPanel = new JPanel();
            int result = JOptionPane.showOptionDialog(jPanel, "Select the format you want the data to be exported", "Export"
                    , JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Icons/JExport.png"), choices, choices[0]);//opening a dialog to ask the user what he wants ot export to
            if (result == 0) {//if the user chooses text field file export
                FileDialog fd = new FileDialog(new JFrame(), "Choose the location and give the type name", FileDialog.SAVE);
                fd.setVisible(true);
                String filepath = fd.getDirectory() + fd.getFile();
                message = bibliography.textFileCreator(outputArea.getText(), filepath);//opening a dialog to ask for the name of the file
            } else {
//if the user chooses the xml export
                message = bibliography.xmlCreation((String) JOptionPane.showInputDialog
                        (null, "How do you want to name your xml file",
                                "To Xml File", JOptionPane.PLAIN_MESSAGE,
                                new ImageIcon("src/Icons/Jxml.png"),
                                null, null));
            }

        }
        if (event.getSource() == findByJournal) {//call for findByJournal button
            message = bibliography.lookUpByJournal((String) JOptionPane.showInputDialog
                    (null, "Enter the name of the venue you want to search for",
                            "Find By Venue", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon("src/Icons/JJournal.png"), null, null));//dialof to ask the name that the user wants ot search for
        }
        if (event.getSource() == findByConference) {//call for findByConference button
            message = bibliography.lookUpByVenue((String) JOptionPane.showInputDialog
                    (null, "Enter the name of the venue you want to search for",
                            "Find By Venue", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon("src/Icons/JVenue.png"),
                            null, null));//dialog to ask for the name the user want to search for
        }
        if (event.getSource() == findByPublisher) {//call for findByBookChapter button
            message = bibliography.lookUpByPublisher((String) JOptionPane.showInputDialog//dialog to ask for the name the user wants to search for
                    (null, "Enter the name of the publisher you want to search for",
                            "Find By Publisher", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon("src/Icons/JPublisher.png"),
                            null, null));
        }
        if (event.getSource() == summary) {//if the user wants an html based on the methods created to find most common and the count
            message = bibliographySummary();
        }
        outputArea.setForeground(Color.black);
        outputArea.setText(message);
        blankDisplay();
    }

    public String bibliographySummary() {
        PrintWriter htmlSummary = null;
        try {
            htmlSummary = new PrintWriter("bibliographySummary.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        htmlSummary.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<title>Bibliography Summary</title>\n" +
                " <font  color=\"red\" size=\"+4\"><b>Bibliography Summary</b></font><br></br><br></br>\n" +
                "\n" +
                "<font size=\"+3\" ><b> Journal Articles </b><br></br>" +
                " <font size=\"+2\" >Number of Journal Articles: " + bibliography.getNumberOfRefs("Journal") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Journal: " + bibliography.getMostCommon("journals") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Publisher: " + bibliography.getMostCommon("publishers") + "</font><br></br>\n" +
                "<font size=\"+3\" > <b>Conference Papers </b><br></br>" +
                " <font size=\"+2\">Number of Conference Paper: " + bibliography.getNumberOfRefs("Conference") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Venue: " + bibliography.getMostCommon("venues") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Location: " + bibliography.getMostCommon("locations") + "</font><br></br>\n" +
                "<font size=\"+3\" > <b>Book Chapters </b><br></br>" +
                " <font size=\"+2\">Number of Book Chapters: " + bibliography.getNumberOfRefs("Book") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Book: " + bibliography.getMostCommon("book") + "</font><br></br>\n" +
                " <font size=\"+2\">Most Frequent Publisher: " + bibliography.getMostCommon("publishers") + "</font<br></br>\n" +
                "</body>\n" +
                "</html>");
        htmlSummary.close();
        return "exported to the html page";
    }

    public void blankDisplay() {//setting all the fields to nothing
        authors.setText("");
        title.setText("");
        pubYear.setText("");
        publisherName.setText("");
        doi.setText("");
        journalName.setText("");
        volume.setText("");
        issue.setText("");
        bookTitle.setText("");
        editorName.setText("");
        conVenueName.setText("");
        locationCon.setText("");
        day.setText("");
        month.setText("");
        year.setText("");

    }

    public void setEditB(boolean set) {  //sets the fields to enabled or disabled based on the the set parameter
        edit.setEnabled(set);
        authors.setEditable(set);
        title.setEditable(set);
        pubYear.setEditable(set);
        publisherName.setEditable(set);
        doi.setEditable(set);
        day.setEditable(set);
        month.setEditable(set);
        year.setEditable(set);
    }

}

