package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/** Tests the state transitions of {@link PhoneDirectory_379}. */
public class PhoneDirectory_379Test {

    @Test
    public void testOfficialExample() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(3);
        assertEquals(0, directory.get());
        assertEquals(1, directory.get());
        assertTrue(directory.check(2));
        assertEquals(2, directory.get());
        assertFalse(directory.check(2));
        directory.release(2);
        assertTrue(directory.check(2));
    }

    @Test
    public void testEveryNumberStartsAvailable() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(7);
        for (int number = 0; number < 7; number++) {
            assertTrue(directory.check(number));
        }
    }

    @Test
    public void testGetAllocatesEachNumberExactlyOnce() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(8);
        Set<Integer> allocated = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            int number = directory.get();
            assertTrue(number >= 0 && number < 8);
            assertTrue(allocated.add(number), "get must not allocate a number twice");
            assertFalse(directory.check(number));
        }
        assertEquals(8, allocated.size());
    }

    @Test
    public void testGetReturnsMinusOneWhenExhausted() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(3);
        assertNotEquals(-1, directory.get());
        assertNotEquals(-1, directory.get());
        assertNotEquals(-1, directory.get());
        assertEquals(-1, directory.get());
        assertEquals(-1, directory.get());
    }

    @Test
    public void testCheckChangesWhenNumberIsAllocated() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(2);
        assertTrue(directory.check(0));
        assertTrue(directory.check(1));
        int number = directory.get();
        assertFalse(directory.check(number));
        assertTrue(directory.check(1 - number));
    }

    @Test
    public void testReleasedNumberBecomesAvailableAndReusable() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(3);
        int first = directory.get();
        int second = directory.get();
        directory.release(first);
        assertTrue(directory.check(first));
        assertFalse(directory.check(second));
        assertEquals(first, directory.get());
        assertFalse(directory.check(first));
    }

    @Test
    public void testReleaseDoesNotMakeAnAssignedNumberDuplicate() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(4);
        int released = directory.get();
        directory.release(released);
        int reused = directory.get();
        assertEquals(released, reused);
        assertFalse(directory.check(reused));
        assertNotEquals(reused, directory.get());
    }

    @Test
    public void testReleaseOfAlreadyAvailableNumberIsNoOp() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(3);
        directory.release(2);
        assertTrue(directory.check(2));
        assertEquals(0, directory.get());
        assertEquals(1, directory.get());
        assertEquals(2, directory.get());
        assertEquals(-1, directory.get());
    }

    @Test
    public void testDoubleReleaseIsIdempotent() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(3);
        int number = directory.get();
        directory.release(number);
        directory.release(number);
        assertTrue(directory.check(number));
        assertEquals(number, directory.get());
        assertNotEquals(number, directory.get());
        assertNotEquals(number, directory.get());
        assertEquals(-1, directory.get());
    }

    @Test
    public void testAllNumbersCanBeReleasedAndReused() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(5);
        Set<Integer> allocated = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            allocated.add(directory.get());
        }
        assertEquals(-1, directory.get());
        for (int number : allocated) {
            directory.release(number);
        }
        Set<Integer> reused = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            reused.add(directory.get());
        }
        assertEquals(allocated, reused);
        assertEquals(-1, directory.get());
    }

    @Test
    public void testMultipleReleasedNumbersAreReturnedWithoutDuplication() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(6);
        for (int i = 0; i < 6; i++) {
            assertNotEquals(-1, directory.get());
        }
        directory.release(1);
        directory.release(4);
        directory.release(5);
        Set<Integer> expected = Set.of(1, 4, 5);
        Set<Integer> actual = new HashSet<>();
        actual.add(directory.get());
        actual.add(directory.get());
        actual.add(directory.get());
        assertEquals(expected, actual);
        assertEquals(-1, directory.get());
    }

    @Test
    public void testReleaseAfterExhaustionRestoresOnlyThatSlot() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(4);
        for (int i = 0; i < 4; i++) {
            directory.get();
        }
        directory.release(2);
        assertTrue(directory.check(2));
        assertFalse(directory.check(0));
        assertFalse(directory.check(1));
        assertFalse(directory.check(3));
        assertEquals(2, directory.get());
        assertEquals(-1, directory.get());
    }

    @Test
    public void testSingletonDirectoryLifecycle() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(1);
        assertTrue(directory.check(0));
        assertEquals(0, directory.get());
        assertFalse(directory.check(0));
        assertEquals(-1, directory.get());
        directory.release(0);
        assertTrue(directory.check(0));
        assertEquals(0, directory.get());
        assertEquals(-1, directory.get());
    }

    @Test
    public void testCheckDoesNotConsumeAvailability() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(4);
        for (int i = 0; i < 20; i++) {
            assertTrue(directory.check(3));
        }
        assertEquals(0, directory.get());
        assertEquals(1, directory.get());
        assertTrue(directory.check(3));
    }

    @Test
    public void testReuseCanInterleaveWithNewAllocations() {
        PhoneDirectory_379 directory = new PhoneDirectory_379(4);
        int first = directory.get();
        int second = directory.get();
        directory.release(first);
        int recycled = directory.get();
        int third = directory.get();
        assertEquals(first, recycled);
        assertNotEquals(second, third);
        assertFalse(directory.check(first));
        assertFalse(directory.check(second));
        assertFalse(directory.check(third));
        assertTrue(directory.check(3));
    }

    @Test
    public void testIndependentDirectoriesDoNotShareState() {
        PhoneDirectory_379 first = new PhoneDirectory_379(3);
        PhoneDirectory_379 second = new PhoneDirectory_379(3);
        assertEquals(0, first.get());
        assertTrue(second.check(0));
        assertEquals(0, second.get());
        first.release(0);
        assertTrue(first.check(0));
        assertFalse(second.check(0));
        assertEquals(0, first.get());
        assertEquals(1, second.get());
    }

    @Test
    public void testFreshInstanceAfterPriorInstanceExhaustion() {
        PhoneDirectory_379 exhausted = new PhoneDirectory_379(2);
        exhausted.get();
        exhausted.get();
        assertEquals(-1, exhausted.get());

        PhoneDirectory_379 fresh = new PhoneDirectory_379(2);
        assertTrue(fresh.check(0));
        assertTrue(fresh.check(1));
        assertEquals(0, fresh.get());
    }

    @Test
    public void testMaximumCapacityAndBoundarySlots() {
        int capacity = 10_000;
        PhoneDirectory_379 directory = new PhoneDirectory_379(capacity);
        Set<Integer> allocated = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            int number = directory.get();
            assertTrue(number >= 0 && number < capacity);
            assertTrue(allocated.add(number));
        }
        assertFalse(directory.check(0));
        assertFalse(directory.check(capacity / 2));
        assertFalse(directory.check(capacity - 1));
        assertEquals(-1, directory.get());
        directory.release(0);
        directory.release(capacity - 1);
        assertTrue(directory.check(0));
        assertTrue(directory.check(capacity - 1));
        int firstReused = directory.get();
        int secondReused = directory.get();
        assertEquals(Set.of(0, capacity - 1), Set.of(firstReused, secondReused));
        assertEquals(-1, directory.get());
    }

    @Test
    public void testSeededOracleSequenceSmallCapacity() {
        runAgainstAvailableSetOracle(3, 1_500, 0x379L);
    }

    @Test
    public void testSeededOracleSequenceWithRepeatedRelease() {
        runAgainstAvailableSetOracle(11, 4_000, 0x379BEEFL);
    }

    @Test
    public void testSeededOracleSequenceLargerCapacity() {
        runAgainstAvailableSetOracle(97, 8_000, 0x5EEDL);
    }

    @Test
    public void testExactLeetCodeOperationBudget() {
        runAgainstAvailableSetOracle(37, 20_000, 0x20_000L);
    }

    private static void runAgainstAvailableSetOracle(int capacity, int operations, long seed) {
        PhoneDirectory_379 directory = new PhoneDirectory_379(capacity);
        Set<Integer> available = new HashSet<>();
        for (int number = 0; number < capacity; number++) {
            available.add(number);
        }

        Random random = new Random(seed);
        for (int operation = 0; operation < operations; operation++) {
            int number = random.nextInt(capacity);
            switch (random.nextInt(3)) {
                case 0 -> {
                    int actual = directory.get();
                    if (available.isEmpty()) {
                        assertEquals(-1, actual);
                    } else {
                        assertTrue(available.remove(actual),
                                "get must return one currently available number");
                    }
                }
                case 1 -> assertEquals(available.contains(number), directory.check(number));
                case 2 -> {
                    directory.release(number);
                    available.add(number);
                }
                default -> throw new AssertionError("unreachable");
            }
        }
    }
}
