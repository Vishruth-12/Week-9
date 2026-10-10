import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollegeFeeCounter {
    interface BusUser {
        double transportFee();
    }

    abstract static class Student {
        final String name;
        Student(String name) { this.name = name; }
        abstract double tuition();
        double otherFees() { return 0; }
        double totalFee() {
            return tuition() + otherFees() + transportFeeFor(this);
        }
        private static double transportFeeFor(Student student) {
            if (student instanceof BusUser) return ((BusUser) student).transportFee();
            return 0;
        }
    }

    static class DayScholar extends Student implements BusUser {
        DayScholar(String name) { super(name); }
        double tuition() { return 40000; }
        public double transportFee() { return 12000; }
    }

    static class Hosteller extends Student {
        Hosteller(String name) { super(name); }
        double tuition() { return 40000; }
        double otherFees() { return 60000; }
    }

    static class Scholar extends Student implements BusUser {
        Scholar(String name) { super(name); }
        double tuition() { return 20000; }
        public double transportFee() { return 12000; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            switch (p[0]) {
                case "DAY_SCHOLAR": students.add(new DayScholar(p[1])); break;
                case "HOSTELLER": students.add(new Hosteller(p[1])); break;
                case "SCHOLAR": students.add(new Scholar(p[1])); break;
                default: throw new IllegalArgumentException("Unknown student type: " + p[0]);
            }
        }
        double total = 0;
        for (Student student : students) {
            double fee = student.totalFee();
            System.out.printf("%s: %.2f%n", student.name, fee);
            total += fee;
        }
        System.out.printf("Total Collected: %.2f%n", total);
        sc.close();
    }
}
