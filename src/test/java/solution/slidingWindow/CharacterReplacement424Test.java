package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterReplacement424Test {

    private final CharacterReplacement_424 test = new CharacterReplacement_424();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.characterReplacement("ABAB", 2));
        assertEquals(4, test.characterReplacement("AABABBA", 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.characterReplacement("", 0));
        assertEquals(1, test.characterReplacement("A", 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.characterReplacement("AABABBAABB", 3));
    }
}
