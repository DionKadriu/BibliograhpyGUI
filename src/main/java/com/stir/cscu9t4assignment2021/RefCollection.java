package com.stir.cscu9t4assignment2021;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;


public class RefCollection {
    private List<Ref> list;

    public RefCollection() {
        list = new ArrayList<>();
    }

    public void addCite(Ref ref) {
        list.add(ref);
    }

    public String lookUpByJournal(String journal) {

        String journals = list.stream().sorted(Comparator.comparing(o -> o.getAuthors()[0]))    //iterating through the elements and sorting based on the first author
                .filter(refJ -> refJ instanceof RefJournal && ((RefJournal) refJ).getJournal().equalsIgnoreCase(journal)) //getting all the instances of refJournal that are the same as the name
                .map(Ref::getCitation).collect(Collectors.joining());//getting the data in the form of method getCitation to display them and joining them in order to return string
        return journals.isBlank() ? "No journals found for the journal name given" : journals;
    }

    public String lookUpByVenue(String venue) {
        String venues = list.stream().sorted(Comparator.comparing(ref -> ref.getAuthors()[0]))//iterating through the elements and sorting based on the first author
                .filter(refV -> refV instanceof RefConference && ((RefConference) refV).getVenue().equalsIgnoreCase(venue))//getting all the instances of refConference that are the same as the name
                .map(Ref::getCitation).collect(Collectors.joining());//getting the data in the form of method getCitation to display them and joining them in order to return string
        return venues.isBlank() ? "No venues found with the venue name given" : venues;
    }

    public String lookUpByPublisher(String publisher) {

        String publishers = list.stream().sorted(Comparator.comparing(o -> o.getAuthors()[0])).//iterating through the elements and sorting based on the first author
                filter(o -> o.getPublisher().equalsIgnoreCase(publisher)).//getting all the publisher that are the same as the parameter
                map(Ref::getCitation).collect(Collectors.joining());//getting the data in the form of method getCitation to display them and joining them in order to return string
        return publishers.isBlank() ? "There are no publishers with the name given" : publishers;
    }

    public int getNumberOfRefs(String type) {
        if (type.equals("Journal")) {
            return (int) list.stream().filter(refJ -> refJ instanceof RefJournal).count();// counting only instances of RefJournal
        }
        if (type.equals("Conference")) {
            return (int) list.stream().filter(refC -> refC instanceof RefConference).count();// counting only instances of RefConference
        }
        if (type.equals("Book")) {
            return (int) list.stream().filter(refB -> refB instanceof RefBookChapter).count();// counting only instances of RefBookChapter
        }
        return 0;
    }

    public String getMostCommon(String reference) {//method to create list that contain the elements to find the most commong in them
        List<String> commonList = null;
        switch (reference) {
            case "journals":
                commonList = list.stream().filter(ref -> ref instanceof RefJournal).map(ref -> ((RefJournal) ref).getJournal()).collect(Collectors.toList());//creating an array of only journals
                break;
            case "publishers":
                commonList = list.stream().map(Ref::getPublisher).collect(Collectors.toList());//creating an array of only publishers
                break;
            case "venues":
                commonList = list.stream().filter(ref -> ref instanceof RefConference).map(ref -> ((RefConference) ref).getVenue()).collect(Collectors.toList());//creating an array of only venues
                break;
            case "locations":
                commonList = list.stream().filter(ref -> ref instanceof RefConference).map(ref -> ((RefConference) ref).getLocation()).collect(Collectors.toList());//creating an array of only locations
                break;
            case "book":
                commonList = list.stream().filter(ref -> ref instanceof RefBookChapter).map(ref -> ((RefBookChapter) ref).getBook()).collect(Collectors.toList());//creating an array of only books

                break;
        }


        return mostCommon(commonList);
    }

    public String mostCommon(List<String> a) {
        Map<String, Integer> map = new HashMap<>();
        for (String i : a) {
            Integer count = map.get(i);
            map.put(i, count != null ? count + 1 : 1);//setting the key to the array of strings and the value to their occurrences
        }
        return Collections.max(map.entrySet(),
                Map.Entry.comparingByValue()).getKey();//returning the key of the biggest number in the count
    }

    public String xmlCreation(String filename) {

        JAXBContext jaxbContestJournal;
        JAXBContext jaxbContextConference;
        JAXBContext jaxbContextBook;
        try {
            jaxbContestJournal = JAXBContext.newInstance(RefJournal.class); //creating the refJournal object container
            jaxbContextConference = JAXBContext.newInstance(RefConference.class);//creating the refConference object container
            jaxbContextBook = JAXBContext.newInstance(RefBookChapter.class);//creating the refBookChapter object container

            //the Java representation of the XML content into XML content
            Marshaller marshallerBook = jaxbContextBook.createMarshaller();
            Marshaller marshallerJournal = jaxbContestJournal.createMarshaller();
            Marshaller marshallerConference = jaxbContextConference.createMarshaller();

            //setting the o
            marshallerJournal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerConference.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerBook.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Print XML String to Console
            StringWriter sw = new StringWriter();//taking the data into a string builder to construct a string

            //Write XML to StringWriter
            for (Ref ref : list) {

                if (ref instanceof RefJournal) {
                    marshallerJournal.marshal(ref, sw);//getting the instances of Ref Journal inside the RefJournal attribute
                }
                if (ref instanceof RefConference) {
                    marshallerConference.marshal(ref, sw);//getting the instances of RefConference inside the RefConference attribute
                }
                if (ref instanceof RefBookChapter) {
                    marshallerBook.marshal(ref, sw);//getting the instances of RefBookChapter inside the RefBookChapter attribute
                }

            }
            //Verify XML Content
            String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<bibliography>" +
                    sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");//replacing other fields to nothing
            PrintWriter printWriter;
            if (filename.contains(".xml")) {
                printWriter = new PrintWriter(filename);
            } else {
                printWriter = new PrintWriter(filename + ".xml");//printing the file into a new file
            }
            System.out.println(sw);

            printWriter.println(xmlContent);
            printWriter.print("</bibliography>");//closing the bibliography
            printWriter.close();

        } catch (JAXBException | FileNotFoundException e) {
            return "The xml creation is invalid";
        }
        System.out.println("created the xml file successfully");
        return "Xml file created";
    }

    public String textFileCreator(String output, String txtFileName) {//method to create text file based on the output area in the GUI
        PrintWriter printWriter;
        try {
            if (output.equals("")) {//throwing an exception if the output area is empty
                throw new Exception();
            }
            if (txtFileName.contains(".txt")) {//checking if the textFile contains .txt to not add the txt part
                printWriter = new PrintWriter(txtFileName);
            } else {
                printWriter = new PrintWriter(txtFileName + ".txt");
            }
            printWriter.print(output);//printing everything to the exported txt file
            printWriter.close();

        } catch (FileNotFoundException e) {
            return "";
        } catch (Exception e) {
            return "the output area is empty therefore you cannot export the file";

        }
        return "Exported to txt to "+ txtFileName;
    }


    public String importCsv(String filepath) {
        String line;
        final String[] header;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));//reading the file based on the filepath passed
            if ((line = bufferedReader.readLine()) != null) {
                header = line.split(",");//getting the first line as the header in order to compare
                while ((line = bufferedReader.readLine()) != null) {
                    String otherlines[] = line.split(",");//splitting the csv file based on a comma
                    addCite(generatedCsvObj(header, otherlines));//adding the object based on the instances returned by the method

                }
            }
        } catch (IOException fileNotFoundException) {

            return "The file was not found";
        }
        return "The data has been imported";
    }

    public Ref generatedCsvObj(String[] header, String[] csvElement) {
        String[] onlyJournal = new String[]{"title", "authors", "year",//array for the onlyJournals csv
                "publisher", "doi", "date", "journal", "volume", "issue"};
        String[] onlyConference = new String[]{"title", "authors", "year",//array for the onlyConferences csv
                "publisher", "doi", "date", "venue", "location"};
        String[] onlyBooks = new String[]{"title", "authors", "year",//array for the onlyBooks csv
                "publisher", "doi", "date", "bookTitle", "editor"};

        //setting the values based on the csv elements
        String title = csvElement[0];
        String[] author = csvElement[1].split(";");
        int pubyear = Integer.parseInt(csvElement[2]);
        String publisher = csvElement[3];
        String doi = csvElement[4];
        String[] date = csvElement[5].split("/");//splitting the date based on the / regex into an array in order to access it
        int day, month, year;
        try {
            day = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            year = Integer.parseInt(date[2]);
        } catch (Exception exception) {//if the date is empty add the todays date
            day = 0;
            month = 0;
            year = 0;
        }

        if ((Arrays.equals(header, onlyJournal)) || (csvElement.length == 9)) {//if the header is the same for onlyJournal or is in the length of add corrected file create a Journal
            System.out.println("adding a journal");
            String journal = csvElement[6];
            int volume = Integer.parseInt(csvElement[7]);
            int issue = Integer.parseInt(csvElement[8]);
            RefJournal ref = new RefJournal(title,
                    author, pubyear,
                    publisher,
                    doi,
                    journal,
                    volume, issue, day, month, year);
            System.out.println(ref);//printing just to show that its adding them to the GUI
            return ref;
        }

        if (Arrays.equals(header, onlyConference) || (csvElement.length > 9 && csvElement.length < 12)) {//if the header is the same for onlyConference or is in the length of addcorrected file create a Conferenece
            String venue, location;
            System.out.println("adding a conference");
            if (!Arrays.equals(header, onlyConference)) {//if the file is from the all the data files add the corresponding fields
                venue = csvElement[9];
                location = csvElement[10];
            } else {
                venue = csvElement[6];
                location = csvElement[7];
            }
            RefConference ref = new RefConference(title, author
                    , doi, publisher, pubyear, venue, location, day, month, year);

            System.out.println(ref);//printing just to show that its adding them to the GUI
            return ref;
        }
        if (Arrays.equals(header, onlyBooks) || (csvElement.length >= 12)) {//if the header is the same for onlyBookChapter or is in the length of add corrected file create a Journal
            System.out.println("adding a book chapter");
            String editor;
            String book;
            if (!Arrays.equals(header, onlyBooks)) {//if the file is from the all the data files add the corresponding fields
                book = csvElement[11];
                if (csvElement.length == 12) {
                    editor = "";
                } else {
                    editor = csvElement[12];
                }
            } else {
                book = csvElement[6];
                editor = csvElement[7];
            }
            RefBookChapter ref = new RefBookChapter(title,
                    author
                    , doi, publisher, pubyear, book, editor, day, month, year);
            System.out.println(ref);//printing just to show that its adding them to the GUI
            return ref;
        }


        return null;

    }


    public boolean dateValidation(String d, String m, String y) {//method for date validation
        try {
            LocalDate.parse(y + "-" + m + "-" + d, DateTimeFormatter.ofPattern("uuuu-M-d").
                    withResolverStyle(ResolverStyle.STRICT));//solving year-month and day-of-month in the ISO calendar system using strict mode will ensure that the day-of-month is valid for the year-month, rejecting invalid values.
            return true;//return true if the date is in the valid range
        } catch (DateTimeParseException e) {
            return false;
        }

    }
}