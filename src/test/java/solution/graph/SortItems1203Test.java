package solution.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:18
 * Created with IntelliJ IDEA
 */

public class SortItems1203Test {

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
}