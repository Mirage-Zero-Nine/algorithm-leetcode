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

    @Test public void testAmountBoundaryAndUnrelatedUsers() {
        assertEquals(2, test.invalidTransactions(new String[]{"a,0,1001,x", "b,0,1001,y"}).size());
        assertTrue(test.invalidTransactions(new String[]{"a,0,1001,x", "b,0,1001,y"}).contains("a,0,1001,x"));
    }

    @Test public void testEarlierTransactionMarksBoth() {
        assertEquals(2, test.invalidTransactions(new String[]{"sam,100,10,a", "sam,40,20,b"}).size());
    }

    @Test public void testSameNameDifferentCityOutsideWindow() {
        assertTrue(test.invalidTransactions(new String[]{"sam,0,1,a", "sam,60,1,b"}).size() == 2);
        assertTrue(test.invalidTransactions(new String[]{"sam,0,1,a", "sam,61,1,b"}).isEmpty());
    }

    @Test public void testRepeatedSameCityRemainsValid() {
        assertTrue(test.invalidTransactions(new String[]{"sam,0,1,a", "sam,60,1000,a", "sam,120,1,a"}).isEmpty());
    }

    @Test public void testOneHighAmountAndOneCrossCity() {
        List<String> out = test.invalidTransactions(new String[]{"a,0,1001,x", "a,50,1,y", "a,200,1,z"});
        // The first two are invalid for independent reasons; the third is outside both windows.
        assertEquals(2, out.size());
    }

    @Test public void testCrossCityPairAmongManyValidRecords() {
        List<String> out = test.invalidTransactions(new String[]{"a,0,1,x", "b,1,1,y", "a,10,1,y", "b,100,1,y"});
        assertEquals(2, out.size());
    }

    @Test public void testDuplicateRecordsAreEachReported() {
        assertEquals(2, test.invalidTransactions(new String[]{"a,1,1001,x", "a,1,1001,x"}).size());
    }

    @Test public void testUnorderedInputPreservesInputOrder() {
        List<String> out = test.invalidTransactions(new String[]{"a,80,1,y", "a,20,1,x"});
        assertEquals(List.of("a,80,1,y", "a,20,1,x"), out);
    }

    @Test public void testOnlyBoundaryAmountsAreValid() {
        assertTrue(test.invalidTransactions(new String[]{"a,0,0,x", "b,0,1000,y"}).isEmpty());
    }
}
