/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Sparsh
 */
public class Student implements Serializable{

    private BigInteger rollno;
    private String name;
    private String dob;
    private String branch;
    private String address;

    public Student(String name, String branch, BigInteger rollno, String address, String dob) {

        this.rollno = rollno;
        this.branch = branch;
        this.dob = dob;
        this.address = address;
        this.name = name;
    }

    public void setRollno(BigInteger rollno) {
        this.rollno = rollno;
    }

    public void SetBranch(String branch) {
        this.branch = branch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public BigInteger getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }
}
