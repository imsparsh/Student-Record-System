/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

/**
 *
 * @author Sparsh
 */
public interface StudentOperations {
    public abstract boolean addStudent(Student s);
    public abstract boolean updateStudent();
    public abstract boolean removeStudent();
    public abstract void searchStudent();
    public abstract void displayStudent();
}
