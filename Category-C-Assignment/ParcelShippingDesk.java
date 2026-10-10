import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParcelShippingDesk {
    interface Insurable {
        double insurance();
    }

    abstract static class Parcel {
        final double weightKg, declaredValue;
        Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }
        abstract double charge();
        abstract String type();
        double insurance() { return this instanceof Insurable ? ((Insurable) this).insurance() : 0.0; }
        double total() { return charge() + insurance(); }
    }

    static class Standard extends Parcel {
        Standard(double weight, double value) { super(weight, value); }
        double charge() { return 40 + 10 * weightKg; }
        String type() { return "STANDARD"; }
    }

    static class Express extends Parcel implements Insurable {
        Express(double weight, double value) { super(weight, value); }
        double charge() { return 80 + 15 * weightKg; }
        public double insurance() { return declaredValue * 0.02; }
        String type() { return "EXPRESS"; }
    }

    static class Fragile extends Parcel implements Insurable {
        Fragile(double weight, double value) { super(weight, value); }
        double charge() { return 40 + 10 * weightKg + 50; }
        public double insurance() { return declaredValue * 0.02; }
        String type() { return "FRAGILE"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double weight = Double.parseDouble(p[1]);
            double value = Double.parseDouble(p[2]);
            switch (p[0]) {
                case "STANDARD": parcels.add(new Standard(weight, value)); break;
                case "EXPRESS": parcels.add(new Express(weight, value)); break;
                case "FRAGILE": parcels.add(new Fragile(weight, value)); break;
                default: throw new IllegalArgumentException("Unknown parcel type: " + p[0]);
            }
        }
        double grandTotal = 0;
        for (Parcel parcel : parcels) {
            double charge = parcel.charge();
            double insurance = parcel.insurance();
            double total = parcel.total();
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    parcel.type(), charge, insurance, total);
            grandTotal += total;
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
