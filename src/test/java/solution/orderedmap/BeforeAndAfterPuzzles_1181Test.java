package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BeforeAndAfterPuzzles_1181Test {

    private final BeforeAndAfterPuzzles_1181 test = new BeforeAndAfterPuzzles_1181();

    @Test
    public void testHappyCases() {
        assertEquals(
            List.of("a chip off the old block party", "a man on a mission impossible", "a man on a mission statement",
                "a quick bite to eat my words", "chocolate bar of soap"),
            test.beforeAndAfterPuzzles(new String[]{
                "mission statement", "a quick bite to eat", "a chip off the old block",
                "chocolate bar", "mission impossible", "a man on a mission", "block party", "eat my words", "bar of soap"
            })
        );

        assertEquals(
            List.of("writing code rocks"),
            test.beforeAndAfterPuzzles(new String[]{"writing code", "code rocks"})
        );
    }

    @Test
    public void testNegativeCases() {
        assertTrue(test.beforeAndAfterPuzzles(new String[]{"hello world", "java rocks"}).isEmpty());
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertTrue(test.beforeAndAfterPuzzles(new String[0]).isEmpty());
        assertTrue(test.beforeAndAfterPuzzles(new String[]{"solo"}).isEmpty());
        assertEquals(
            List.of("a"),
            test.beforeAndAfterPuzzles(new String[]{"a", "a"})
        );
    }

    @Test
    public void testLargeCase() {
        String[] phrases = {
            "one two", "two three", "three four", "four five", "five six",
            "alpha beta", "beta gamma", "gamma delta", "delta epsilon", "epsilon zeta"
        };
        List<String> result = test.beforeAndAfterPuzzles(phrases);
        assertTrue(result.contains("one two three"));
        assertTrue(result.contains("two three four"));
        assertTrue(result.contains("alpha beta gamma"));
        assertTrue(result.contains("delta epsilon zeta"));
    }
}
