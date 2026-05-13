package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class DepthSum_339Test {

    private final DepthSum_339 test = new DepthSum_339();

    @Test
    public void testHappyCases() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2), n3 = new NestedInteger(3);
        assertEquals(6, test.depthSum(List.of(n1, n2, n3)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.depthSum(null));
        assertEquals(0, test.depthSum(List.of()));
    }

    @Test
    public void testLargeCase() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2);
        assertEquals(3, test.depthSum(List.of(n1, n2)));
    }

    @Test
    public void testNullList() {
        assertEquals(0, test.depthSum(null));
    }

    @Test
    public void testEmptyList() {
        assertEquals(0, test.depthSum(List.of()));
    }

    @Test
    public void testSingleInteger() {
        assertEquals(5, test.depthSum(List.of(new NestedInteger(5))));
    }

    @Test
    public void testNestedOneLevel() {
        // [[1,1],2,[1,1]] -> depth 2: 1+1+1+1=4*2=8, depth 1: 2*1=2, total=10
        NestedInteger inner1 = new NestedInteger();
        inner1.add(new NestedInteger(1));
        inner1.add(new NestedInteger(1));
        NestedInteger inner2 = new NestedInteger();
        inner2.add(new NestedInteger(1));
        inner2.add(new NestedInteger(1));
        assertEquals(10, test.depthSum(List.of(inner1, new NestedInteger(2), inner2)));
    }

    @Test
    public void testDeeplyNested() {
        // [1,[4,[6]]] -> 1*1 + 4*2 + 6*3 = 1 + 8 + 18 = 27
        NestedInteger innermost = new NestedInteger();
        innermost.add(new NestedInteger(6));
        NestedInteger middle = new NestedInteger();
        middle.add(new NestedInteger(4));
        middle.add(innermost);
        assertEquals(27, test.depthSum(List.of(new NestedInteger(1), middle)));
    }

    @Test
    public void testNegativeValues() {
        // [-1, [-2]] -> -1*1 + -2*2 = -1 + -4 = -5
        NestedInteger inner = new NestedInteger();
        inner.add(new NestedInteger(-2));
        assertEquals(-5, test.depthSum(List.of(new NestedInteger(-1), inner)));
    }

    @Test
    public void testZeroValues() {
        assertEquals(0, test.depthSum(List.of(new NestedInteger(0), new NestedInteger(0))));
    }

    @Test
    public void testEmptyNestedList() {
        // [[], 1] -> empty list contributes 0, 1*1 = 1
        NestedInteger emptyInner = new NestedInteger();
        assertEquals(1, test.depthSum(List.of(emptyInner, new NestedInteger(1))));
    }

    @Test
    public void testGiantCase() {
        // 100 integers at depth 1, each value 1 -> sum = 100
        List<NestedInteger> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new NestedInteger(1));
        }
        assertEquals(100, test.depthSum(list));
    }
}
