import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeApplianceEnergyReport {
    interface SaverMode {
        double saverFactor();
    }

    abstract static class Appliance {
        final double hours;
        Appliance(double hours) { this.hours = hours; }
        abstract double powerWatts();
        abstract String type();
        boolean supportsSaver() { return this instanceof SaverMode; }
        double units(boolean saver) {
            double units = powerWatts() * hours / 1000.0;
            return saver ? units * ((SaverMode) this).saverFactor() : units;
        }
    }

    static class Fridge extends Appliance {
        Fridge(double hours) { super(hours); }
        double powerWatts() { return 150; }
        String type() { return "FRIDGE"; }
    }

    static class AC extends Appliance implements SaverMode {
        AC(double hours) { super(hours); }
        double powerWatts() { return 1500; }
        String type() { return "AC"; }
        public double saverFactor() { return 0.75; }
    }

    static class TV extends Appliance {
        TV(double hours) { super(hours); }
        double powerWatts() { return 100; }
        String type() { return "TV"; }
    }

    static class Washer extends Appliance implements SaverMode {
        Washer(double hours) { super(hours); }
        double powerWatts() { return 500; }
        String type() { return "WASHER"; }
        public double saverFactor() { return 0.75; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<String[]> requests = new ArrayList<>();
        for (int i = 0; i < n; i++) requests.add(sc.nextLine().trim().split("\\s+"));
        double totalCost = 0;
        for (String[] p : requests) {
            String type = p[0];
            double hours = Double.parseDouble(p[1]);
            boolean saver = p.length >= 3 && p[2].equals("SAVER");
            Appliance appliance;
            switch (type) {
                case "FRIDGE": appliance = new Fridge(hours); break;
                case "AC": appliance = new AC(hours); break;
                case "TV": appliance = new TV(hours); break;
                case "WASHER": appliance = new Washer(hours); break;
                default: throw new IllegalArgumentException("Unknown appliance type: " + type);
            }
            if (saver && !appliance.supportsSaver()) {
                System.out.println(appliance.type() + ": saver mode not supported");
                continue;
            }
            double units = appliance.units(saver);
            double cost = units * 8.0;
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", appliance.type(), units, cost);
            totalCost += cost;
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
