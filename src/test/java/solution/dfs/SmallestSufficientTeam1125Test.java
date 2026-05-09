package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SmallestSufficientTeam1125Test {

    private final SmallestSufficientTeam_1125 test = new SmallestSufficientTeam_1125();

    @Test
    public void testHappyCases() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"java", "nodejs", "reactjs"},
            List.of(List.of("java"), List.of("nodejs"), List.of("nodejs", "reactjs"))
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testEdgeCases() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"algorithms", "math"},
            List.of(List.of("algorithms", "math"))
        );
        assertEquals(1, result.length);
    }

    @Test
    public void testLargeCase() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b", "c"},
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("a", "c"))
        );
        assertEquals(2, result.length);
    }
}
