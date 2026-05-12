package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SuperEggDrop887Test {

    private final SuperEggDrop_887 test = new SuperEggDrop_887();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.superEggDrop(1, 2));
        assertEquals(6, test.superEggDrop(2, 6));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.superEggDrop(1, 1));
        assertEquals(1, test.superEggDrop(2, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.superEggDrop(2, 10));
    }
}
