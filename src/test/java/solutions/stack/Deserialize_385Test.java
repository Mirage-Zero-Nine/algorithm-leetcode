package solutions.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class Deserialize_385Test {

    private final Deserialize_385 test = new Deserialize_385();

    @Test
    public void testHappyCases() {
        NestedInteger result = test.deserialize("324");
        assertEquals(324, result.getInteger());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deserialize(""));
        NestedInteger result = test.deserialize("[123,[456,[789]]]");
        assertEquals(123, result.getList().get(0).getInteger());
    }

    @Test
    public void testLargeCase() {
        NestedInteger result = test.deserialize("[1,2,3]");
        assertEquals(3, result.getList().size());
    }

    @Test
    public void testSingleNegativeNumber() {
        NestedInteger result = test.deserialize("-3");
        assertEquals(-3, result.getInteger());
    }

    @Test
    public void testNestedEmpty() {
        NestedInteger result = test.deserialize("[[]]");
        assertEquals(1, result.getList().size());
        assertEquals(0, result.getList().get(0).getList().size());
    }

    @Test
    public void testDeeplyNested() {
        NestedInteger result = test.deserialize("[1,[2,[3,[4]]]]");
        assertEquals(1, result.getList().get(0).getInteger());
        NestedInteger level2 = result.getList().get(1);
        assertEquals(2, level2.getList().get(0).getInteger());
        NestedInteger level3 = level2.getList().get(1);
        assertEquals(3, level3.getList().get(0).getInteger());
        assertEquals(4, level3.getList().get(1).getList().get(0).getInteger());
    }

    @Test
    public void testMultipleNegatives() {
        NestedInteger result = test.deserialize("[-1,-2,-3]");
        assertEquals(-1, result.getList().get(0).getInteger());
        assertEquals(-2, result.getList().get(1).getInteger());
        assertEquals(-3, result.getList().get(2).getInteger());
    }

    @Test
    public void testSingleZero() {
        NestedInteger result = test.deserialize("0");
        assertEquals(0, result.getInteger());
    }

    @Test
    public void testListWithZero() {
        NestedInteger result = test.deserialize("[0]");
        assertEquals(0, result.getList().get(0).getInteger());
    }

    @Test
    public void testGiantFlat() {
        // Build [1,2,3,...,100]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i <= 100; i++) {
            if (i > 1) sb.append(",");
            sb.append(i);
        }
        sb.append("]");
        NestedInteger result = test.deserialize(sb.toString());
        assertEquals(100, result.getList().size());
        assertEquals(1, result.getList().get(0).getInteger());
        assertEquals(100, result.getList().get(99).getInteger());
    }
}
