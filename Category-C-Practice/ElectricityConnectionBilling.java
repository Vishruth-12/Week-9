import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElectricityConnectionBilling {
    abstract static class Connection {
        final int units;
        Connection(int units) { this.units = units; }
        abstract double bill();
        abstract String type();
    }

    static class Home extends Connection {
        Home(int units) { super(units); }
        double bill() {
            return Math.min(units, 100) * 5.0 + Math.max(0, units - 100) * 7.0;
        }
        String type() { return "HOME"; }
    }

    static class Shop extends Connection {
        Shop(int units) { super(units); }
        double bill() { return units * 8.0 + 100; }
        String type() { return "SHOP"; }
    }

    static class Factory extends Connection {
        Factory(int units) { super(units); }
        double bill() { return Math.max(1000.0, units * 6.0); }
        String type() { return "FACTORY"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Connection> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int units = Integer.parseInt(p[1]);
            switch (p[0]) {
                case "HOME": list.add(new Home(units)); break;
                case "SHOP": list.add(new Shop(units)); break;
                case "FACTORY": list.add(new Factory(units)); break;
                default: throw new IllegalArgumentException("Unknown connection type: " + p[0]);
            }
        }
        double total = 0;
        for (Connection connection : list) {
            double bill = connection.bill();
            System.out.printf("%s: %.2f%n", connection.type(), bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
