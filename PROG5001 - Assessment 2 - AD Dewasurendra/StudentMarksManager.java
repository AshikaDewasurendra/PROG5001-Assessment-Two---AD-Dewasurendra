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

public class StudentMarksManager{

    List<Student> students = new ArrayList<>();

    public int readFromFile(String fileName){
    
        File file = new File(fileName);
    
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
    
        br.readLine();
        br.readLine();
    
        String line;
            while ((line = br.readLine() ) != null){
                    if(line.contains("#")){
                    line = line.substring(0,line.indexOf("#"));
                    }
        
            String[] parts = line.split(",");
            String lastName = parts.length>0 ? parts[0] : "";
            String firstName = parts.length>1 ? parts[1] : "";
            String studentID = parts.length>2 ? parts[2] : "";
            double A1 = parts.length > 3 && !parts[3].isEmpty() ? Double.parseDouble(parts[3]):0.0;
            double A2 = parts.length > 4 && !parts[3].isEmpty() ? Double.parseDouble(parts[4]):0.0;
            double A3 = parts.length > 5 && !parts[3].isEmpty() ? Double.parseDouble(parts[5]):0.0;
        
            students.add(new Student(lastName, firstName, studentID, A1, A2, A3));
            }
        
            System.out.println("\nFile '" +fileName+ "' Read Successfully");
            return 1;
    }   
        catch (IOException e){
    
        System.out.println("\nError");
        return 0;
    
        }
    
    }
}






