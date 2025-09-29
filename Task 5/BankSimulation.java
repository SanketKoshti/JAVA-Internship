import java.util.*;

class Account {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountHolder, String acc_no,double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account number is :"+ acc_no);
        transactionHistory.add("Account opened with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("Successfully deposited " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("Successfully withdrew " + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction history for " + accountHolder + ":");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.println("Enter the Account Number :");
        String acc_no=sc.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        Account acc = new Account(name,acc_no, balance);

        int choice;
        do {
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depAmount = sc.nextDouble();
                    acc.deposit(depAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double wAmount = sc.nextDouble();
                    acc.withdraw(wAmount);
                    break;
                case 3:
                    System.out.println("Current Balance: " + acc.getBalance());
                    break;
                case 4:
                    acc.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
