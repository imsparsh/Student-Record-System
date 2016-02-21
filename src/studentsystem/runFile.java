/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Sparsh
 */
public class runFile {

    public static void main(String ar[]) throws IOException {
        int ch;
        Scanner sc = new Scanner(System.in);
        DataInputStream ds = new DataInputStream(System.in);
        runDB rdb = new runDB();
        StudentOperationsFile sdb = new StudentOperationsFile();
        System.out.println("Student Record System | Serializable File Implementation");
        rdb.displayMenu();
        ch = sc.nextInt();
        while (ch != 6) {
            switch (ch) {
                case 1: // add a student
                    if (sdb.addStudent(rdb.takeStudent(ds))) {
                        System.out.println("Student Added successfully");
                    } else {
                        System.out.println("Error Occurred.");
                    }
                    break;
                case 2: // update a student
                    if (sdb.updateStudent()) {
                        System.out.println("Student information updated successfully");
                    } else {
                        System.out.println("Error Occurred.");
                    }
                    break;
                case 3: // remove a student
                    if (sdb.removeStudent()) {
                        System.out.println("Student removed successfully");
                    } else {
                        System.out.println("Error Occurred.");
                    }
                    break;
                case 4: // search a student
                    sdb.searchStudent();
                    break;
                case 5: // display a student
                    System.out.println("Displaying Students Records:");
                    sdb.displayStudent();
                    System.out.println("Done.");
                    break;
                case 6: // exit
                    break;
            }
            rdb.displayMenu();
            ch = sc.nextInt();
        }
    }

    void displayMenu() {
        System.out.println();
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Remove Student");
        System.out.println("4. Search Student");
        System.out.println("5. Display Student");
        System.out.println("6. Exit");
    }

    Student takeStudent(DataInputStream ds) throws IOException {
        System.out.println("Enter Name");
        String name = ds.readLine();
        System.out.println("Enter Branch");
        String branch = ds.readLine();
        System.out.println("Enter RollNo.");
        BigInteger rollno = new BigInteger(ds.readLine());
        System.out.println("Enter Address");
        String address = ds.readLine();
        System.out.println("Enter DOB");
        String dob = ds.readLine();
        Student stud = new Student(name, branch, rollno, address, dob);
        return stud;
    }
}
