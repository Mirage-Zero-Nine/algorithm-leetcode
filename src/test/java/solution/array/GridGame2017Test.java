package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/07 15:20
 * Created with IntelliJ IDEA
 */

public class GridGame2017Test {

    private final GridGame_2017 test = new GridGame_2017();

    @Test
    public void test() {
        assertEquals(4, test.gridGame(new int[][]{{2,5,4},{1,5,1}}));
        assertEquals(4, test.gridGame(new int[][]{{3,3,1},{8,5,2}}));
        assertEquals(7, test.gridGame(new int[][]{{1,3,1,15},{1,3,3,1}}));
    }
}