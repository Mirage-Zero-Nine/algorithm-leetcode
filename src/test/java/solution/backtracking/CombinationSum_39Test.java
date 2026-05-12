package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSum_39Test {
    private final CombinationSum_39 solution = new CombinationSum_39();

    private Set<String> canonicalize(List<List<Integer>> combinations) {
        return combinations.stream()
                .map(combo -> combo.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.toSet());
    }

    @Test
    void testBasic() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        assertEquals(2, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5}, 8);
        assertEquals(Set.of("2,2,2,2", "2,3,3", "3,5"), canonicalize(result));
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2}, 1);
        assertEquals(0, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.combinationSum(new int[]{1}, 2);
        assertEquals(1, result.size());
    }

    @Test
    void testLargeTarget() {
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 5}, 8);
        assertEquals(3, result.size());
    }
}
