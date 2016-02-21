/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sparsh
 */
public class StudentOperationsFile implements StudentOperations {
    
    DataInputStream ds = new DataInputStream(System.in);
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        FileInputStream streamIn = null;
        FileOutputStream streamOut = null;
    
    @Override
    public boolean addStudent(Student s) {
        boolean b = false;
        try {
            fout = new FileOutputStream("./student.ser", true);
            oos = new ObjectOutputStream(fout);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException exp) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, exp);
        }
        try {
            oos.writeObject(s);
            oos.flush();
            b = true;
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return b;
    }
    
    @Override
    public boolean updateStudent() {
        boolean foundAndUpdated = false;
        ArrayList<Student> al = new ArrayList();
        boolean entryFound = false;
        try {
            streamIn = new FileInputStream("./student.ser");
            Student s = null;
            while (true) {
                try {
                    ois = new ObjectInputStream(streamIn);
                    s = (Student) ois.readObject();
                } catch (EOFException exc) {
                    ois.close();
                    break;
                }
                if (s instanceof Student) {
                    entryFound = true;
                    al.add(s);
                    System.out.println("Roll No.: " + s.getRollno());
                }
            }
            if (!entryFound) {
                System.out.println("No Entry Found.");
            } else {
                StudentOperationsFile sof = new StudentOperationsFile();
                System.out.println("Enter the roll no. to remove.");
                BigInteger rollno = new BigInteger(ds.readLine());
                streamOut = new FileOutputStream("./student.ser");
                streamOut.close();
                Iterator it = al.iterator();
                    while (it.hasNext()) {
                        s = (Student) it.next();
                        if (s.getRollno().equals(rollno)) {
                            foundAndUpdated = true;
                            System.out.println("Enter Name");
                            String name = ds.readLine();
                            System.out.println("Enter Branch");
                            String branch = ds.readLine();
                            System.out.println("Enter Address");
                            String address = ds.readLine();
                            System.out.println("Enter DOB");
                            String dob = ds.readLine();
                            s = new Student(name, branch, rollno, address, dob);
                        }
                        sof.addStudent(s);
                    }
                    if (!foundAndUpdated) {
                        System.out.println("Student Not Found");
                    }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundAndUpdated;
    }
    
    @Override
    public boolean removeStudent() {
        ArrayList<Student> al = new ArrayList();
        boolean foundAndRemoved = false;
        boolean entryFound = false;
        try {
            streamIn = new FileInputStream("./student.ser");
            Student s = null;
            while (true) {
                try {
                    ois = new ObjectInputStream(streamIn);
                    s = (Student) ois.readObject();
                } catch (EOFException exc) {
                    ois.close();
                    break;
                }
                if (s instanceof Student) {
                    entryFound = true;
                    al.add(s);
                    System.out.println("Roll No.: " + s.getRollno());
                }
            }
            if (!entryFound) {
                System.out.println("No Entry Found.");
            } else {
                StudentOperationsFile sof = new StudentOperationsFile();
                System.out.println("Enter the roll no. to remove.");
                BigInteger rollno = new BigInteger(ds.readLine());
                streamOut = new FileOutputStream("./student.ser");
                streamOut.close();
                Iterator it = al.iterator();
                while (it.hasNext()) {
                    s = (Student) it.next();
                    if (!s.getRollno().equals(rollno)) {
                        sof.addStudent(s);
                    } else {
                        foundAndRemoved = true;
                    }
                }
                if (!foundAndRemoved) {
                    System.out.println("Student Not Found");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundAndRemoved;
    }
    
    @Override
    public void searchStudent() {
        boolean entryFound = false, found = false;
        try {
            System.out.println("Enter any name to search for a student.");
            String name = ds.readLine();
            streamIn = new FileInputStream("./student.ser");
            Student s = null;
            System.out.println("Search Results:");
            while (true) {
                try {
                    ois = new ObjectInputStream(streamIn);
                    s = (Student) ois.readObject();
                } catch (EOFException exc) {
                    ois.close();
                    break;
                }
                if (s instanceof Student) {
                    entryFound = true;
                    if (s.getName().contains(name)) {
                        found = true;
                        System.out.println("Roll No.: " + s.getRollno() + "\t Name: " + s.getName());
                    }
                }
            }
            if (!found) {
                System.out.println("No Match Found.");
            }
            if (!entryFound) {
                System.out.println("No Student Entry Found.");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void displayStudent() {
        boolean entryFound = false;
        try {
            streamIn = new FileInputStream("./student.ser");
            Student s = null;
            while (true) {
                try {
                    ois = new ObjectInputStream(streamIn);
                    s = (Student) ois.readObject();
                } catch (EOFException exc) {
                    ois.close();
                    break;
                }
                if (s instanceof Student) {
                    entryFound = true;
                    System.out.println();
                    System.out.println("Roll No.: " + s.getRollno());
                    System.out.println("Name: " + s.getName());
                    System.out.println("Branch: " + s.getBranch());
                    System.out.println("Address: " + s.getAddress());
                    System.out.println("DOB: " + s.getDob());
                }
            }
            if (!entryFound) {
                System.out.println("No Entry Found.");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentOperationsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
