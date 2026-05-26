package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.NestedInteger;
import library.NestedIntegerHelper;
import org.junit.jupiter.api.Test;

public class NestedIterator_341Test {

    private NestedInteger makeInt(int val) { return new NestedInteger(val); }

    private NestedInteger makeList(NestedInteger... items) { return new NestedIntegerHelper(items); }

    private List<Integer> flatten(List<NestedInteger> nestedList) {
        NestedIterator_341 iter = new NestedIterator_341(nestedList);
        List<Integer> result = new ArrayList<>();
        while (iter.hasNext()) result.add(iter.next());
        return result;
    }

    @Test
    public void testHappyCases() {
        // [[1,1],2,[1,1]]
        NestedInteger list1 = new NestedIntegerHelper(makeInt(1), makeInt(1));
        NestedInteger list2 = new NestedIntegerHelper(makeInt(1), makeInt(1));
        NestedIterator_341 iter = new NestedIterator_341(Arrays.asList(list1, makeInt(2), list2));
        List<Integer> result = new ArrayList<>();
        while (iter.hasNext()) result.add(iter.next());
        assertEquals(Arrays.asList(1, 1, 2, 1, 1), result);
    }

    @Test
    public void testEdgeCases() {
        NestedIterator_341 iter = new NestedIterator_341(Arrays.asList(makeInt(1)));
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testLargeCase() {
        List<NestedInteger> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) list.add(makeInt(i));
        NestedIterator_341 iter = new NestedIterator_341(list);
        for (int i = 1; i <= 5; i++) assertEquals(i, iter.next());
    }

    @Test
    public void testDeeplyNested() {
        // [[[1]]]
        NestedInteger inner = makeList(makeInt(1));
        NestedInteger mid = makeList(inner);
        assertEquals(Arrays.asList(1), flatten(Arrays.asList(mid)));
    }

    @Test
    public void testEmptyLists() {
        // [[], [1], []]
        NestedInteger empty1 = makeList();
        NestedInteger empty2 = makeList();
        assertEquals(Arrays.asList(1), flatten(Arrays.asList(empty1, makeInt(1), empty2)));
    }

    @Test
    public void testAllEmptyLists() {
        // [[], [[]], []]
        NestedInteger empty = makeList();
        NestedInteger nestedEmpty = makeList(makeList());
        NestedIterator_341 iter = new NestedIterator_341(Arrays.asList(empty, nestedEmpty, empty));
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMultipleHasNextCalls() {
        NestedIterator_341 iter = new NestedIterator_341(Arrays.asList(makeInt(5)));
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(5, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMixedNestedAndFlat() {
        // [1,[4,[6]]]
        NestedInteger innerList = makeList(makeInt(4), makeList(makeInt(6)));
        assertEquals(Arrays.asList(1, 4, 6), flatten(Arrays.asList(makeInt(1), innerList)));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(Arrays.asList(-1, -2, -3), flatten(Arrays.asList(makeInt(-1), makeInt(-2), makeInt(-3))));
    }

    @Test
    public void testSingleNestedList() {
        // [[1,2,3]]
        NestedInteger list = makeList(makeInt(1), makeInt(2), makeInt(3));
        assertEquals(Arrays.asList(1, 2, 3), flatten(Arrays.asList(list)));
    }

    @Test
    public void testGiantCase() {
        List<NestedInteger> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(makeList(makeInt(i), makeInt(i + 1)));
        }
        NestedIterator_341 iter = new NestedIterator_341(list);
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, iter.next());
            assertEquals(i + 1, iter.next());
        }
        assertFalse(iter.hasNext());
    }
}
