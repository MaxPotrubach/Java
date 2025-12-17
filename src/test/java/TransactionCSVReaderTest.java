import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class TransactionCSVReaderTest {
    @Test
    public void testReadTransactions() throws Exception {
        File tempFile = File.createTempFile("transactions", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("2025-01-01,200.0,Дохід\n");
            writer.write("2025-01-02,-500.0,Витрата\n");
        }

        URL fileUrl = tempFile.toURI().toURL();

        List<Transaction> transactions = TransactionCSVReader.readTransactions(fileUrl.toString());

        Assertions.assertEquals(2, transactions.size());

        Transaction t1 = transactions.get(0);
        Transaction t2 = transactions.get(1);

        Assertions.assertEquals("2025-01-01", t1.getDate());
        Assertions.assertEquals(200.0, t1.getAmount());
        Assertions.assertEquals("Дохід", t1.getDescription());

        Assertions.assertEquals("2025-01-02", t2.getDate());
        Assertions.assertEquals(-500.0, t2.getAmount());
        Assertions.assertEquals("Витрата", t2.getDescription());
    }
}