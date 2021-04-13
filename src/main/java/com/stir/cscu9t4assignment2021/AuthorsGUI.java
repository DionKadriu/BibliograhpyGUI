package com.stir.cscu9t4assignment2021;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/**
 * @author 2835267
 * class to give the possibility of adding authors manually
 */
public class AuthorsGUI extends JFrame implements ActionListener{

    private JLabel labname = new JLabel("Name and Surname:");
    private JTextField name = new JTextField(20);
    private JButton ok = new JButton("OK");

    private JButton cancel = new JButton("Cancel");

    private JTextArea outputArea = new JTextArea(20, 50);
    private JButton add = new JButton("ADD");

    private ArrayList<String> authors = new ArrayList<>();

    public static void main(String[] args) {
        AuthorsGUI authorsGUI = new AuthorsGUI();
    }

    public AuthorsGUI() {
        super("Add authors");

        setLayout(null);
        setSize(600, 400);
        labname.setBounds(5, 5, 150, 20);
        name.setBounds(160, 5, 300, 20);
        add(labname);
        add(name);

        add.setBounds(500, 5, 70, 20);
        ok.setBounds(200, 320, 100, 20);

        add(ok);
        cancel.addActionListener(this);
        cancel.setBounds(310, 320, 100, 20);
        add(cancel);
        outputArea.setBounds(80, 40, 450, 250);
        add(outputArea);
        outputArea.setVisible(true);
        add(add);

        add.addActionListener(this);

        cancel.addActionListener(e -> { //adding the action listener to set the visibility of gui and remove the authors from the gui
            setVisible(false);
            authors.removeAll(authors); //removing everything so the authors are not displayed
            outputArea.setText("");
        });
    }

    public JButton getCancel() { //method to be able refer from the main gui
        return cancel;
    }

    public JButton getOk() { //method to be able refer from the main gui
        return ok;
    }

    public ArrayList<String> getAuthors() {//method to be able refer to the authors from the main gui
        return authors;
    }

    public String addButton() {
        if (name.getText().equals("")) {
            return "Please enter a name"; //checking if the field is empty
        }
        authors.add(name.getText());

        return String.join("\n", authors); //returning the authors in a new line delimiter to be able to put them in the gui
    }

    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == add) {

            message = addButton();
            name.setText("");
        }
        outputArea.setText(message);
    }


}
