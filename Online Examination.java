import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    // User Information
    private static String username = "student";
    private static String password = "password123";
    private static String profileInfo = "Name: Student\nEmail: student@example.com";

    // Timer setup
    private static final int TIME_LIMIT = 5 * 60 * 1000; // 5 minutes in milliseconds
    private static Timer timer = new Timer();
    private static boolean timeIsUp = false;

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Login
        if (login(scanner)) {
            // Start timer for the exam
            startTimer();

            // MCQ Section
            int score = takeMCQs(scanner);

            // Auto Submit if time runs out
            if (timeIsUp) {
                System.out.println("Time is up! Your exam has been submitted automatically.");
            }

            // Display the score
            System.out.println("Your score: " + score);

            // Logout
            logout(scanner);
        }
        scanner.close();
    }

    // Login Method
    private static boolean login(Scanner scanner) {
        System.out.println("Enter Username: ");
        String enteredUsername = scanner.nextLine();

        System.out.println("Enter Password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    // Start timer for the exam
    private static void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeIsUp = true;
            }
        }, TIME_LIMIT);
    }

    // Method for updating profile
    private static void updateProfile(Scanner scanner) {
        System.out.println("Current Profile Info: " + profileInfo);
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        profileInfo = "Name: " + newName + "\nEmail: " + newEmail;
        System.out.println("Profile updated successfully!");
    }

    // Method for updating password
    private static void updatePassword(Scanner scanner) {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(password)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            password = newPassword;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect current password.");
        }
    }

    // MCQ section
    private static int takeMCQs(Scanner scanner) {
        int score = 0;
        String[] questions = {
            "What is the capital of France?",
            "What is 5 + 3?",
            "Which programming language is this project written in?"
        };

        String[] options = {
            "1. Berlin 2. Madrid 3. Paris 4. Rome",
            "1. 6 2. 7 3. 8 4. 9",
            "1. Python 2. Java 3. C++ 4. JavaScript"
        };

        String[] correctAnswers = {"3", "3", "2"};

        for (int i = 0; i < questions.length; i++) {
            if (timeIsUp) {
                break;
            }
            System.out.println(questions[i]);
            System.out.println(options[i]);
            System.out.print("Select an option (1-4): ");
            String answer = scanner.nextLine();
            if (answer.equals(correctAnswers[i])) {
                score++;
            }
        }

        return score;
    }

    // Logout Method
    private static void logout(Scanner scanner) {
        System.out.println("Do you want to log out? (yes/no)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("Logging out...");
            System.exit(0);
        } else {
            System.out.println("You are still logged in.");
        }
    }
}
