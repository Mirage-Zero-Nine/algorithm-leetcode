package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstUnique1429Test {

    @Test
    public void testHappyCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{2, 3, 5});
        assertEquals(2, fu.showFirstUnique());
        fu.add(5);
        assertEquals(2, fu.showFirstUnique());
        fu.add(2);
        assertEquals(3, fu.showFirstUnique());
        fu.add(3);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 1});
        assertEquals(-1, fu.showFirstUnique());
        FirstUnique_1429 fu2 = new FirstUnique_1429(new int[]{1});
        assertEquals(1, fu2.showFirstUnique());
    }

    @Test
    public void testLargeCase() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 3, 4, 5});
        assertEquals(1, fu.showFirstUnique());
        fu.add(1); fu.add(2); fu.add(3); fu.add(4);
        assertEquals(5, fu.showFirstUnique());
    }
}
