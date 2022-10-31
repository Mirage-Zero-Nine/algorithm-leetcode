package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/31 00:03
 * Created with IntelliJ IDEA
 */

public class FindCheapestPrice787Test {

    private final FindCheapestPrice_787 test = new FindCheapestPrice_787();

    @Test
    public void test() {
        int[][] flight = new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        assertEquals(700, test.findCheapestPrice(4, flight, 0, 3, 1));
        assertEquals(700, test.findCheapestPriceDFS(4, flight, 0, 3, 1));

        flight = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        assertEquals(200, test.findCheapestPrice(3, flight, 0, 2, 1));
        assertEquals(200, test.findCheapestPriceDFS(3, flight, 0, 2, 1));

        flight = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        assertEquals(500, test.findCheapestPrice(3, flight, 0, 2, 0));
        assertEquals(500, test.findCheapestPriceDFS(3, flight, 0, 2, 0));

    }

    @Test
    public void testInvalid() {
        int[][] flight = new int[][]{{11, 12, 74}, {1, 8, 91}, {4, 6, 13}, {7, 6, 39}, {5, 12, 8}, {0, 12, 54}, {8, 4, 32}, {0, 11, 4}, {4, 0, 91}, {11, 7, 64}, {6, 3, 88}, {8, 5, 80}, {11, 10, 91}, {10, 0, 60}, {8, 7, 92}, {12, 6, 78}, {6, 2, 8}, {4, 3, 54}, {3, 11, 76}, {3, 12, 23}, {11, 6, 79}, {6, 12, 36}, {2, 11, 100}, {2, 5, 49}, {7, 0, 17}, {5, 8, 95}, {3, 9, 98}, {8, 10, 61}, {2, 12, 38}, {5, 7, 58}, {9, 4, 37}, {8, 6, 79}, {9, 0, 1}, {2, 3, 12}, {7, 10, 7}, {12, 10, 52}, {7, 2, 68}, {12, 2, 100}, {6, 9, 53}, {7, 4, 90}, {0, 5, 43}, {11, 2, 52}, {11, 8, 50}, {12, 4, 38}, {7, 9, 94}, {2, 7, 38}, {3, 7, 88}, {9, 12, 20}, {12, 0, 26}, {10, 5, 38}, {12, 8, 50}, {0, 2, 77}, {11, 0, 13}, {9, 10, 76}, {2, 6, 67}, {5, 6, 34}, {9, 7, 62}, {5, 3, 67}};
        assertEquals(-1, test.findCheapestPrice(13, flight, 10, 1, 10));
        assertEquals(-1, test.findCheapestPriceDFS(13, flight, 10, 1, 10));

        flight = new int[][]{{4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}};
        assertEquals(-1, test.findCheapestPrice(5, flight, 2, 1, 1));
        assertEquals(-1, test.findCheapestPriceDFS(5, flight, 2, 1, 1));
    }
}