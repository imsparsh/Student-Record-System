Student Record System
=====================

A Student Record Management System developed in Java, as a Training Completion Project at my college PSIT.
The project is completely developed in NetBeans IDE, so you can directly import and run.

**Technologies Used:**
- Java # back end programming
- MySQLDB # database

It allows three different implementations to manage the records of the student as:

**Database Implementation**
-    Student class is used for storing the information of a particular subject.
-    Mysql Database is used in the system.
-    Connector J is included in 'lib' directory.
-    sql file for database is included in 'db' directory.

**File Implementation**
-    Student class is used for storing the information of a particular subject.
-    Serializable Interface is implemented.
-    'student.ser' file is stored in root directory.

**Collection Implementation**
-    Student class is used for storing the information of a particular subject.
-    ArrayList Collection Class is used for storing the Student objects.
-    Only runtime operations are allowed, the collection is not stored anywhere on file system.


***The structure of the project is described below:***

1.   Class : Student (name, roll no, branch, address, dob)
2.   Interface: StudentsOperation
3.   Class: StudentsOperationFile
4.   Class: StudentsOperationCollection
5.   Class: StudentsOperationDB

**Operations Covered:**

    a.   AddRecord(Student)
    b.   UpdateRecord()
    c.   RemoveRecord()
    d.   SearchRecord()
    e.   DisplayRecord()


**Usage:**
```

# make sure the database is ready and running..
# all the dependencies for the libraries are installed..

# for Database Implementation
    "javac runDB.java"
    "java runDB"

# for File Implementation
    "javac runFile.java"
    "java runFile"

# for Collections Implementation
    "javac runCollection.java"
    "java runCollection"
```
