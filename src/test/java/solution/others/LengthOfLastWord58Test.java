package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLastWord58Test {

    private final LengthOfLastWord_58 test = new LengthOfLastWord_58();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.lengthOfLastWord("Hello World"));
        assertEquals(4, test.lengthOfLastWord("   fly me   to   the moon  "));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.lengthOfLastWord(""));
        assertEquals(0, test.lengthOfLastWord("   "));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.lengthOfLastWord("luffy is still joyboy"));
    }
}
