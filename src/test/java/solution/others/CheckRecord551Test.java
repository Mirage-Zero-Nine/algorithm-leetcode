package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckRecord551Test {

    private final CheckRecord_551 test = new CheckRecord_551();

    @Test
    public void testHappyCases() {
        assertTrue(test.checkRecord("PPALLP"));
        assertFalse(test.checkRecord("PPALLL"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.checkRecord("P"));
        assertFalse(test.checkRecord("AA"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.checkRecord("PPPPPPPPPP"));
        assertFalse(test.checkRecord("AALLPALLP"));
    }
}
