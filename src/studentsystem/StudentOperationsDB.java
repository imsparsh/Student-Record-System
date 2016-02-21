/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.DataInputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sparsh
 */
public class StudentOperationsDB implements StudentOperations {

    DataInputStream ds = new DataInputStream(System.in);

    @Override
    public boolean addStudent(Student s) {
        Connection con = null;
        boolean b = false;
        String insertQuery = "insert into student values('" + s.getRollno().toString() + "', '" + s.getName() + "', '" + s.getBranch() + "', '" + s.getAddress() + "', '" + s.getDob() + "')";
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?zeroDateTimeBehavior=convertToNull&user=root");
            stmt = con.createStatement();
            int result = stmt.executeUpdate(insertQuery);
            if (result > 0) {
                b = true;
            } else {
                b = false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DB Exception");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public boolean updateStudent() {
        Connection con = null;
        boolean b = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?zeroDateTimeBehavior=convertToNull&user=root");
            Statement stmt = null;
            String query = "select * from student";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            if (rs.getRow() > 0) {
                rs.beforeFirst();
                System.out.println();
                System.out.println("Available Students:");
                while (rs.next()) {
                    System.out.println("Roll No.: " + rs.getString(1));
                }
                System.out.println("Enter the roll no. to update.");
                BigInteger rollno = new BigInteger(ds.readLine());
                String insertQuery = "select * from student where rollno = '" + rollno.toString() + "'";
                stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(insertQuery);
                result.last();
                if (result.getRow() > 0) {
                    b = true;
                    System.out.println("Enter Name");
                    String name = ds.readLine();
                    System.out.println("Enter Branch");
                    String branch = ds.readLine();
                    System.out.println("Enter Address");
                    String address = ds.readLine();
                    System.out.println("Enter DOB");
                    String dob = ds.readLine();
                    String Query = "update student set name = '" + name + "', branch = '" + branch + "', address = '" + address + "', dob = '" + dob + "' where rollno = '" + rollno.toString() + "'";
                    stmt = con.createStatement();
                    int resultA = stmt.executeUpdate(Query);
                    if (resultA > 0) {
                        b = true;
                    } else {
                        b = false;
                    }
                } else {
                    b = false;
                    System.out.println("Student not Found.");
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DB Exception");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public boolean removeStudent() {
        Connection con = null;
        boolean b = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?zeroDateTimeBehavior=convertToNull&user=root");
            Statement stmt = null;
            String query = "select * from student";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            if (rs.getRow() > 0) {
                rs.beforeFirst();
                System.out.println();
                System.out.println("Available Students:");
                while (rs.next()) {
                    System.out.println("Roll No.: " + rs.getString(1));
                }
                System.out.println("Enter the roll no. to remove.");
                BigInteger rollno = new BigInteger(ds.readLine());
                String insertQuery = "delete from student where rollno = '" + rollno.toString() + "'";
                stmt = con.createStatement();
                int result = stmt.executeUpdate(insertQuery);
                if (result > 0) {
                    b = true;
                } else {
                    b = false;
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DB Exception");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public void searchStudent() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?zeroDateTimeBehavior=convertToNull&user=root");
            System.out.println("Enter any name to search for a student.");
            String name = ds.readLine();
            Statement stmt = null;
            String query = "select * from student where name like '%"+ name +"%'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            if (rs.getRow() > 0) {
                rs.beforeFirst();
                System.out.println();
                System.out.println("Search Results:");
                while (rs.next()) {
                    System.out.println("Roll No.: " + rs.getString("rollno") + "\t Name: " + rs.getString("name"));
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DB Exception");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void displayStudent() {
        Connection con = null;
        ResultSet rs = null;
        String user = "system";
        String pwd = "spar";

        String selectQuery = "select * from student";
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?zeroDateTimeBehavior=convertToNull&user=root");
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectQuery);
            rs.last();
            if (rs.getRow() > 0) {
                rs.beforeFirst();
                while (rs.next()) {
                    System.out.println();
                    System.out.println("Roll No.: " + rs.getString(1));
                    System.out.println("Name: " + rs.getString(2));
                    System.out.println("Branch: " + rs.getString(3));
                    System.out.println("Address: " + rs.getString(4));
                    System.out.println("DOB: " + rs.getString(5));
                }
            } else {
                System.out.println("No Student Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DB Exception");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
