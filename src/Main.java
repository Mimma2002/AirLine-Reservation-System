import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n:::::::::::Welcome to MIM's Airline Reservation System ::::::::::\n");
        AirlineReservationSystem system = new AirlineReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (system.registerUser(username, password)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Username already exists. Try a different one.");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    if (system.loginUser(username, password)) {
                        System.out.println("Login successful!");
                        userMenu(system, scanner);
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void userMenu(AirlineReservationSystem system, Scanner scanner) {
        while (true) {
            System.out.println("\n1. Display available flights");
            System.out.println("\n2. Book a flight");
            System.out.println("\n3. Cancel a booking");
            System.out.println("\n4. Display my bookings");
            System.out.println("\n5. Logout");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    system.displayAvailableFlights();
                    break;
                case 2:
                    System.out.print("Enter flight number to book: ");
                    String flightNumber = scanner.nextLine();
                    system.bookFlight(flightNumber);
                    break;
                case 3:
                    System.out.print("Enter flight number to cancel: ");
                    flightNumber = scanner.nextLine();
                    system.cancelBooking(flightNumber);
                    break;
                case 4:
                    system.displayUserBookings();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
