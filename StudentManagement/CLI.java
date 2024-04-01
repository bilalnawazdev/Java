import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        students.addAll(StudentManager.readStudentsFromFile());
        displayMenu();
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("\n1. Add new student");
            System.out.println("2. View student details");
            System.out.println("3. Update student grade");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudentDetails();
                        break;
                    case 3:
                        updateStudentGrade();
                        break;
                    case 4:
                        StudentManager.writeStudentsToFile(students);
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                choice = 0;
            }
        } while (choice != 4);
    }

    private static void addStudent() {
        try {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student grade: ");
            String grade = scanner.nextLine();
            students.add(new Student(studentId, name, grade));
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    private static void viewStudentDetails() {
        try {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            for (Student student : students) {
                if (student.getStudentId().equals(studentId)) {
                    System.out.println(student);
                    return;
                }
            }
            System.out.println("Student not found.");
        } catch (Exception e) {
            System.err.println("Error viewing student details: " + e.getMessage());
        }
    }

    private static void updateStudentGrade() {
        try {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            for (Student student : students) {
                if (student.getStudentId().equals(studentId)) {
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine();
                    student.setGrade(newGrade);
                    System.out.println("Grade updated successfully.");
                    return;
                }
            }
            System.out.println("Student not found.");
        } catch (Exception e) {
            System.err.println("Error updating student grade: " + e.getMessage());
        }
    }
}