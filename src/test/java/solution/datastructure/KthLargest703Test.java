package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthLargest703Test {

    @Test
    public void testHappyCases() {
        KthLargest_703 kl = new KthLargest_703(3, new int[]{4, 5, 8, 2});
        assertEquals(4, kl.add(3));
        assertEquals(5, kl.add(5));
        assertEquals(5, kl.add(10));
        assertEquals(8, kl.add(9));
        assertEquals(8, kl.add(4));
    }

    @Test
    public void testEdgeCases() {
        KthLargest_703 kl = new KthLargest_703(1, new int[]{});
        assertEquals(1, kl.add(1));
        assertEquals(2, kl.add(2));
    }

    @Test
    public void testLargeCase() {
        KthLargest_703 kl = new KthLargest_703(2, new int[]{1, 2, 3, 4, 5});
        assertEquals(5, kl.add(6));
        assertEquals(6, kl.add(7));
    }
}
