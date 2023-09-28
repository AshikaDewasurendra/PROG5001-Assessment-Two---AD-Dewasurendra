import java.util.*;
import java.io.*;

/**
 * Write a description of class StudentMarksManager here.
 *
 * @author (Ashika Dewasurendra)
 * @version (a version number or a date)
 */

class Student{

    String lastName;
    String firstName;
    String studentID;
    double A1;
    double A2;
    double A3;
    double total;

    public Student(String lastName, String firstName, String studentID, double A1, double A2, double A3){

    this.lastName = lastName;
    this.firstName = firstName;
    this.studentID = studentID;
    this.A1 = A1;
    this.A2 = A2;
    this.A3 = A3;

    this.total = (A1 + A2 + A3);
    }







}

