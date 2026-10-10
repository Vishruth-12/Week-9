import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieTicketCounter {
    abstract static class Ticket {
        static final double CONVENIENCE_FEE = 20.0;
        final int count;
        Ticket(int count) { this.count = count; }
        abstract double pricePerTicket();
        abstract String seat();
        double amount() { return count * (pricePerTicket() + CONVENIENCE_FEE); }
    }

    static class Regular extends Ticket {
        Regular(int count) { super(count); }
        double pricePerTicket() { return 150; }
        String seat() { return "REGULAR"; }
    }

    static class Premium extends Ticket {
        Premium(int count) { super(count); }
        double pricePerTicket() { return 250; }
        String seat() { return "PREMIUM"; }
    }

    static class Recliner extends Ticket {
        Recliner(int count) { super(count); }
        double pricePerTicket() { return 400; }
        String seat() { return "RECLINER"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int count = Integer.parseInt(p[1]);
            switch (p[0]) {
                case "REGULAR": tickets.add(new Regular(count)); break;
                case "PREMIUM": tickets.add(new Premium(count)); break;
                case "RECLINER": tickets.add(new Recliner(count)); break;
                default: throw new IllegalArgumentException("Unknown seat type: " + p[0]);
            }
        }
        double total = 0;
        for (Ticket ticket : tickets) {
            double amount = ticket.amount();
            System.out.printf("%s: %.2f%n", ticket.seat(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
