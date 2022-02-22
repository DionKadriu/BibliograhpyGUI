package com.stir.cscu9t4assignment2021;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * @author Dion Kadriu
 * The parent class with the common attributes that the child classes will have
 * */
@XmlType(propOrder = {"title", "authors", "pubyear", "publisher", "doi","date"})//setting the order for the elements of the xml
public class Ref {
    String validator="khsdkasdhk";
    private String title;
    private String authors[];
    private String doi;
    private String publisher;
    private int pubyear;
    private Date dateAdded;

    public Ref(){}

    public Ref(String title, String[] authors, String doi, String publisher, int pubyear) {
        this.title = title;
        this.authors = authors;
        this.doi = doi;
        this.publisher = publisher;
        this.pubyear = pubyear;
    }

    public Ref(String title, String[] authors, String doi, String publisher, int pubyear, int day, int month, int year) {
        this.title = title;
        this.authors = authors;
        this.doi = doi;
        this.publisher = publisher;
        this.pubyear = pubyear;

        if (day == 0 && month == 0 && year == 0) {
            dateAdded = new Date();
        } else {
            dateAdded = new Date(year - 1900, month - 1, day);
        }
    }


    @XmlElement//setting the title as an element of xml
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement//setting the authors as an element of xml
    public String[] getAuthors() {
        return authors;
    }

    @XmlElement//setting the doi as an element of xml
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    @XmlElement//setting the publisher as an element of xml
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @XmlElement//setting the date as an element of xml
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yyyy ");
        return sdf.format(dateAdded);
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }


    @XmlElement//setting the publication year as an element
    public int getPubyear() {
        return this.pubyear;
    }

    public String getCitation() {
        return "Ref{" +
                "validator='" + validator + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", doi='" + doi + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pubyear=" + pubyear +
                ", dateAdded=" + dateAdded +
                '}';
    }

    @Override
    public String toString() {
        return getCitation();
    }
}
