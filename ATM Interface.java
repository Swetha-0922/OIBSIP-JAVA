import java.util.Scanner;

class Main {
    private static String userId = "user123";
    private static String userPin = "1234";
    private static double balance = 1000.00;
    private static String transactionHistory = "Transaction History:\n";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Authentication
        if (authenticateUser(scanner)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nWelcome to ATM");
                System.out.println("1. View Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewTransactionHistory();
                        break;
                    case 2:
                        withdrawMoney(scanner);
                        break;
                    case 3:
                        depositMoney(scanner);
                        break;
                    case 4:
                        transferMoney(scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
        scanner.close();
    }

    // Authentication Method
    private static boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.next();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.next();

        if (enteredUserId.equals(userId) && enteredPin.equals(userPin)) {
            System.out.println("Authentication successful.");
            return true;
        } else {
            System.out.println("Invalid credentials. Try again.");
            return false;
        }
    }

    // View Transaction History
    private static void viewTransactionHistory() {
        System.out.println(transactionHistory);
    }

    // Withdraw Money
    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= balance) {
            balance -= amount;
            transactionHistory += "Withdrawn: " + amount + "\n";
            System.out.println("Withdrawal successful! Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Deposit Money
    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        balance += amount;
        transactionHistory += "Deposited: " + amount + "\n";
        System.out.println("Deposit successful! Current balance: " + balance);
    }

    // Transfer Money
    private static void transferMoney(Scanner scanner) {
        System.out.print("Enter recipient's account ID: ");
        String recipientAccount = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= balance) {
            balance -= amount;
            transactionHistory += "Transferred " + amount + " to " + recipientAccount + "\n";
            System.out.println("Transfer successful! Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}
