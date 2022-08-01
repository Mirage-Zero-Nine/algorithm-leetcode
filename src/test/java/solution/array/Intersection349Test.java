package solution.array;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BorisMirage
 * Time: 2022/07/31 19:24
 * Created with IntelliJ IDEA
 */

public class Intersection349Test {

    private final Intersection_349 test = new Intersection_349();

    @Test
    public void test() {
        List<Integer> expected = Arrays.asList(2, 5);
        List<Integer> actual = Arrays.stream(test.intersection(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                        new int[]{2, 2, 0, 0, 11, 5}
                ))
                .boxed()
                .collect(Collectors.toList());
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    public void test1() {
        List<Integer> expected = Arrays.asList(9, 4);
        List<Integer> actual = Arrays.stream(test.intersection(
                        new int[]{4, 9, 5},
                        new int[]{9, 4, 9, 8, 4}
                ))
                .boxed()
                .collect(Collectors.toList());
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    public void test2() {
        List<Integer> expected = Collections.singletonList(2);
        List<Integer> actual = Arrays.stream(test.intersection(
                        new int[]{1, 2, 2, 1},
                        new int[]{2, 2}
                ))
                .boxed()
                .collect(Collectors.toList());
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}