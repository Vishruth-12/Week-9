import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryLateFineCounter {
    abstract static class LibraryItem {
        final String title;
        final int daysLate;
        LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
        abstract double fine();
    }

    static class Book extends LibraryItem {
        Book(String title, int days) { super(title, days); }
        double fine() { return 2.0 * daysLate; }
    }

    static class DVD extends LibraryItem {
        DVD(String title, int days) { super(title, days); }
        double fine() { return Math.min(50.0, 5.0 * daysLate); }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title, int days) { super(title, days); }
        double fine() { return 1.0 * daysLate; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int days = Integer.parseInt(p[2]);
            switch (p[0]) {
                case "BOOK": items.add(new Book(p[1], days)); break;
                case "DVD": items.add(new DVD(p[1], days)); break;
                case "MAGAZINE": items.add(new Magazine(p[1], days)); break;
                default: throw new IllegalArgumentException("Unknown item type: " + p[0]);
            }
        }
        double total = 0;
        for (LibraryItem item : items) {
            double fine = item.fine();
            System.out.printf("%s: %.2f%n", item.title, fine);
            total += fine;
        }
        System.out.printf("Total Fines: %.2f%n", total);
        sc.close();
    }
}
