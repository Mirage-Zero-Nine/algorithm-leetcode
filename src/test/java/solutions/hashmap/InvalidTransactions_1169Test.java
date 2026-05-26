package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class InvalidTransactions_1169Test {

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

    @Test
    public void testAmountExceeds1000() {
        List<String> result = test.invalidTransactions(new String[]{"alice,20,1001,mtv"});
        assertEquals(1, result.size());
        assertEquals("alice,20,1001,mtv", result.get(0));
    }

    @Test
    public void testExactly1000NotInvalid() {
        List<String> result = test.invalidTransactions(new String[]{"alice,20,1000,mtv"});
        assertEquals(0, result.size());
    }

    @Test
    public void testSameCityWithin60Min() {
        // Same name, within 60 min, but same city -> valid
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "alice,50,800,mtv"});
        assertEquals(0, result.size());
    }

    @Test
    public void testDifferentCityExactly60Min() {
        // Same name, exactly 60 min apart, different city -> invalid
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "alice,80,800,beijing"});
        assertEquals(2, result.size());
    }

    @Test
    public void testDifferentCityOver60Min() {
        // Same name, 61 min apart, different city -> valid
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "alice,81,800,beijing"});
        assertEquals(0, result.size());
    }

    @Test
    public void testDifferentNames() {
        // Different names, within 60 min, different city -> valid (names don't match)
        List<String> result = test.invalidTransactions(new String[]{"alice,20,800,mtv", "bob,50,800,beijing"});
        assertEquals(0, result.size());
    }

    @Test
    public void testMultipleInvalid() {
        List<String> result = test.invalidTransactions(new String[]{
            "alice,20,800,mtv", "alice,50,100,beijing", "alice,51,1200,mtv"
        });
        assertEquals(3, result.size());
    }

    @Test
    public void testGiantCase() {
        String[] transactions = new String[100];
        for (int i = 0; i < 100; i++) {
            transactions[i] = "user," + i + ",500," + (i % 2 == 0 ? "cityA" : "cityB");
        }
        // All within 60 min of each other with alternating cities and same name
        List<String> result = test.invalidTransactions(transactions);
        assertTrue(result.size() > 0);
    }
}
