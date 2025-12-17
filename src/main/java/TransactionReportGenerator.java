import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public abstract class TransactionReportGenerator {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
    public static void printTopAndLeastExpensesByDate(List<Transaction> transactions, String dateStr) {
        System.out.println("3 найбільших і 3 найменших витрат за " + dateStr + ":");
        List<Transaction> result = TransactionAnalyzer.findTopAndLeastExpensesByDate(transactions, dateStr);
        for (Transaction expense : result) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void generateExpensesReport(List<Transaction> transactions) {
        Map<String, Double> categoryTotals = new HashMap<>();
        Map<String, Double> monthTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getAmount() >= 0) continue;

            categoryTotals.put(
                    t.getDescription(),
                    categoryTotals.getOrDefault(t.getDescription(), 0.0) + Math.abs(t.getAmount())
            );

            LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
            String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            monthTotals.put(
                    month,
                    monthTotals.getOrDefault(month, 0.0) + Math.abs(t.getAmount())
            );
        }

        System.out.println("=== Витрати по категоріях ===");
        for (String category : categoryTotals.keySet()) {
            double total = categoryTotals.get(category);
            int stars = (int) (total / 1000);
            stars = Math.max(1, stars);
            System.out.printf("%-20s | %s (%.2f грн)%n", category, "*".repeat(stars), total);
        }

        System.out.println("\n=== Витрати по місяцях ===");
        for (String month : monthTotals.keySet()) {
            double total = monthTotals.get(month);
            int stars = (int) (total / 1000);
            stars = Math.max(1, stars);
            System.out.printf("%-10s | %s (%.2f грн)%n", month, "*".repeat(stars), total);
        }
    }
}