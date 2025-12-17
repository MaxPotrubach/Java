import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) count++;
        }
        return count;
    }

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<Transaction> findTopAndLeastExpensesByDate(List<Transaction> transactions, String dateStr) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        YearMonth targetMonth = YearMonth.parse(dateStr, monthFormatter);

        List<Transaction> filtered = transactions.stream()
                .filter(t -> {
                    LocalDate transactionDate = LocalDate.parse(t.getDate(), dateFormatter);
                    YearMonth transactionMonth = YearMonth.from(transactionDate);
                    return transactionMonth.equals(targetMonth) && t.getAmount() < 0;
                })
                .toList();

        if (filtered.size() <= 6) return filtered;

        List<Transaction> smallest = filtered.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(3)
                .toList();

        List<Transaction> largest = filtered.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .toList();

        List<Transaction> result = new ArrayList<>();
        result.addAll(smallest);
        result.addAll(largest);
        return result;
    }
}