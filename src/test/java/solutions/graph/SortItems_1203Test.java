package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:18
 * Created with IntelliJ IDEA
 */

public class SortItems_1203Test {

    private final SortItems_1203 test = new SortItems_1203();

    @Test
    public void test() {
        int[] group = new int[]{-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(6),
                Lists.newArrayList(5),
                Lists.newArrayList(6),
                Lists.newArrayList(3, 6),
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList()
        );
        List<Integer> expected = Lists.newArrayList(6, 3, 4, 1, 5, 2, 0, 7);
        int[] actual = test.sortItems(8, 2, group, beforeItems);
        assertEquals(expected.size(), actual.length);
        assertTrue(expected.containsAll(Arrays.stream(actual).boxed().collect(Collectors.toList())));
    }

    @Test
    public void testEmpty() {
        int[] group = new int[]{-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(6),
                Lists.newArrayList(5),
                Lists.newArrayList(6),
                Lists.newArrayList(3),
                Lists.newArrayList(),
                Lists.newArrayList(4),
                Lists.newArrayList()
        );
        int[] actual = test.sortItems(8, 2, group, beforeItems);
        assertEquals(0, actual.length);
        assertArrayEquals(new int[]{}, actual);
    }

    @Test
    public void testNoDependenciesKeepsAllItemsPresent() {
        int[] group = new int[]{0, 0, 1, 1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList()
        );
        int[] actual = test.sortItems(4, 2, Arrays.copyOf(group, group.length), beforeItems);
        assertValidOrder(4, group, beforeItems, actual);
    }

    @Test
    public void testSingleGroupWithItemDependencies() {
        int[] group = new int[]{0, 0, 0, 0};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(0),
                Lists.newArrayList(1),
                Lists.newArrayList(2)
        );
        int[] actual = test.sortItems(4, 1, Arrays.copyOf(group, group.length), beforeItems);
        assertArrayEquals(new int[]{0, 1, 2, 3}, actual);
    }

    @Test
    public void testCrossGroupDependencyRespected() {
        int[] group = new int[]{0, 0, 1, 1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList(1),
                Lists.newArrayList(2)
        );
        int[] actual = test.sortItems(4, 2, Arrays.copyOf(group, group.length), beforeItems);
        assertValidOrder(4, group, beforeItems, actual);
    }

    @Test
    public void testGroupCycleReturnsEmpty() {
        int[] group = new int[]{0, 1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(1),
                Lists.newArrayList(0)
        );
        int[] actual = test.sortItems(2, 2, Arrays.copyOf(group, group.length), beforeItems);
        assertEquals(0, actual.length);
    }

    @Test
    public void testItemCycleWithinSameGroupReturnsEmpty() {
        int[] group = new int[]{0, 0, 0};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(2),
                Lists.newArrayList(0),
                Lists.newArrayList(1)
        );
        int[] actual = test.sortItems(3, 1, Arrays.copyOf(group, group.length), beforeItems);
        assertEquals(0, actual.length);
    }

    @Test
    public void testAllUngroupedItemsWithLinearDependencies() {
        int[] group = new int[]{-1, -1, -1, -1, -1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(0),
                Lists.newArrayList(1),
                Lists.newArrayList(2),
                Lists.newArrayList(3)
        );
        int[] actual = test.sortItems(5, 0, Arrays.copyOf(group, group.length), beforeItems);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, actual);
    }

    @Test
    public void testMixedGroupedAndUngroupedDependenciesReturnsEmptyForCurrentImplementation() {
        int[] group = new int[]{0, -1, 0, -1, 1, 1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(0),
                Lists.newArrayList(1),
                Lists.newArrayList(2),
                Lists.newArrayList(3),
                Lists.newArrayList(4)
        );
        int[] actual = test.sortItems(6, 2, Arrays.copyOf(group, group.length), beforeItems);
        assertEquals(0, actual.length);
    }

    @Test
    public void testGiantCaseChainDependencies() {
        int n = 120;
        int[] group = new int[n];
        List<List<Integer>> beforeItems = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            group[i] = i / 10;
            beforeItems.add(Lists.newArrayList());
            if (i > 0) {
                beforeItems.get(i).add(i - 1);
            }
        }
        int[] actual = test.sortItems(n, 12, Arrays.copyOf(group, group.length), beforeItems);
        assertValidOrder(n, group, beforeItems, actual);
    }

    private void assertValidOrder(int n, int[] group, List<List<Integer>> beforeItems, int[] actual) {
        assertEquals(n, actual.length);

        Set<Integer> seen = new HashSet<>();
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < actual.length; i++) {
            int item = actual[i];
            assertTrue(item >= 0 && item < n);
            assertTrue(seen.add(item));
            pos.put(item, i);
        }

        for (int i = 0; i < n; i++) {
            for (int pre : beforeItems.get(i)) {
                assertTrue(pos.get(pre) < pos.get(i));
            }
        }

        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < actual.length; i++) {
            int item = actual[i];
            int g = group[item];
            if (g >= 0) {
                first.putIfAbsent(g, i);
                last.put(g, i);
            }
        }

        for (Map.Entry<Integer, Integer> e : first.entrySet()) {
            int g = e.getKey();
            int l = e.getValue();
            int r = last.get(g);
            for (int i = l; i <= r; i++) {
                assertEquals(g, group[actual[i]]);
            }
        }
    }
}
