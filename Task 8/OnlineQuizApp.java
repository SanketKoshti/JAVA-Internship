
import java.util.*;

class Question { 
    String question;
    String[] options;
    int correctAnswer; 

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class OnlineQuizApp {  
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    
        
        List<Question> quiz = new ArrayList<>();
        quiz.add(new Question("Which language is used for Android development?",
                new String[]{"1. Python", "2. Java", "3. C#", "4. PHP"}, 1));
        quiz.add(new Question("Which company developed Java?",
                new String[]{"1. Microsoft", "2. Sun Microsystems", "3. Apple", "4. IBM"}, 1));
        quiz.add(new Question("What does SQL stand for?",
                new String[]{"1. Structured Question Language", "2. Simple Query Language", "3. Structured Query Language", "4. Sequential Query Language"}, 2));
        quiz.add(new Question("Which of the following is not OOPs concept?",
                new String[]{"1. Inheritance", "2. Encapsulation", "3. Polymorphism", "4. Compilation"}, 3));
        quiz.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"1. extend", "2. implements", "3. inherit", "4. extends"}, 3));

        int score = 0;

       
        for (int i = 0; i < quiz.size(); i++) {
            Question q = quiz.get(i);
            System.out.println("\nQ" + (i + 1) + ". " + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }
            System.out.print("Enter your choice (1-4): ");

            int answer;
            if (sc.hasNextInt()) {
                answer = sc.nextInt() - 1; 
            } else {
                System.out.println("Invalid input! Skipping question.");
                sc.next(); 
                continue;
            }

            if (answer == q.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer is: " + (q.correctAnswer + 1));
            }
        }

        System.out.println("\n--- Quiz Completed ---");
        System.out.println("Your Score: " + score + "/" + quiz.size());

        sc.close();
    }
}
