package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HighFive1086Test {

    private final HighFive_1086 test = new HighFive_1086();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[][]{{1, 87}, {2, 88}},
            test.highFive(new int[][]{{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}}));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new int[][]{{1, 100}},
            test.highFive(new int[][]{{1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 100}}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[][]{{1, 90}, {2, 90}},
            test.highFive(new int[][]{{1, 90}, {1, 90}, {1, 90}, {1, 90}, {1, 90}, {1, 80},
                {2, 90}, {2, 90}, {2, 90}, {2, 90}, {2, 90}, {2, 80}}));
    }
}
