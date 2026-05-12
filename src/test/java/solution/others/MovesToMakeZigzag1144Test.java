package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovesToMakeZigzag1144Test {

    private final MovesToMakeZigzag_1144 test = new MovesToMakeZigzag_1144();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.movesToMakeZigzag(new int[]{1, 2, 3}));
        assertEquals(4, test.movesToMakeZigzag(new int[]{9, 6, 1, 6, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.movesToMakeZigzag(new int[]{1}));
        assertEquals(0, test.movesToMakeZigzag(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.movesToMakeZigzag(new int[]{1, 2, 3, 4, 5}));
    }
}
