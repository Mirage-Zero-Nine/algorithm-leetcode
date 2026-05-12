package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.NestedInteger;
import library.NestedIntegerHelper;
import org.junit.jupiter.api.Test;

public class NestedIterator341Test {

    private NestedInteger makeInt(int val) { return new NestedInteger(val); }

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
}
