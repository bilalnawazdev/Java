import java.io.*;
import java.util.*;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentId;
    private String name;
    private String grade;

    public Student(String studentId, String name, String grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Grade: " + grade;
    }
}

class StudentManager {
    private static final String FILE_PATH = "students.txt";
    public static List<Student> readStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
             InputStreamReader isr = new InputStreamReader(new FileInputStream(FILE_PATH))) {
            while (true) {
                Student student = (Student) ois.readObject();
                students.add(student);
            }
        } catch (EOFException e) {
            // Reached end of file
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return students;
    }


    public static void writeStudentsToFile(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
             ) {
            for (Student student : students) {
                if(student!=null){
                    oos.writeObject( student.toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}



