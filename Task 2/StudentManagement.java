import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;

    
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getMarks() {
        return marks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }

    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: 
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    students.add(new Student(id, name, marks));
                    System.out.println("Student added successfully!");
                    break;

                case 2: 
                
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("=== Student List ===");
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:

                    System.out.print("Enter ID of student to update: ");
                    int updateId = sc.nextInt();
                    boolean found = false;
                    for (Student s : students) {
                        if (s.getId() == updateId) {
                            sc.nextLine(); 
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new marks: ");
                            double newMarks = sc.nextDouble();
                            s.setName(newName);
                            s.setMarks(newMarks);
                            System.out.println("Student updated successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with ID " + updateId + " not found.");
                    }
                    break;

                case 4: 
                
                    System.out.print("Enter ID of student to delete: ");
                    int deleteId = sc.nextInt();
                    Student toDelete = null;
                    for (Student s : students) {
                        if (s.getId() == deleteId) {
                            toDelete = s;
                            break;
                        }
                    }
                    if (toDelete != null) {
                        students.remove(toDelete);
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student with ID " + deleteId + " not found.");
                    }
                    break;

                case 5:
                
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
