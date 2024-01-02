//CSL203 OOP JAVA LAB ----- ARSHAK MUHAMMED P K, R3A, 72

// Implement a java program for the following scenario
// Define a class with data members username, password and phone_number and isValid ().
// The function isValid () checks the validity of password and phone number.
// If the password and phone_number is valid, write the user’s name and phone_number into a file. 
// If it is not valid, raise an exception with appropriate error message indicating why the password or 
// phone_number is invalid in the console.
// isValid () method:
// i. length of password should be in between 8 and 16
// ii. Password should include a digit and an uppercase letter.
// iii. Phone_number should be 10-digit number and all the elements should be digits.
// (use the concept of this and constructor overloading)

import java.io.*;
import java.util.*;
import java.lang.*;

class Data{
    String username,password,phn_no;
    // Constructor to initialize data members
    Data(String username,String password, String phn_no){
        this.username = username;
        this.password = password;
        this.phn_no = phn_no;
    }
    
    // Method to validate the user data
    public void isValid() throws Exception{
        PrintWriter pw = null;
        // If both password and phone number are valid, write to a file
        if(isValidPassword() && isValidPhoneNumber()){
            pw = new PrintWriter(new File("myfile.txt"));
            pw.println("Username:"+username);
            pw.println("Password:"+password);
            System.out.println("User information written to file successfully.");
        }
        pw.flush();
        pw.close();
    }

        // Method to validate the password
    public boolean isValidPassword() throws Exception{
        if(password.length()<8 || password.length()>16){
            throw new Exception("Invalid Password! Length should be between 8 and 16");
        }

        boolean containsDigit = false;
        boolean containsUpperCase = false;
        // Check each character in the password
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)){
                containsDigit = true;
            }
            else if(Character.isUpperCase(c)){
                containsUpperCase = true;
            }
        }
        // Check if both a digit and an uppercase letter are present
        if(!containsDigit || !containsUpperCase){
            throw new Exception("Password must contain a digit and a uppercase letter");
        }

        else{
            return true;
        }
    }


    // Method to validate the phone number
    public boolean isValidPhoneNumber() throws Exception{
        // Check if the phone number is a 10-digit number
    if(!phn_no.matches("\\d{10}")){
        throw new Exception("Invalid Phone Number");
    }
    else{
        return true;
    }
}
}

public class SETA{
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter username:");
            String username = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            System.out.println("Enter Phone Number:");
            String phn_no = sc.nextLine();

            Data data = new Data(username, password, phn_no);
            data.isValid();
        }
        catch(Exception e){
            System.err.println("Validation Failed!! "+e.getMessage());
        }

    }
}
