package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/19 23:36
 * Created with IntelliJ IDEA
 */

public class CanConstruct383Test {

    private final CanConstruct_383 test = new CanConstruct_383();

    @Test
    public void test() {
        assertTrue(test.canConstruct("aa", "aab"));
    }

    @Test
    public void testFalse() {
        assertFalse(test.canConstruct("a", "b"));
        assertFalse(test.canConstruct("aa", "ab"));
    }
}