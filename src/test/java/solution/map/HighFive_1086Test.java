package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HighFive_1086Test {

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

    @Test
    public void testAdditionalHappyCases() {
        assertArrayEquals(new int[][]{{1, 70}},
            test.highFive(new int[][]{{1, 50}, {1, 60}, {1, 70}, {1, 80}, {1, 90}}));
        assertArrayEquals(new int[][]{{1, 98}, {2, 70}},
            test.highFive(new int[][]{
                {1, 100}, {1, 99}, {1, 98}, {1, 97}, {1, 96}, {1, 10},
                {2, 50}, {2, 60}, {2, 70}, {2, 80}, {2, 90}
            }));
        assertArrayEquals(new int[][]{{1, 20}, {2, 40}, {3, 60}},
            test.highFive(new int[][]{
                {1, 0}, {1, 10}, {1, 20}, {1, 30}, {1, 40},
                {2, 20}, {2, 30}, {2, 40}, {2, 50}, {2, 60},
                {3, 40}, {3, 50}, {3, 60}, {3, 70}, {3, 80}
            }));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertArrayEquals(new int[][]{{1, 0}},
            test.highFive(new int[][]{{1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}}));
        assertArrayEquals(new int[][]{{1, 88}},
            test.highFive(new int[][]{{1, 88}, {1, 88}, {1, 88}, {1, 88}, {1, 88}, {1, 70}}));
        assertArrayEquals(new int[][]{{1, 52}, {2, 82}},
            test.highFive(new int[][]{
                {1, 50}, {1, 51}, {1, 52}, {1, 53}, {1, 54},
                {2, 80}, {2, 81}, {2, 82}, {2, 83}, {2, 84}
            }));
    }

    @Test
    public void testAdditionalGiantCase() {
        int[][] items = new int[50][2];
        for (int i = 0; i < 25; i++) {
            items[i][0] = 1;
            items[i][1] = 50 + i;
        }
        for (int i = 25; i < 50; i++) {
            items[i][0] = 2;
            items[i][1] = 70 + (i - 25);
        }
        assertArrayEquals(new int[][]{{1, 72}, {2, 92}}, test.highFive(items));
    }

    @Test
    public void testExactlyFiveScores() {
        assertArrayEquals(new int[][]{{1, 60}},
            test.highFive(new int[][]{{1, 40}, {1, 50}, {1, 60}, {1, 70}, {1, 80}}));
    }

    @Test
    public void testAllMaxScores() {
        assertArrayEquals(new int[][]{{1, 100}},
            test.highFive(new int[][]{{1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 0}, {1, 0}}));
    }

    @Test
    public void testThreeStudents() {
        assertArrayEquals(new int[][]{{1, 80}, {2, 60}, {3, 40}},
            test.highFive(new int[][]{
                {1, 60}, {1, 70}, {1, 80}, {1, 90}, {1, 100},
                {2, 40}, {2, 50}, {2, 60}, {2, 70}, {2, 80},
                {3, 20}, {3, 30}, {3, 40}, {3, 50}, {3, 60}
            }));
    }

    @Test
    public void testGiantCase() {
        int[][] items = new int[50][2];
        for (int i = 0; i < 50; i++) { items[i][0] = 1; items[i][1] = i + 1; }
        int[][] result = test.highFive(items);
        assertArrayEquals(new int[][]{{1, 48}}, result); // avg of top 5: 46+47+48+49+50=240/5=48
    }
}
