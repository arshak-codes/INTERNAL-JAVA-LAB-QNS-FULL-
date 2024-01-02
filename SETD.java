//CSL203 OOP JAVA LAB ----- ARSHAK MUHAMMED P K, R3A, 72
// Define a class named "Employee" with the following data members: Employee Name, Department, and Employee ID.
//Implement an "ischeck ()" method within the class that performs the following tasks:
// a. The method should throw an "IDException."
// b. Return true if the Employee ID is valid, and false otherwise.
// c. The method should check for the following errors and throw an "IDException" with the appropriate message:
// i. The length of the Employee ID is not equal to 8 characters.
// ii. The Employee ID does not start with the department code (e.g., "HR" for Human Resources, 
// "IT" for Information Technology).
// iii. Any non-alphanumeric characters are present in the Employee ID.
// If the Employee ID is valid, the method should write the name, department, and Employee ID into a file. 
// If an "IDException" is thrown, catch it and print the name, Employee ID, and the associated error message 
// indicating why the Employee ID is invalid in the console. (use the concept of this and constructor overloading)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Employee {
    String name, dept, empid;

    // Constructor Overloading
    Employee(String name, String dept, String empid) {
        this.name = name;
        this.dept = dept;
        this.empid = empid;
    }

    public boolean ischeck() throws IDException {
        try {
            if (checkemployeeid()) {
                try (PrintWriter pw = new PrintWriter(new File("myfile2.txt"))) {
                    pw.println("Employee Name:" + name);
                    pw.println("Department:" + dept);
                    pw.println("Employee ID:" + empid);
                }
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error id: " + e.getMessage());
            return false;
        }
    }

    public boolean checkemployeeid() throws IDException {
        try {
            if (empid.length() != 8) {
                throw new IDException("Employee ID length must be 8!");
            }

            // Simplified department code extraction
            //String deptcode = dept.split(" ")[0];
            String deptcode="";
            char c1;
            StringTokenizer tokens = new StringTokenizer(dept," ");
            while(tokens.hasMoreTokens()){
                String toks = tokens.nextToken();
                c1 = toks.charAt(0);
                deptcode = deptcode+c1;
            }

            if (!empid.startsWith(deptcode)) {
                throw new IDException("Employee ID must start with the department code!");
            }

            if (!empid.matches("[a-zA-Z0-9]+")) {
                throw new IDException("Employee ID should contain alphanumeric characters");
            }
        } catch (IDException e) {
            System.err.println("Error id: " + e.getMessage());
            return false;
        }
        return true;
    }
}

class IDException extends Exception {
    public IDException(String msg) {
        super(msg);
    }
}

public class SETD {
    public static void main(String[] args) {
        Employee e1 = new Employee("Arshak", "Human Resources", "HR123456");

        try {
            if (e1.ischeck()) {
                System.out.println("File created successfully");
            }
            else{
                throw new IDException("");
            }
        } catch (IDException e) {
            System.out.println("Error Employee Details:");
            System.out.println("Name: " + e1.name);
            System.out.println("Employee ID: " + e1.empid);
        }
    }
}
