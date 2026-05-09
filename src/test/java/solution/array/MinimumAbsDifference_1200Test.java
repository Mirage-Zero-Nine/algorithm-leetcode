package solution.array;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumAbsDifference_1200Test {
    private final MinimumAbsDifference_1200 solution = new MinimumAbsDifference_1200();

    @Test
    void testBasic() {
        assertEquals(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4)), solution.minimumAbsDifference(new int[]{4, 2, 1, 3}));
    }

    @Test
    void testTwoElements() {
        assertEquals(Arrays.asList(Arrays.asList(1, 3)), solution.minimumAbsDifference(new int[]{1, 3}));
    }

    @Test
    void testLargeGap() {
        assertEquals(Arrays.asList(Arrays.asList(-14, -10), Arrays.asList(19, 23), Arrays.asList(23, 27)), solution.minimumAbsDifference(new int[]{3, 8, -10, 23, 19, -4, -14, 27}));
    }

    @Test
    void testNegatives() {
        assertEquals(Arrays.asList(Arrays.asList(-3, -2), Arrays.asList(-2, -1)), solution.minimumAbsDifference(new int[]{-3, -2, -1}));
    }

    @Test
    void testDuplicateGaps() {
        assertEquals(Arrays.asList(Arrays.asList(1, 3), Arrays.asList(3, 5)), solution.minimumAbsDifference(new int[]{1, 3, 5}));
    }
}
