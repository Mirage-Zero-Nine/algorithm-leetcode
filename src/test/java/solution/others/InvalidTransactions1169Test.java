package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class InvalidTransactions1169Test {

    private final InvalidTransactions_1169 test = new InvalidTransactions_1169();

    @Test
    public void testHappyCases() {
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "alice,50,100,beijing"});
        assertEquals(2, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.invalidTransactions(new String[]{}).size());
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv"});
        assertEquals(0, result.size());
    }

    @Test
    public void testLargeCase() {
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "bob,50,1200,mtv"});
        assertTrue(result.contains("bob,50,1200,mtv"));
    }
}
