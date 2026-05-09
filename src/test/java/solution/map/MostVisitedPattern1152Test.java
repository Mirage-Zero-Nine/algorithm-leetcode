package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MostVisitedPattern1152Test {

    private final MostVisitedPattern_1152 test = new MostVisitedPattern_1152();

    @Test
    public void testHappyCases() {
        assertEquals(List.of("home", "about", "career"),
            test.mostVisitedPattern(
                new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"}
            ));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1"},
                new int[]{1, 2, 3},
                new String[]{"a", "b", "c"}
            ));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u2", "u2", "u2"},
                new int[]{1, 2, 3, 4, 5, 6},
                new String[]{"a", "b", "c", "a", "b", "c"}
            ));
    }
}
