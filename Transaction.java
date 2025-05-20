import java.time.LocalDate;

public class Transaction{
    private String subCategory;
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(String category, String subCategory, double amount, LocalDate date) {
        this.category = category;
        this.subCategory=subCategory;
        this.amount = amount;
        this.date = date;
    }

    public String getSubCategory() { return subCategory; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
    return String.format("%s,%s,%.2f,%s", category, subCategory, amount, date);
   }


    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        return new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]));
    }
}