import java.util.*;

class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> users = new HashMap<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        // Predefined user login credentials
        users.put("user1", "password123");

        System.out.println("Welcome to the Online Reservation System!");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Login functionality
    private static void login() {
        System.out.print("\nEnter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            userModule();
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }

    // User functionality after successful login
    private static void userModule() {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> makeReservation();
                case 2 -> cancelReservation();
                case 3 -> viewReservations();
                case 4 -> {
                    System.out.println("Logged out. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Reservation functionality
    private static void makeReservation() {
        System.out.print("\nEnter Train Number: ");
        String trainNumber = scanner.next();
        System.out.print("Enter Train Name: ");
        scanner.nextLine(); // Consume newline
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type (e.g., Sleeper, AC): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (dd-mm-yyyy): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From (Departure Location): ");
        String from = scanner.nextLine();
        System.out.print("Enter To (Destination): ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, from, to);
        reservations.add(reservation);

        System.out.println("\nReservation successful!");
        System.out.println(reservation);
    }

    // Cancellation functionality
    private static void cancelReservation() {
        System.out.print("\nEnter PNR Number to cancel: ");
        String pnr = scanner.next();

        Reservation reservation = findReservationByPNR(pnr);
        if (reservation != null) {
            System.out.println("Reservation details:\n" + reservation);
            System.out.println("\nDo you want to cancel this reservation? (Y/N)");
            char choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                reservations.remove(reservation);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with the given PNR number.");
        }
    }

    // Method to find reservation by PNR number
    private static Reservation findReservationByPNR(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                return reservation;
            }
        }
        return null;
    }

    // View all reservations
    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations found.");
        } else {
            System.out.println("\nYour Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

// Reservation class to hold reservation details
class Reservation {
    private static int pnrCounter = 1001;  // Static PNR counter for unique PNR generation
    private String pnr;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.pnr = String.valueOf(pnrCounter++);
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public String getPnr() {
        return pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + "\nTrain Number: " + trainNumber + "\nTrain Name: " + trainName + "\nClass Type: " + classType +
                "\nDate of Journey: " + dateOfJourney + "\nFrom: " + from + "\nTo: " + to;
    }
}
