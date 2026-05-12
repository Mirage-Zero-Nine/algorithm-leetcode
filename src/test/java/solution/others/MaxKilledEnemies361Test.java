package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxKilledEnemies361Test {

    private final MaxKilledEnemies_361 test = new MaxKilledEnemies_361();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxKilledEnemies(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{}));
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'W'}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.maxKilledEnemies(new char[][]{{'E', 'E', 'E'}, {'E', 'W', 'E'}, {'E', 'E', 'E'}}));
    }
}
