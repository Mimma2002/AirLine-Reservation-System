import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirlineReservationSystem {
    private List<Flight> flights;
    private List<Booking> bookings;
    private Map<String, User> users;
    private User loggedInUser;

    public AirlineReservationSystem() {
        flights = new ArrayList<>();
        bookings = new ArrayList<>();
        users = new HashMap<>();
        loggedInUser = null;

        // Adding some default flights
        addFlight(new Flight("AA101", "New York", "Los Angeles", 150));
        addFlight(new Flight("BA202", "London", "New York", 200));
        addFlight(new Flight("CA303", "Beijing", "San Francisco", 180));
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, new User(username, password));
        return true;
    }

    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void logoutUser() {
        loggedInUser = null;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void displayAvailableFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void bookFlight(String flightNumber) {
        if (loggedInUser == null) {
            System.out.println("Please login to book a flight.");
            return;
        }

        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                if (flight.bookSeat()) {
                    bookings.add(new Booking(flightNumber, loggedInUser.getUsername()));
                    System.out.println("Booking successful for " + loggedInUser.getUsername() + " on flight " + flightNumber);
                } else {
                    System.out.println("No available seats on flight " + flightNumber);
                }
                return;
            }
        }
        System.out.println("Flight " + flightNumber + " not found");
    }

    public void cancelBooking(String flightNumber) {
        if (loggedInUser == null) {
            System.out.println("Please login to cancel a booking.");
            return;
        }

        for (Booking booking : bookings) {
            if (booking.getFlightNumber().equals(flightNumber) && booking.getPassengerName().equals(loggedInUser.getUsername())) {
                for (Flight flight : flights) {
                    if (flight.getFlightNumber().equals(flightNumber)) {
                        if (flight.cancelSeat()) {
                            bookings.remove(booking);
                            System.out.println("Booking cancelled for " + loggedInUser.getUsername() + " on flight " + flightNumber);
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("Booking not found for " + loggedInUser.getUsername() + " on flight " + flightNumber);
    }

    public void displayUserBookings() {
        if (loggedInUser == null) {
            System.out.println("Please login to view your bookings.");
            return;
        }

        System.out.println("Bookings for " + loggedInUser.getUsername() + ":");
        for (Booking booking : bookings) {
            if (booking.getPassengerName().equals(loggedInUser.getUsername())) {
                System.out.println(booking);
            }
        }
    }
}
