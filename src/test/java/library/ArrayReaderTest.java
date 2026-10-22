package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ArrayReaderTest {
    @Test void readsFirst() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{7, 8}); assertEquals(0, r.get(7)); }
    @Test void readsLast() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{7, 8}); assertEquals(1, r.get(8)); }
    @Test void missingReturnsSentinel() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{1}); assertEquals(Integer.MAX_VALUE, r.get(9)); }
    @Test void emptyReturnsSentinel() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{}); assertEquals(Integer.MAX_VALUE, r.get(0)); }
    @Test void duplicateKeepsLastIndex() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{3, 4, 3}); assertEquals(2, r.get(3)); }
    @Test void negativeValues() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{-2}); assertEquals(0, r.get(-2)); }
    @Test void zeroValue() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{0}); assertEquals(0, r.get(0)); }
    @Test void integerBoundaries() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}); assertEquals(1, r.get(Integer.MAX_VALUE)); }
    @Test void resetReplacesExistingKeys() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{1}); r.setArr(new int[]{2}); assertEquals(Integer.MAX_VALUE, r.get(1)); }
    @Test void resetUpdatesDuplicateIndex() { ArrayReader r = new ArrayReader(); r.setArr(new int[]{1, 1}); r.setArr(new int[]{1, 2, 1}); assertEquals(2, r.get(1)); }
}
