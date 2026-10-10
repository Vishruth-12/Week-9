import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelBookingCommonFee {
    abstract static class Booking {
        static final double BOOKING_FEE = 50.0;
        final double distanceKm;
        Booking(double distanceKm) { this.distanceKm = distanceKm; }
        abstract double baseFare();
        abstract String mode();
        double total() { return baseFare() + BOOKING_FEE; }
    }

    static class Bus extends Booking {
        Bus(double distance) { super(distance); }
        double baseFare() { return distanceKm * 2.0; }
        String mode() { return "BUS"; }
    }

    static class Train extends Booking {
        Train(double distance) { super(distance); }
        double baseFare() { return distanceKm * 1.5; }
        String mode() { return "TRAIN"; }
    }

    static class Flight extends Booking {
        Flight(double distance) { super(distance); }
        double baseFare() { return 2500 + distanceKm * 4.0; }
        String mode() { return "FLIGHT"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double distance = Double.parseDouble(p[1]);
            switch (p[0]) {
                case "BUS": bookings.add(new Bus(distance)); break;
                case "TRAIN": bookings.add(new Train(distance)); break;
                case "FLIGHT": bookings.add(new Flight(distance)); break;
                default: throw new IllegalArgumentException("Unknown mode: " + p[0]);
            }
        }
        for (Booking booking : bookings) {
            System.out.printf("%s: %.2f%n", booking.mode(), booking.total());
        }
        sc.close();
    }
}
