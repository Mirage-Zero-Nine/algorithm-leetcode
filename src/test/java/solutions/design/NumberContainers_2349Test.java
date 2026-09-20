package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/08/03 15:52
 * Created with IntelliJ IDEA
 */

public class NumberContainers_2349Test {

    private NumberContainers_2349 test;

    @BeforeEach
    public void setUp() {
        test = new NumberContainers_2349();
    }

    @Test
    public void test() {
        test.change(1, 10);
        assertEquals(test.find(10), 1);
        test.change(1, 20);
        assertEquals(test.find(10), -1);
        assertEquals(test.find(20), 1);
        assertEquals(test.find(30), -1);
    }

    @Test
    public void test1() {
        assertEquals(test.find(10), -1);
        test.change(2, 10);
        test.change(1, 10);
        test.change(3, 10);
        test.change(5, 10);
        assertEquals(test.find(10), 1);
        test.change(1, 20);
        assertEquals(test.find(10), 2);
    }

    @Test
    public void testReplaceWithSameValueKeepsIndex() {
        test.change(4, 7);
        test.change(4, 7);
        assertEquals(4, test.find(7));
    }

    @Test
    public void testFindReturnsSmallestIndex() {
        test.change(9, 3);
        test.change(2, 3);
        test.change(6, 3);
        assertEquals(2, test.find(3));
    }

    @Test
    public void testReplacingSmallestIndexMovesMinimum() {
        test.change(1, 8);
        test.change(2, 8);
        assertEquals(1, test.find(8));
        test.change(1, 9);
        assertEquals(2, test.find(8));
        assertEquals(1, test.find(9));
    }

    @Test
    public void testMultipleNumbersIndependent() {
        test.change(1, 100);
        test.change(2, 200);
        test.change(3, 100);
        assertEquals(1, test.find(100));
        assertEquals(2, test.find(200));
    }

    @Test
    public void testNegativeAndZeroValues() {
        test.change(5, 0);
        test.change(3, -1);
        test.change(1, -1);
        assertEquals(5, test.find(0));
        assertEquals(1, test.find(-1));
    }

    @Test
    public void testOverwriteChain() {
        test.change(10, 1);
        test.change(10, 2);
        test.change(10, 3);
        assertEquals(-1, test.find(1));
        assertEquals(-1, test.find(2));
        assertEquals(10, test.find(3));
    }

    @Test
    public void testSparseLargeIndex() {
        test.change(1_000_000_000, 42);
        assertEquals(1_000_000_000, test.find(42));
    }

    @Test
    public void testGiantCase() {
        for (int i = 1; i <= 5000; i++) {
            test.change(i, i % 5);
        }
        assertEquals(5, test.find(0));
        assertEquals(1, test.find(1));
        assertEquals(2, test.find(2));
        assertEquals(3, test.find(3));
        assertEquals(4, test.find(4));

        for (int i = 1; i <= 1000; i++) {
            test.change(i, 99);
        }
        assertEquals(1001, test.find(1));
        assertEquals(1, test.find(99));
    }

    @Test
    public void testFindMissingNumbersBeforeAndBetweenChanges() {
        assertEquals(-1, test.find(1));
        assertEquals(-1, test.find(2));
        test.change(8, 1);
        assertEquals(-1, test.find(2));
        assertEquals(8, test.find(1));
        test.change(8, 2);
        assertEquals(-1, test.find(1));
        assertEquals(8, test.find(2));
    }

    @Test
    public void testReplacingOnlyIndexRemovesOldNumber() {
        test.change(17, 4);
        assertEquals(17, test.find(4));
        test.change(17, 5);
        assertEquals(-1, test.find(4));
        assertEquals(17, test.find(5));
    }

    @Test
    public void testChangingNonMinimumLeavesMinimumUnchanged() {
        test.change(2, 6);
        test.change(4, 6);
        test.change(9, 6);
        test.change(9, 7);
        assertEquals(2, test.find(6));
        assertEquals(9, test.find(7));
    }

    @Test
    public void testReplacingMinimumWithExistingNumber() {
        test.change(1, 10);
        test.change(3, 20);
        test.change(5, 20);
        test.change(1, 20);
        assertEquals(-1, test.find(10));
        assertEquals(1, test.find(20));
    }

    @Test
    public void testReassignmentAcrossSeveralNumbers() {
        test.change(1, 11);
        test.change(2, 22);
        test.change(3, 33);
        test.change(1, 22);
        test.change(2, 33);
        test.change(3, 11);
        assertEquals(3, test.find(11));
        assertEquals(1, test.find(22));
        assertEquals(2, test.find(33));
    }

    @Test
    public void testChangingSameIndexAndNumberRepeatedlyDoesNotDuplicateIt() {
        for (int i = 0; i < 20; i++) {
            test.change(12, 99);
        }
        test.change(4, 99);
        test.change(12, 99);
        assertEquals(4, test.find(99));
        test.change(4, 100);
        assertEquals(12, test.find(99));
    }

    @Test
    public void testUpperContractBounds() {
        test.change(1_000_000_000, 1_000_000_000);
        test.change(999_999_999, 1_000_000_000);
        test.change(1, 1_000_000_000);
        assertEquals(1, test.find(1_000_000_000));
        test.change(1, 999_999_999);
        assertEquals(999_999_999, test.find(1_000_000_000));
        assertEquals(1, test.find(999_999_999));
    }

    @Test
    public void testOutOfOrderSparseIndexes() {
        test.change(100, 7);
        test.change(1, 7);
        test.change(50, 7);
        test.change(75, 8);
        assertEquals(1, test.find(7));
        assertEquals(75, test.find(8));
        test.change(1, 8);
        assertEquals(50, test.find(7));
        assertEquals(1, test.find(8));
    }

    @Test
    public void testDrainAndRepopulateNumberSet() {
        test.change(2, 30);
        test.change(4, 30);
        test.change(6, 30);
        test.change(2, 31);
        test.change(4, 31);
        test.change(6, 31);
        assertEquals(-1, test.find(30));
        assertEquals(2, test.find(31));
        test.change(1, 30);
        assertEquals(1, test.find(30));
    }

    @Test
    public void testOldNumberCanBeReintroducedAfterRemoval() {
        test.change(1, 4);
        test.change(2, 4);
        test.change(1, 5);
        test.change(2, 6);
        assertEquals(-1, test.find(4));
        test.change(3, 4);
        assertEquals(3, test.find(4));
    }

    @Test
    public void testSeveralMissingQueriesDoNotCreateIndexes() {
        for (int number = 1; number <= 100; number++) {
            assertEquals(-1, test.find(number));
        }
        test.change(10, 55);
        assertEquals(10, test.find(55));
        assertEquals(-1, test.find(56));
    }

    @Test
    public void testIndependentInstances() {
        NumberContainers_2349 first = new NumberContainers_2349();
        NumberContainers_2349 second = new NumberContainers_2349();
        first.change(1, 8);
        second.change(2, 8);
        assertEquals(1, first.find(8));
        assertEquals(2, second.find(8));
        first.change(1, 9);
        assertEquals(-1, first.find(8));
        assertEquals(2, second.find(8));
    }

    @Test
    public void testRepeatedCallsAfterAllIndexesMove() {
        test.change(1, 1);
        test.change(2, 1);
        test.change(3, 2);
        test.change(1, 2);
        test.change(2, 2);
        assertEquals(-1, test.find(1));
        assertEquals(1, test.find(2));
        test.change(1, 1);
        assertEquals(1, test.find(1));
        assertEquals(2, test.find(2));
    }

    @Test
    public void testSeededStatefulSequenceAgainstIndependentMapAndTreeSetOracle() {
        Map<Integer, Integer> expectedByIndex = new HashMap<>();
        Map<Integer, TreeSet<Integer>> expectedByNumber = new HashMap<>();
        Random random = new Random(2349L);

        for (int operation = 0; operation < 4_000; operation++) {
            int index = 1 + random.nextInt(80);
            if (random.nextInt(3) != 0) {
                int number = 1 + random.nextInt(17);
                test.change(index, number);
                Integer oldNumber = expectedByIndex.put(index, number);
                if (oldNumber != null) {
                    TreeSet<Integer> oldIndexes = expectedByNumber.get(oldNumber);
                    oldIndexes.remove(index);
                    if (oldIndexes.isEmpty()) {
                        expectedByNumber.remove(oldNumber);
                    }
                }
                expectedByNumber.computeIfAbsent(number, ignored -> new TreeSet<>()).add(index);
            } else {
                int number = 1 + random.nextInt(20);
                int expected = expectedByNumber.containsKey(number)
                        ? expectedByNumber.get(number).first()
                        : -1;
                assertEquals(expected, test.find(number), "operation " + operation + ", number " + number);
            }
        }

        for (int number = 1; number <= 20; number++) {
            int expected = expectedByNumber.containsKey(number)
                    ? expectedByNumber.get(number).first()
                    : -1;
            assertEquals(expected, test.find(number));
        }
    }

    @Test
    public void testMaximumNumberOfOperationsWithinContract() {
        Map<Integer, Integer> expectedMinimum = new HashMap<>();
        int operationCount = 0;
        for (int i = 1; i <= 50_000; i++) {
            int number = 1 + (i % 97);
            test.change(i, number);
            expectedMinimum.merge(number, i, Math::min);
            operationCount++;
        }
        for (int i = 1; i <= 50_000; i++) {
            int number = 1 + (i % 97);
            int expected = expectedMinimum.getOrDefault(number, -1);
            assertEquals(expected, test.find(number));
            operationCount++;
        }
        assertEquals(100_000, operationCount);
    }

    @Test
    public void testImplementationSupportedIntegerExtremes() {
        test.change(Integer.MAX_VALUE, Integer.MIN_VALUE);
        test.change(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, test.find(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, test.find(Integer.MAX_VALUE));
    }
}
