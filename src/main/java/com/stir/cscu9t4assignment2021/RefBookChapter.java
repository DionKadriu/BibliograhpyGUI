package com.stir.cscu9t4assignment2021;
import com.sun.xml.txw2.annotation.XmlNamespace;

import javax.xml.bind.annotation.*;

/**
 * @author Dion Kadriu
 * The class that handles the refBookChapter objects
 */
@XmlRootElement(name = "item")//setting the root element to RefBookChapter class with the name of item
public class RefBookChapter extends Ref {

    private String book;
    private String editor;

    public RefBookChapter() {
    }

    public RefBookChapter(String title, String[] authors, String doi, String publisher, int pubyear, String book, String editor) {
        super(title, authors, doi, publisher, pubyear);
        this.book = book;
        this.editor = editor;
    }

    public RefBookChapter(String title, String[] authors, String doi, String publisher, int pubyear, String book, String editor, int day, int month, int year) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.book = book;
        this.editor = editor;
    }

    @XmlAttribute(name = "category")//setting the attribute to category with the return value of the RefBookChapter class
    public String getType() {
        return RefBookChapter.class.getSimpleName();
    }

    @XmlElement
    public String getBook() {
        return book;
    }

    @XmlElement
    public String getEditor() {
        return editor;
    }

    @Override
    public String getCitation() {

            return getTitle() + ", (" + getPubyear() + "), " + String.join(", ", getAuthors()) + ", in " + book
                    + ", " + getPublisher() + ", " + "ed: " + editor + ", doi:" + getDoi() + ", " + getDate() + "\n";
    }

    /**
     *
     * @optional
     */

}
