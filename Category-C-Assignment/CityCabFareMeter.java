import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityCabFareMeter {
    interface NightService {
        double applyNightFare(double fare);
    }

    abstract static class Cab {
        final double km;
        Cab(double km) { this.km = km; }
        abstract double ratePerKm();
        abstract String type();
        double fare(String time) {
            double fare = Math.max(100.0, km * ratePerKm());
            if (time.equals("NIGHT")) {
                if (!(this instanceof NightService)) return -1;
                fare = ((NightService) this).applyNightFare(fare);
            }
            return fare;
        }
    }

    static class Mini extends Cab {
        Mini(double km) { super(km); }
        double ratePerKm() { return 10; }
        String type() { return "MINI"; }
    }

    static class Sedan extends Cab implements NightService {
        Sedan(double km) { super(km); }
        double ratePerKm() { return 14; }
        String type() { return "SEDAN"; }
        public double applyNightFare(double fare) { return fare * 1.20; }
    }

    static class SUV extends Cab implements NightService {
        SUV(double km) { super(km); }
        double ratePerKm() { return 18; }
        String type() { return "SUV"; }
        public double applyNightFare(double fare) { return fare * 1.20; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<String[]> requests = new ArrayList<>();
        for (int i = 0; i < n; i++) requests.add(sc.nextLine().trim().split("\\s+"));
        double total = 0;
        for (String[] p : requests) {
            double km = Double.parseDouble(p[1]);
            String time = p[2];
            Cab cab;
            switch (p[0]) {
                case "MINI": cab = new Mini(km); break;
                case "SEDAN": cab = new Sedan(km); break;
                case "SUV": cab = new SUV(km); break;
                default: throw new IllegalArgumentException("Unknown cab type: " + p[0]);
            }
            double fare = cab.fare(time);
            if (fare < 0) {
                System.out.println(cab.type() + ": night service not available");
            } else {
                System.out.printf("%s: %.2f%n", cab.type(), fare);
                total += fare;
            }
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
