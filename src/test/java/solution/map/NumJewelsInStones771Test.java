package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/09 20:39
 * Created with IntelliJ IDEA
 */

public class NumJewelsInStones771Test {


    private final NumJewelsInStones_771 test = new NumJewelsInStones_771();

    @Test
    public void test() {
        assertEquals(3, test.numJewelsInStones("aA", "aAAbbbb"));
    }

    @Test
    public void testEmpty() {
        assertEquals(0, test.numJewelsInStones("z", "ZZ"));
    }
}