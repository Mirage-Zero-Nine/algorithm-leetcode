package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BorisMirage
 * Time: 2022/11/10 23:22
 * Created with IntelliJ IDEA
 */

public class Intersection350Test {

    private final Intersection_350 test = new Intersection_350();

    @Test
    public void test() {

        List<Integer> output = Arrays.stream(test.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}))
                .boxed()
                .collect(Collectors.toList());
        assertEquals(2, output.size());
        assertTrue(Lists.newArrayList(2, 2).containsAll(output));
    }

    @Test
    public void test1() {
        List<Integer> output = Arrays.stream(test.intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}))
                .boxed()
                .collect(Collectors.toList());
        assertEquals(2, output.size());
        assertTrue(Lists.newArrayList(4, 9).containsAll(output));
    }
}