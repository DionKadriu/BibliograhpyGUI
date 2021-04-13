package com.stir.cscu9t4assignment2021;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "item")//setting the root and attribute with the name item
@XmlType(propOrder = {"journal", "volume", "issue"})//setting the order for elements
public class RefJournal extends Ref {
    private String journal;
    private int volume;
    private int issue;

    public RefJournal() {
    }

    public RefJournal(String title, String[] authors, int pubyear, String publisher, String doi,
                      String journal, int volume, int issue) {
        super(title, authors, doi, publisher, pubyear);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
    }

    public RefJournal(String title, String[] authors, int pubyear, String publisher, String doi, String journal,
                      int volume, int issue, int day, int month, int year) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
    }

    @XmlAttribute(name = "category")//setting the attribute to category with the return value of the RefJournal class
    public String getType() {
        return RefJournal.class.getSimpleName();
    }

    @XmlElement//setting the element of the journal name for the xml
    public String getJournal() {
        return journal;
    }

    @XmlElement//setting the element of the volume name for the xml
    public int getVolume() {
        return volume;
    }

    @XmlElement//setting the element of the issue name for the xml
    public int getIssue() {
        return issue;
    }

    @Override
    public String getCitation() {

        return getTitle() + ", (" + getPubyear() + ") " +
                ", " + String.join(", ", getAuthors()) + ", " + getPublisher() + ", \""
                + getJournal() + "\", " + "vol." + getVolume() + " no." + getIssue() + " doi:" + getDoi() + " " + getDate() + "\n";
    }


    @Override
    public String toString() {//to String
        return getCitation();
    }
}
