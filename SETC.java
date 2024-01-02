//CSL203 OOP JAVA LAB ----- ARSHAK MUHAMMED P K, R3A, 72
// Implement the following multithread program.
// The main function is responsible for reading the filename from the console. 
// The input file is expected to contain a line of integers separated by colons (":").
// The program should Three threads:
// Thread1: This thread should read the file, tokenize the integers, and print the 
// lists of odd and even numbers separately into an "odd.txt” and “even.txt”file.
// Thread2: This thread should determine the second smallest and largest numbers in both
// file and write these values into the "smallest. Text” and “largest.txt” file.
// Thread 3: Read “odd.txt” and “even.txt” and write the sorted numbers in “oddsort.txt” and “evensort.txt” file.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

class Thread1 extends Thread{
    String name;
    Thread1(String name){
        this.name=name;
    }
    public void run(){
        try(BufferedReader br = new BufferedReader(new FileReader(name))){
            String line;
            PrintWriter pw1 = pw1 = new PrintWriter(new FileWriter("even.txt",true));
            PrintWriter pw2 = pw2 = new PrintWriter(new FileWriter("odd.txt",true));

            while((line=br.readLine())!=null){
                StringTokenizer tokens = new StringTokenizer(line,":");
                while(tokens.hasMoreTokens()){
                    String toks = tokens.nextToken();
                    Integer num = Integer.parseInt(toks);
                    if(num%2==0){
                        pw1.println(num);
                    }
                    else{
                        pw2.println(num);
                    }
                }
            }
            pw1.close();
            pw2.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Thread2 extends Thread{
    public void run(){
        try{
            BufferedReader br1 = new BufferedReader(new FileReader("even.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("odd.txt"));
            ArrayList <Integer> numberarray = new ArrayList <Integer>();
            String line;
            while((line = br1.readLine())!=null){
                Integer num = Integer.parseInt(line);
                numberarray.add(num);
            }
            PrintWriter pw3 = new PrintWriter(new FileWriter("smallest.txt",true));
            PrintWriter pw4 = new PrintWriter(new FileWriter("largest.txt",true));
            Collections.sort(numberarray);
            pw3.println("Second Smallest even:"+numberarray.get(1));
            pw4.println("Largest even:"+numberarray.get(numberarray.size()-1));
            numberarray.clear();
            while((line = br2.readLine())!=null){
                Integer num = Integer.parseInt(line);
                numberarray.add(num);
            }
            Collections.sort(numberarray);
            pw3.println("Second Smallest odd:"+numberarray.get(1));
            pw4.println("Largest odd:"+numberarray.get(numberarray.size()-1));
            pw3.flush();
            pw3.close();
            pw4.flush();
            pw4.close();

            br1.close();
            br2.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Thread3 extends Thread{
    public void run(){
        try{
            BufferedReader br3 = new BufferedReader(new FileReader("odd.txt"));
            BufferedReader br4 = new BufferedReader(new FileReader("even.txt"));
            ArrayList <Integer> list = new ArrayList<Integer>();
            String line;
            while((line=br3.readLine())!=null){
                Integer n = Integer.parseInt(line);
                list.add(n);
            }
            Collections.sort(list);
            PrintWriter pw5 = new PrintWriter(new FileWriter("oddsorted.txt"));
            for(Integer nums: list){
                pw5.println(nums);
            }
            System.out.println("Oddsorted file created succesfully!");
            list.clear();
            line=null;
            while((line=br4.readLine())!=null){
                Integer n = Integer.parseInt(line);
                list.add(n);
            }
            Collections.sort(list);
            PrintWriter pw6 = new PrintWriter(new FileWriter("evensorted.txt"));
            for(Integer nums:list){
                pw6.println(nums);
            }
            System.out.println("Evensorted file created succesfully!");
            pw5.flush();
            pw5.close();
            pw6.flush();
            pw6.close();

            br3.close();
            br4.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

public class SETC{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name:");
        String name = sc.nextLine();

        Thread1 t1 = new Thread1(name);
        Thread2 t2 = new Thread2();
        Thread3 t3 =  new Thread3();

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }


