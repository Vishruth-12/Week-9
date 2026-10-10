import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeeklyStaffPay {
    abstract static class Staff {
        final String name;
        Staff(String name) { this.name = name; }
        abstract double pay();
    }

    static class FullTime extends Staff {
        final double weeklySalary;
        FullTime(String name, double salary) { super(name); weeklySalary = salary; }
        double pay() { return weeklySalary; }
    }

    static class Hourly extends Staff {
        final double hours, rate;
        Hourly(String name, double hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
        double pay() {
            return Math.min(hours, 40) * rate + Math.max(0, hours - 40) * rate * 1.5;
        }
    }

    static class Intern extends Staff {
        final double stipend;
        Intern(String name, double stipend) { super(name); this.stipend = stipend; }
        double pay() { return stipend; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Staff> staffList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            switch (p[0]) {
                case "FULLTIME": staffList.add(new FullTime(p[1], Double.parseDouble(p[2]))); break;
                case "HOURLY": staffList.add(new Hourly(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]))); break;
                case "INTERN": staffList.add(new Intern(p[1], Double.parseDouble(p[2]))); break;
                default: throw new IllegalArgumentException("Unknown staff type: " + p[0]);
            }
        }
        double total = 0;
        for (Staff staff : staffList) {
            double pay = staff.pay();
            System.out.printf("%s: %.2f%n", staff.name, pay);
            total += pay;
        }
        System.out.printf("Total Payroll: %.2f%n", total);
        sc.close();
    }
}
