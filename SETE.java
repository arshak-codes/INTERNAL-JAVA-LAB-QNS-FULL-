//CSL203 OOP JAVA LAB ----- ARSHAK MUHAMMED P K, R3A, 72
// Implement a java program for the following scenario
// Define a class with data members Citizen Name, Country, & Social Security Number (SSN) and isValid(). 
// The Social Security number is a nine-digit number in the format "AA-GGG-SSSS”. 
// Check the social security number is valid by using an isValid() method. 
// If the social security number is valid, write the name, Country and SSN number into a file. 
// If SocSecException is thrown, it should catch it and print the name, SSN, and the associated
// error message indicating why the SSN is invalid in the console.
// isValid() method:
// a.This method throw SocSeException
// b.Return true if the SSN number is valid, false otherwise
// c.The method checks the following errors and throws a SocSecExceotion with the appropriate message
// i.Number of characters not equal to 11 characters
// ii.Dashes in the wrong spot
// iii.Any non-digit in the SSN

import java.io.*;

// Class representing a Citizen with name, country, and social security number (ssn)
class Citizen {
    String name, country, ssn;

    // Constructor to initialize Citizen object with name, country, and ssn
    Citizen(String name, String country, String ssn) {
        this.name = name;
        this.country = country;
        this.ssn = ssn;
    }

    // Method to check if the Citizen's SSN is valid and save the details to a file
    public boolean isValid() throws SocSecException {
        // Check if the SSN is valid
        if (ssnvalid()) {
            try (PrintWriter pw = new PrintWriter(new File("Myfile.txt"))) {
                // Write Citizen details to the file
                pw.println(name);
                pw.println(country);
                pw.println(ssn);
                System.out.println("File created successfully!");
                return true;
            } catch (FileNotFoundException e) {
                // Handle file not found exception
                e.printStackTrace();
                return false;
            }
        } else {
            // Throw exception if SSN is not valid
            throw new SocSecException("Error creating File!!");
        }
    }

    // Method to validate the format and structure of the SSN
    public boolean ssnvalid() throws SocSecException {
        // Check if SSN length is 11 characters
        if (ssn.length() != 11) {
            throw new SocSecException("SSN MUST BE 11 CHARACTERS!");
        }
        // Check if SSN has hyphens at the correct positions
        if (ssn.charAt(2) != '-' && ssn.charAt(6) != '-') {
            throw new SocSecException("SSN NOT IN CORRECT FORMAT!");
        }
        // Replace hyphens and check if the remaining characters are digits
        String ssnmod = ssn.replaceAll("-", "2");
        if (!ssnmod.matches("\\d+")) {
            throw new SocSecException("SSN MUST CONTAIN ONLY INTEGERS!");
        } else {
            // SSN is valid
            return true;
        }
    }
}

// Custom exception class for handling social security number (SSN) related exceptions
class SocSecException extends Exception {
    public SocSecException(String msg) {
        super(msg);
    }
}

// Main class for testing the Citizen class and SSN validation
public class SETE {
    public static void main(String[] args) {
        // Create a Citizen object with sample details
        Citizen c1 = new Citizen("Arshak", "India", "11-222-3333");
        try {
            // Call isValid method and handle SocSecException
            c1.isValid();
        } catch (SocSecException e) {
            // Print error message if SocSecException is caught
            System.err.println("Error " + e.getMessage());
        }
    }
}
