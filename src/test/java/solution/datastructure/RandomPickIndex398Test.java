package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RandomPickIndex398Test {

    @Test
    public void testHappyCases() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 3, 3, 3});
        int idx = rp.pick(3);
        assertTrue(idx == 2 || idx == 3 || idx == 4);
    }

    @Test
    public void testEdgeCases() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1});
        assertTrue(rp.pick(1) == 0);
    }

    @Test
    public void testLargeCase() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 3, 4, 5, 1, 1, 1});
        int idx = rp.pick(1);
        assertTrue(idx == 0 || idx == 5 || idx == 6 || idx == 7);
    }
}
