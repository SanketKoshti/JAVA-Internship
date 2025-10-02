import java.sql.*;
import java.util.Scanner;

public class ToDoDBApp {

    static final String URL = "jdbc:mysql://localhost:3306/tododb";
    static final String USER = "root";
    static final String PASSWORD = "*********";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- ToDo Menu ---");
                System.out.println("1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Update Task Status");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice;
                if(sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); 
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        addTask(con, sc);
                        break;
                    case 2:
                        viewTasks(con);
                        break;
                    case 3:
                        updateTask(con, sc);
                        break;
                    case 4:
                        deleteTask(con, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        }
    }

    static void addTask(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        String sql = "INSERT INTO tasks(title, status) VALUES(?, 'Pending')";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, title);
            pst.executeUpdate();
            System.out.println("Task added successfully!");
        }
    }

    static void viewTasks(Connection con) throws SQLException {
        String sql = "SELECT * FROM tasks";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Task List ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " +
                                   rs.getString("title") + " - " +
                                   rs.getString("status"));
            }
        }
    }

    static void updateTask(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter task ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new status (Pending/Done): ");
        String status = sc.nextLine();

        String sql = "UPDATE tasks SET status=? WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, status);
            pst.setInt(2, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Task updated!");
            else System.out.println("Task not found.");
        }
    }

    static void deleteTask(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter task ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM tasks WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Task deleted!");
            else System.out.println("Task not found.");
        }
    }
}
