import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class TransactionManager {
    private final List<Transaction> transactions = new ArrayList<>();
    private final FileManager fileManager = FileManager.getInstance();

    public TransactionManager() {
        transactions.addAll(fileManager.loadTransactions());
    }

    public void addTransaction(String category, String subCategory,double amount, LocalDate date) {
        Transaction t = new Transaction(category,subCategory, amount, date);
        transactions.add(t);
        fileManager.saveTransaction(t);
    }

    public void printMonthlySummary(YearMonth month) {
        double income = 0, expense = 0;
        System.out.println("Monthly Summary for: " + month);
        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(month)) {
                if (t.getCategory().equalsIgnoreCase("Income")) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                }
            }
        }
        System.out.println("Total Income: Rs " + income);
        System.out.println("Total Expense: Rs " + expense);
        System.out.println("Balance: Rs " + (income - expense));

    }

    public void loadTransactionsFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction t = Transaction.fromString(line);
                transactions.add(t);
                fileManager.saveTransaction(t); // Save to our main expenses.txt
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
