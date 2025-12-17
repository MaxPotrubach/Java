import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);


        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }
    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions,"02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions,"03-2023");

        assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }
    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2025-01-01", -100.0, "Кафе"),
                new Transaction("2025-01-02", -50.0, "Новус"),
                new Transaction("2025-01-03", -300.0, "Заправка"),
                new Transaction("2025-01-04", -20.0, "Кіоск"),
                new Transaction("2025-01-05", -200.0, "Аптека"),
                new Transaction("2025-01-06", -400.0, "Відпочинок"),
                new Transaction("2025-01-07", -10.0, "Таксі"),
                new Transaction("2025-01-08", -500.0, "Поїздка"),
                new Transaction("2025-01-09", -5.0, "Парк"),
                new Transaction("2025-01-10", -250.0, "Магазин одягу"),
                new Transaction("2025-01-11", 300.0, "Зарплата")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        assertEquals(10, topExpenses.size());

        assertEquals(-500.0, topExpenses.getFirst().getAmount());

        for (int i = 0; i < topExpenses.size() - 1; i++) {
            assertTrue(topExpenses.get(i).getAmount() <= topExpenses.get(i + 1).getAmount());
        }

    }
}