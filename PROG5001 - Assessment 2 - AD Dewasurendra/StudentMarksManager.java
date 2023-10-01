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
            String lastName = parts.length > 0 ? parts[0] : "";
            String firstName = parts.length > 1 ? parts[1] : "";
            String studentID = parts.length > 2 ? parts[2] : "";
            double A1 = parts.length > 3 && !parts[3].isEmpty() ? Double.parseDouble(parts[3]) : 0.0;
            double A2 = parts.length > 4 && !parts[3].isEmpty() ? Double.parseDouble(parts[4]) : 0.0;
            double A3 = parts.length > 5 && !parts[3].isEmpty() ? Double.parseDouble(parts[5]) : 0.0;
        
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

    public void printStudents() {
       
        System.out.printf("| %-30s | %-20s | %-15s | %-15s | %-15s | %-15s | %-7s |%n", "Last Name", "First Name", "Student ID", "Assessment 01", "Assessment 02", "Assessment 03", "Total");
        System.out.printf("+----------------------------------+---------------------------+-----------------+-----------------+-----------------+-----------------+---------+%n");
  
     
        for (Student s : students) {
            System.out.printf("| %-30s | %-20s | %-15s | %-15.2f | %-15.2f | %-15.2f | %-7.2f |%n", s.lastName, s.firstName, s.studentID, s.A1, s.A2, s.A3, s.total);
        }
    }

    public void printStudentsBelowThreshold(double threshold){
    
    System.out.printf("| %-30s | %-20s | %-15s | %-15s | %-15s | %-15s | %-7s |%n", "Last Name", "First Name", "Student ID", "Assessment 01", "Assessment 02", "Assessment 03", "Total");
    System.out.printf("+----------------------------------+---------------------------+-----------------+-----------------+-----------------+-----------------+---------+%n");
  
    for(Student s: students){
        if(s.total < threshold){
            System.out.printf("| %-30s | %-20s | %-15s | %-15.2f | %-15.2f | %-15.2f | %-7.2f |%n", s.lastName , s.firstName , s.studentID , s.A1,s.A2,s.A3,s.total);
        }
    }
    
    }

    public void sortStudents(){
        int n = students.size();
        for(int i=0;i<n-1;i++){
            for(int j=0; j<n-i-1;j++){
                if(students.get(j).total > students.get(j+1).total){
                
                Student temp = students.get(j);
                students.set(j, students.get(j+1));
                students.set(j+1,temp);
                }
            
            }
        
        }
    }
    
    public void printTopAndBottomStudents(){
        
        
        sortStudents();
        
        for (int i = students.size() - 1; i >= students.size() - 5; i--) {
            System.out.printf(students.get(i).lastName, students.get(i).firstName, students.get(i).studentID, students.get(i).A1, students.get(i).A2, students.get(i).A3, students.get(i).total);
        }
    
        for (int i = 0; i < 5; i++) {
            System.out.printf( students.get(i).lastName, students.get(i).firstName, students.get(i).studentID, students.get(i).A1, students.get(i).A2, students.get(i).A3, students.get(i).total);
        }
    
    
    }
    
    public static void main(String[] args) throws IOException{
    
    StudentMarksManager manager = new StudentMarksManager();
    
    Scanner scanner = new Scanner(System.in);
    String filename = null;
    int file = 0;
    
    while(true){
            System.out.println("Menu:");
            System.out.print("1. Read a file" + (filename != null && file == 1 ? " (Current file: " + filename + ")" : ""));
            System.out.print("\n2. Show all students with Total marks");
            System.out.print("\n3. Show Students below the given threshold");
            System.out.print("\n4. Show Top and Bottom students");
            System.out.print("\n5. Exit");
            System.out.print("\nEnter your choice: ");
    
    
    try{
    
    int choice = scanner.nextInt();
    
    if(choice == 5){
        return;
    }
    
    if(choice !=1 && file==0){
    
        System.out.println("First read a file");
          System.out.println();
                    continue;
    
    }
    switch(choice){
    
        case 1:
            System.out.print("Enter the filename: ");
            filename = (scanner.next()+".csv");
            file = manager.readFromFile(filename);
            break;
        
            case 2:
                System.out.println();
                System.out.println("All students with calculated Total marks;");
                manager.printStudents();
                break;
                
                
                 case 3:
                        
                        System.out.print("Enter the threshold");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("That's not a valid number , Enter a valid threshold");
                            System.out.print("Enter the threshold: ");
                            scanner.next();
                        }
                        Double threshold = scanner.nextDouble();
                        if (threshold < 0 || threshold > 40) {
                            System.out.println("\nThe threshold must be in the range 0 - 30 !");
                            break;
                        }
                        
                        System.out.println();
                        
                        System.out.println("Students with total marks less than " + threshold + ";");
                        manager.printStudentsBelowThreshold(threshold);
                        break;               
             case 4:
                        System.out.println();
                        manager.printTopAndBottomStudents();
                        break;
                    default:
                  
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer");
                scanner.nextLine(); 
            }
            System.out.println();
    }
    
    }
    
    }
    
    
    







