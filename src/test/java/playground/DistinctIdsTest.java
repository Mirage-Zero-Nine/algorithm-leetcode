package playground;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:20
 * Created with IntelliJ IDEA
 */

public class DistinctIdsTest {

    private final DistinctIds test = new DistinctIds();

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 1, 2, 2, 3};
        assertEquals(1, test.distinctIds(arr, arr.length, 6));
    }
}