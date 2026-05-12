package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TitleToNumber171Test {

    private final TitleToNumber_171 test = new TitleToNumber_171();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.titleToNumber("A"));
        assertEquals(28, test.titleToNumber("AB"));
        assertEquals(701, test.titleToNumber("ZY"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(26, test.titleToNumber("Z"));
        assertEquals(27, test.titleToNumber("AA"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(18278, test.titleToNumber("ZZZ"));
    }
}
