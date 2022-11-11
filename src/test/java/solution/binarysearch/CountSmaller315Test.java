package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/10 23:17
 * Created with IntelliJ IDEA
 */

public class CountSmaller315Test {

    private final CountSmaller_315 test = new CountSmaller_315();

    @Test
    public void test() {
        assertIterableEquals(Lists.newArrayList(2, 1, 1, 0), test.countSmaller(new int[]{5, 2, 6, 1}));
        assertIterableEquals(Lists.newArrayList(0, 0), test.countSmaller(new int[]{-1, -1}));
    }

    @Test
    public void testEmpty() {
        assertIterableEquals(Lists.newArrayList(0), test.countSmaller(new int[]{-1}));
    }

    @Test
    public void testBrutal() {
        assertIterableEquals(Lists.newArrayList(2, 1, 1, 0), test.brutal(new int[]{5, 2, 6, 1}));
        assertIterableEquals(Lists.newArrayList(0, 0), test.brutal(new int[]{-1, -1}));
    }

    @Test
    public void testBrutalEmpty() {
        assertIterableEquals(Lists.newArrayList(0), test.brutal(new int[]{-1}));
    }
}