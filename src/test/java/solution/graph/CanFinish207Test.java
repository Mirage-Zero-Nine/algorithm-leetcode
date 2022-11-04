package solution.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:11
 * Created with IntelliJ IDEA
 */

public class CanFinish207Test {

    private final CanFinish_207 test = new CanFinish_207();

    @Test
    public void test() {
        int[][] prerequisites = new int[][]{{1, 0}};
        assertTrue(test.canFinish(2, prerequisites));
        assertTrue(test.intArray(2, prerequisites));

        prerequisites = new int[][]{{1, 0}, {0, 1}};
        assertFalse(test.canFinish(2, prerequisites));
        assertFalse(test.intArray(2, prerequisites));
    }
}