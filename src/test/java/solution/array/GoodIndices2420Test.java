package solution.array;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/06 23:36
 * Created with IntelliJ IDEA
 */

public class GoodIndices2420Test {

    private final GoodIndices_2420 test = new GoodIndices_2420();

    @Test
    public void test() {
        List<Integer> expected = Lists.newArrayList(2, 3);
        Assertions.assertEquals(expected, test.goodIndices(new int[]{2, 1, 1, 1, 3, 4, 1}, 2));
        expected = Lists.newArrayList(4, 5);
        Assertions.assertEquals(expected, test.goodIndices(new int[]{87, 20, 17, 9, 3, 32, 47, 59, 84, 94}, 4));
    }

    @Test
    public void testEmptyOutput() {
        List<Integer> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, test.goodIndices(new int[]{2, 1, 1, 2}, 2));
    }
}