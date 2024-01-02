//CSL203 OOP JAVA LAB ----- ARSHAK MUHAMMED P K, R3A, 72

// Design a base class named "Customer" with attributes "name" and "phone-number." Derive a class called 
//"Depositor" from the Customer class, adding attributes "accno" and "balance." 
// The Depositor class should contain methods for depositing and withdrawing funds.
// Further, derive a class named "Borrower" from the Depositor class, introducing attributes 
// "loan-no" and "loan-amt." The Borrower class should have a method named "check_eligibility" 
// that examines the eligibility for a loan by verifying the balance. If the balance is greater than 0,
// display "Eligible for loan" and write the customer details to a file. If the balance is not sufficient,
// throw a custom exception.
// In the main method, utilize an array of objects to manage the list of customers. 
// Read the details of eligible customers from the file and display the customers whose 
// names start with the letter 's' on the console.


import java.io.*;

class Customer {
    String name, ph_no;

    Customer(String name, String ph_no) {
        this.name = name;
        this.ph_no = ph_no;
    }
}

class Depositor extends Customer {
    int accno;
    double balance = 0.0;

    Depositor(String name, String ph_no, int accno, double balance) {
        super(name, ph_no);
        this.accno = accno;
        this.balance = balance;
    }

    public void deposit(double amt) {
        balance += amt;
        System.out.println("Deposited " + amt);
    }

    public void withdraw(double amt) {
        balance -= amt;
        System.out.println("Withdrawn " + amt);
    }
}

class Borrower extends Depositor {
    int loanno;
    double loanamt;

    Borrower(String name, String ph_no, int accno, double balance, int loanno, int loanamt) {
        super(name, ph_no, accno, balance);
        this.loanno = loanno;
        this.loanamt = loanamt;
    }

    public void checkEligibility() throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter("loaneligible.txt", true))) {
            if (balance > 0) {
                System.out.println("Eligible for loan!");
                pw.println("NAME: " + name);
                pw.println("PHONE NUMBER: " + ph_no);
                pw.println("ACCOUNT NUMBER: " + accno);
                pw.println();
            } else {
                throw new Exception("Insufficient Balance");
            }
        }
    }
}

public class Main1 {
    public static void main(String[] args) {
        Borrower[] customers = new Borrower[3];

        customers[0] = new Borrower("Arshak", "9447837405", 123, 100000, 2, 5000);
        customers[1] = new Borrower("Sathish", "9447837405", 456, 200, 1, 2000);
        customers[2] = new Borrower("Babu", "9447837405", 789, 10, 1, 2000);

        try {
            for (Borrower cus : customers) {
                cus.checkEligibility();
            }
        } catch (Exception e) {
            System.err.println("Not Eligible: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("loaneligible.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("NAME: S")) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
