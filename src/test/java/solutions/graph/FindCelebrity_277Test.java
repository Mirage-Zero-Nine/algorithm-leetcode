package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindCelebrity_277Test {

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

    @Test
    public void testFivePeople() {
        assertEquals(0, test.findCelebrity(5));
    }

    @Test
    public void testSixPeople() {
        assertEquals(0, test.findCelebrity(6));
    }

    @Test
    public void testSevenPeople() {
        assertEquals(0, test.findCelebrity(7));
    }

    @Test
    public void testEightPeople() {
        assertEquals(0, test.findCelebrity(8));
    }

    @Test
    public void testNinePeople() {
        assertEquals(0, test.findCelebrity(9));
    }

    @Test
    public void testGiantCase() {
        // With knows(a,b) = a > b, person 0 is always the celebrity
        assertEquals(0, test.findCelebrity(100));
    }

    @Test
    public void testTwoPeople() {
        assertEquals(0, test.findCelebrity(2));
    }
}
