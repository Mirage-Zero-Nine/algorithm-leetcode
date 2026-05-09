package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindCelebrity277Test {

    private final FindCelebrity_277 test = new FindCelebrity_277();

    @Test
    public void testHappyCases() {
        // The internal knows(a,b) returns a>b, so person 0 is known by all but knows nobody
        assertEquals(0, test.findCelebrity(3));
        assertEquals(0, test.findCelebrity(2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.findCelebrity(1));
        assertEquals(0, test.findCelebrity(4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.findCelebrity(10));
    }
}
