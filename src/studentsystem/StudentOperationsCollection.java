/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.DataInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sparsh
 */
public class StudentOperationsCollection implements StudentOperations {

    DataInputStream ds = new DataInputStream(System.in);
    ArrayList studs = new ArrayList();

    @Override
    public boolean addStudent(Student s) {
        boolean b = false;
        try {
            studs.add(s);
            b = true;
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean updateStudent() {
        boolean foundAndUpdated = false;
        try {
            Iterator it = studs.iterator();
            if (studs.size() > 0) {
                System.out.println("Available Students:");
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    System.out.println("Roll No.: " + s.getRollno());
                }
                System.out.println("Enter the roll no. to update.");
                BigInteger rollno = new BigInteger(ds.readLine());
                it = studs.iterator();
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    if (rollno.equals(s.getRollno())) {
                        foundAndUpdated = true;
                        studs.remove(s);
                        System.out.println("Enter Name");
                        String name = ds.readLine();
                        System.out.println("Enter Branch");
                        String branch = ds.readLine();
                        System.out.println("Enter Address");
                        String address = ds.readLine();
                        System.out.println("Enter DOB");
                        String dob = ds.readLine();
                        Student st = new Student(name, branch, rollno, address, dob);
                        studs.add(st);
                        break;
                    } else {
                        foundAndUpdated = false;
                    }
                }
                if (!foundAndUpdated) {
                    System.out.println("Student Not Found");
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundAndUpdated;
    }

    @Override
    public boolean removeStudent() {
        boolean foundAndRemoved = false;
        try {
            Iterator it = studs.iterator();
            if (studs.size() > 0) {
                System.out.println("Available Students:");
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    System.out.println("Roll No.: " + s.getRollno());
                }
                System.out.println("Enter the roll no. to remove.");
                BigInteger rollno = new BigInteger(ds.readLine());
                it = studs.iterator();
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    if (rollno.equals(s.getRollno())) {
                        foundAndRemoved = true;
                        studs.remove(s);
                        break;
                    } else {
                        foundAndRemoved = false;
                    }
                }
                if (!foundAndRemoved) {
                    System.out.println("Student Not Found");
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundAndRemoved;
    }

    @Override
    public void searchStudent() {
        try {
            System.out.println("Enter any name to search for a student.");
            String name = ds.readLine();
            Iterator it = studs.iterator();
            if (studs.size() > 0) {
                System.out.println("Search Results:");
                boolean found = false;
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    if (s.getName().contains(name)) {
                        found = true;
                        System.out.println("Roll No.: " + s.getRollno() + "\t Name: " + s.getName());
                    }
                }
                if (!found) {
                    System.out.println("No Match Found.");
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayStudent() {
        try {
            Iterator it = studs.iterator();
            if (studs.size() > 0) {
                while (it.hasNext()) {
                    Student s = (Student) it.next();
                    System.out.println();
                    System.out.println("Roll No.: " + s.getRollno());
                    System.out.println("Name: " + s.getName());
                    System.out.println("Branch: " + s.getBranch());
                    System.out.println("Address: " + s.getAddress());
                    System.out.println("DOB: " + s.getDob());
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
