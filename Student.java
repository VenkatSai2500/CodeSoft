import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student implements Serializable{
    private static final long serialVersionUID=1L;
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade){
        this.name=name;
        this.rollNumber=rollNumber;
        this.grade=grade;
    }
    public String getName(){
        return name;
    }
    public int getRollNumber(){
        return rollNumber;
    }
    public String getGrade(){
        return grade;
    }
    @Override
    public String toString(){
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
class StudentManagementSystem{
    private List<Student> students;
    public StudentManagementSystem(){
        this.students = new ArrayList<>();
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(int rollNumber){
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }
    public Student searchStudent(int rollNumber){
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber){
                return student;
            }
        }
        return null;
    }
    public void displayAllStudents(){
        for (Student student : students){
            System.out.println(student);
        }
    }
    public void saveToFile(String fileName){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(students);
            System.out.println("Student data saved to file.");
        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            students = (List<Student>) ois.readObject();
            System.out.println("Student data loaded from file.");
        } 
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        boolean exit = false;
        while (!exit){
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data to File");
            System.out.println("6. Load Data from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice){
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    break;
                case 2:
                    System.out.print("Enter roll number of student to remove: ");
                    int removeRollNumber = scanner.nextInt();
                    sms.removeStudent(removeRollNumber);
                    break;
                case 3:
                    System.out.print("Enter roll number of student to search: ");
                    int searchRollNumber = scanner.nextInt();
                    Student foundStudent = sms.searchStudent(searchRollNumber);
                    if (foundStudent != null){
                        System.out.println("Student found: " + foundStudent);
                    } 
                    else{
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.saveToFile("student_data.ser");
                    break;
                case 6:
                    sms.loadFromFile("student_data.ser");
                    break;
                case 7:
                    exit=true;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
