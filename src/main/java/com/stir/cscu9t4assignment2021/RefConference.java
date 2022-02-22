package com.stir.cscu9t4assignment2021;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * @author Dion Kadriu
 * The class that handles the refConferences objects
 */
@XmlRootElement(name = "item")//setting the RefConference class as the attribute for its instances with the name item
@XmlType(propOrder = {"venue","location"})//setting the order of refConference elements
public class RefConference extends Ref {

    private String venue;
    private String location;

    public RefConference() {
    }

    public RefConference(String title, String[] authors, String doi, String publisher, int pubyear,
                         String venue, String location) {
        super(title, authors, doi, publisher, pubyear);
        this.venue = venue;
        this.location = location;
    }

    public RefConference(String title, String[] authors, String doi, String publisher, int pubyear, String venue,
                         String location, int day, int month, int year) {
        super(title, authors, doi, publisher, pubyear, day, month, year);
        this.venue = venue;
        this.location = location;
    }

    @XmlAttribute(name = "category")//setting the attribute to category with the return value of the RefConference class
    public String getType() {
        return RefConference.class.getSimpleName();
    }

    @XmlElement//setting the venue as an element of the xml
    public String getVenue() {
        return this.venue;
    }

    @XmlElement//setting the location as an element of the xml
    public String getLocation() {
        return this.location;
    }


    public String getCitation() {
        return getTitle() + ", (" + getPubyear() + ") " + String.join(", ", getAuthors()) +
                ", " + getPublisher() + ", " + venue + " in " + location + " doi:" + getDoi() + ", " + getDate() + "\n";
    }

}
