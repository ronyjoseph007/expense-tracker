import java.io.*;
import java.util.*;
import java.nio.file.*;

public class FileManager {
    private static FileManager instance;
    private final String filePath = "transactions.txt";

    private FileManager() {}

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public void saveTransaction(Transaction transaction) {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), 
            StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
        writer.write(transaction.toString());
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}


    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromString(line));
            }
        } catch (IOException e) {
           
        }
        return transactions;
    }
}
