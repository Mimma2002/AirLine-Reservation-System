public class Booking {
    private String flightNumber;
    private String passengerName;

    public Booking(String flightNumber, String passengerName) {
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "flightNumber='" + flightNumber + '\'' +
                ", passengerName='" + passengerName + '\'' +
                '}';
    }
}
