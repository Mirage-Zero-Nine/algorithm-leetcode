package solution.stack;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ExclusiveTime_636Test {
    private final ExclusiveTime_636 e = new ExclusiveTime_636();

    @Test public void testSimple() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:7"));
        assertArrayEquals(new int[]{4, 4}, result);
    }
    @Test public void testSingleFunction() {
        int[] result = e.exclusiveTime(1, List.of("0:start:0", "0:end:3"));
        assertArrayEquals(new int[]{4}, result);
    }
    @Test public void testNested() {
        int[] result = e.exclusiveTime(3, List.of("0:start:0", "1:start:1", "1:end:2", "2:start:3", "2:end:4", "0:end:5"));
        assertArrayEquals(new int[]{2, 2, 2}, result);
    }
    @Test public void testAdjacent() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "0:end:1", "1:start:2", "1:end:3"));
        assertArrayEquals(new int[]{2, 2}, result);
    }
}
