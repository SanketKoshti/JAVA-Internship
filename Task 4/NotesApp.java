import java.io.*;
import java.util.Scanner;

public class NotesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "notes.txt";

        try {
            
            System.out.println("Enter your notes (type 'exit' to stop): ");
            FileWriter fw = new FileWriter(fileName, true); 
            while (true) {
                String note = sc.nextLine();
                if (note.equalsIgnoreCase("exit")) break;
                fw.write(note + "\n");
            }
            fw.close();
            System.out.println(" Notes saved to " + fileName);

            
            System.out.println("\nYour saved notes:");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (IOException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
