import java.util.*;

class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return id + ". " + title + (isIssued ? " (Issued)" : " (Available)");
    }
}

class User {
    private int userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    @Override
    public String toString() {
        return "User: " + name + " (ID: " + userId + ")";
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addUser(User user) { users.add(user); }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void showUsers() {
        for (User u : users) {
            System.out.println(u);
        }
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null && !book.isIssued()) {
            book.issue();
            user.borrowBook(book);
            System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName());
        } else {
            System.out.println("Cannot issue book (not found or already issued).");
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null && book.isIssued()) {
            book.returnBook();
            user.returnBook(book);
            System.out.println("Book '" + book.getTitle() + "' returned by " + user.getName());
        } else {
            System.out.println("Cannot return book (not issued or not found).");
        }
    }

    private Book findBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) return b;
        }
        return null;
    }

    private User findUser(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) return u;
        }
        return null;
    }
}

public class Task3Program {
    public static void main(String[] args) {
        Library library = new Library();

       
        library.addBook(new Book(1, "Java Programming"));
        library.addBook(new Book(2, "Data Structures"));
        library.addBook(new Book(3, "Operating Systems"));

        Scanner sc = new Scanner(System.in);
        int choice;

        
        System.out.print("Enter your User ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); 
        
        System.out.print("Enter your Name: ");
        String userName = sc.nextLine();
        
        library.addUser(new User(userId, userName));

        System.out.println("Welcome, " + userName + "! You have been registered.\n");

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show all books");
            System.out.println("2. Issue book");
            System.out.println("3. Return book");
            System.out.println("4. Show users");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    int bId = sc.nextInt();
                    System.out.print("Enter your User ID: ");
                    int uId = sc.nextInt();
                    library.issueBook(bId, uId);
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    bId = sc.nextInt();
                    System.out.print("Enter your User ID: ");
                    uId = sc.nextInt();
                    library.returnBook(bId, uId);
                    break;
                case 4:
                    library.showUsers();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
