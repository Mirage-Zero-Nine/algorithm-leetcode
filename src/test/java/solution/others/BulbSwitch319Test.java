package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BulbSwitch319Test {

    private final BulbSwitch_319 test = new BulbSwitch_319();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.bulbSwitch(3));
        assertEquals(2, test.bulbSwitch(5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.bulbSwitch(0));
        assertEquals(1, test.bulbSwitch(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.bulbSwitch(100));
        assertEquals(31, test.bulbSwitch(1000));
    }
}
