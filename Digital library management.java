import java.util.*;

class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> adminCredentials = new HashMap<>();
    private static List<Book> books = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize default admin credentials and some sample books
        adminCredentials.put("admin", "admin123");
        books.add(new Book(1, "Java Programming", "Technology", true));
        books.add(new Book(2, "Data Structures", "Education", true));
        books.add(new Book(3, "Harry Potter", "Fiction", true));

        System.out.println("Welcome to the Library Management System!");
        while (true) {
            System.out.println("\nSelect user type:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> userLogin();
                case 3 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin login and functionalities
    private static void adminLogin() {
        System.out.print("\nEnter Admin Username: ");
        String username = scanner.next();
        System.out.print("Enter Admin Password: ");
        String password = scanner.next();

        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            System.out.println("Login successful!");
            adminModule();
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }

    private static void adminModule() {
        while (true) {
            System.out.println("\nAdmin Module - Select an option:");
            System.out.println("1. Add a Book");
            System.out.println("2. Update a Book");
            System.out.println("3. Delete a Book");
            System.out.println("4. View All Books");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> updateBook();
                case 3 -> deleteBook();
                case 4 -> viewBooks();
                case 5 -> {
                    System.out.println("Logged out from Admin Module.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Book Title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();
        System.out.print("Enter Book Category: ");
        String category = scanner.nextLine();
        books.add(new Book(id, title, category, true));
        System.out.println("Book added successfully!");
    }

    private static void updateBook() {
        System.out.print("\nEnter Book ID to Update: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getId() == id) {
                System.out.print("Enter New Title: ");
                scanner.nextLine(); // Consume newline
                book.setTitle(scanner.nextLine());
                System.out.print("Enter New Category: ");
                book.setCategory(scanner.nextLine());
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    private static void deleteBook() {
        System.out.print("\nEnter Book ID to Delete: ");
        int id = scanner.nextInt();
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book deleted successfully!");
    }

    private static void viewBooks() {
        System.out.println("\n--- Available Books ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // User login and functionalities
    private static void userLogin() {
        System.out.print("\nEnter User Username: ");
        String username = scanner.next();
        System.out.print("Enter User Password: ");
        String password = scanner.next();

        // Simple user validation (you can expand this with more sophisticated login)
        boolean userExists = users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));

        if (userExists) {
            System.out.println("Login successful!");
            userModule();
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }

    private static void userModule() {
        while (true) {
            System.out.println("\nUser Module - Select an option:");
            System.out.println("1. View All Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewBooks();
                case 2 -> issueBook();
                case 3 -> returnBook();
                case 4 -> {
                    System.out.println("Logged out from User Module.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void issueBook() {
        System.out.print("\nEnter Book ID to Issue: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getId() == id && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available or already issued!");
    }

    private static void returnBook() {
        System.out.print("\nEnter Book ID to Return: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getId() == id && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("This book was not issued or not found!");
    }

    // Book class (Book entity)
    static class Book {
        private int id;
        private String title;
        private String category;
        private boolean available;

        public Book(int id, String title, String category, boolean available) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.available = available;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Book ID: " + id + ", Title: " + title + ", Category: " + category + ", Available: " + (available ? "Yes" : "No");
        }
    }

    // User class (User entity)
    static class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
