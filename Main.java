import java.io.File;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager tm = new TransactionManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Income\n2. Add Expense\n3. Load From File\n4. Monthly Summary\n5. Exit");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Income SubCategory");
                    String subcat = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amt = Double.parseDouble(sc.nextLine());
                    tm.addTransaction("Income", subcat, amt, LocalDate.now());
                    System.out.println("Income Added Sucessfully.");
                }
                case 2 -> {
                    System.out.print("Enter Expense SubCategory: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amt = Double.parseDouble(sc.nextLine());
                    tm.addTransaction("Expense", cat, amt, LocalDate.now());
                    System.out.println("Expense Added Sucessfully.");
                }
                case 3 -> {
                    System.out.print("Enter file name: ");
                    String filePath = sc.nextLine();
                    File file = new File(filePath);
                    if (file.exists()) {
                        tm.loadTransactionsFromFile(file);
                        System.out.println("File loaded successfully.");
                    } else {
                        System.out.println("File not found.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter month and year (e.g., 2025-05): ");
    String input = sc.nextLine();
    try {
        YearMonth ym = YearMonth.parse(input);
        tm.printMonthlySummary(ym);
    } catch (Exception e) {
        System.out.println("Invalid format. Please enter in yyyy-MM format (e.g., 2025-05).");
    }
                }
                case 5 -> {
                    System.out.println("Exiting. Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
