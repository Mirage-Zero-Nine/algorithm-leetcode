package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class DepthSumInverse_364Test {

    private final DepthSumInverse_364 test = new DepthSumInverse_364();

    @Test
    public void testHappyCases() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(1),
                n3 = new NestedInteger(2), n4 = new NestedInteger(1), n5 = new NestedInteger(1);
        assertEquals(6, test.depthSumInverse(List.of(n1, n2, n3, n4, n5)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.depthSumInverse(null));
        assertEquals(0, test.depthSumInverse(List.of()));
    }

    @Test
    public void testLargeCase() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2), n3 = new NestedInteger(3);
        assertEquals(6, test.depthSumInverse(List.of(n1, n2, n3)));
    }

    @Test
    public void testSingleInteger() {
        assertEquals(5, test.depthSumInverse(List.of(new NestedInteger(5))));
    }

    @Test
    public void testFlatListAllSameDepth() {
        assertEquals(10, test.depthSumInverse(List.of(
                new NestedInteger(1),
                new NestedInteger(2),
                new NestedInteger(3),
                new NestedInteger(4)
        )));
    }

    @Test
    public void testTwoLevelNestedList() {
        NestedInteger list1 = listOf(new NestedInteger(1), new NestedInteger(1));
        NestedInteger list2 = listOf(new NestedInteger(1), new NestedInteger(1));
        NestedInteger two = new NestedInteger(2);
        assertEquals(8, test.depthSumInverse(List.of(list1, two, list2)));
    }

    @Test
    public void testDeepSingleChain() {
        NestedInteger bottom = new NestedInteger(9);
        NestedInteger l1 = listOf(bottom);
        NestedInteger l2 = listOf(l1);
        NestedInteger l3 = listOf(l2);
        assertEquals(9, test.depthSumInverse(List.of(l3)));
    }

    @Test
    public void testMixedDepthValues() {
        NestedInteger one = new NestedInteger(1);
        NestedInteger deep = listOf(listOf(new NestedInteger(2)));
        assertEquals(5, test.depthSumInverse(List.of(one, deep)));
    }

    @Test
    public void testNegativeValues() {
        NestedInteger l = listOf(new NestedInteger(-1), new NestedInteger(-2));
        assertEquals(-3, test.depthSumInverse(List.of(l)));
    }

    @Test
    public void testListWithEmptyNestedLists() {
        NestedInteger empty1 = new NestedInteger();
        NestedInteger empty2 = new NestedInteger();
        assertEquals(0, test.depthSumInverse(List.of(empty1, empty2)));
    }

    @Test
    public void testGiantDepthStructure() {
        NestedInteger current = new NestedInteger(1);
        for (int i = 0; i < 200; i++) {
            current = listOf(current);
        }
        assertEquals(1, test.depthSumInverse(List.of(current)));
    }

    private NestedInteger listOf(NestedInteger... values) {
        NestedInteger out = new NestedInteger();
        for (NestedInteger value : values) {
            out.add(value);
        }
        return out;
    }
}
