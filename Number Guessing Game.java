import java.util.Random;
import java.util.Scanner;

public class Main{
    
    // Method to play a round of the game
    public static void playGame() {
        // Random number between 1 and 100
        Random rand = new Random();
        int targetNumber = rand.nextInt(100) + 1;
        int numberOfAttempts = 0;
        int maxAttempts = 10;
        int userGuess = 0;
        boolean guessedCorrectly = false;

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Guess the Number!");
        System.out.println("I have selected a number between 1 and 100. Try to guess it!");
        System.out.println("You have " + maxAttempts + " attempts.");
        
        // Game loop
        while (numberOfAttempts < maxAttempts && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            numberOfAttempts++;
            
            if (userGuess < targetNumber) {
                System.out.println("The number is higher. Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("The number is lower. Try again.");
            } else {
                guessedCorrectly = true;
                System.out.println("Congratulations! You guessed the correct number.");
            }
        }
        
        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all attempts. The correct number was: " + targetNumber);
        }

        // Show score based on the number of attempts
        int score = Math.max(0, (maxAttempts - numberOfAttempts) * 10);
        System.out.println("Your score is: " + score);
    }

    // Main method to run the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playGame();
            
            // Ask user if they want to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            
            if (response.equalsIgnoreCase("no")) {
                playAgain = false;
                System.out.println("Thanks for playing!");
            }
        }

        scanner.close();
    }
}
