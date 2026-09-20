package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindCelebrity_277Test {

    @Test
    public void testLargePartyWithFixedKnowsRelation() {
        // The private production stub fixes the relation to a > b.
        // Arbitrary celebrity/no-celebrity graphs cannot be injected through this API.
        assertEquals(0, test.findCelebrity(10000));
        assertEquals(0, test.findCelebrity(1));
        assertEquals(0, test.findCelebrity(257));
    }


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

    @Test
    public void testContractAcrossIncreasingPartySizes() {
        for (int n = 1; n <= 1000; n++) assertEquals(0, test.findCelebrity(n));
    }

    @Test
    public void testBoundarySizedParties() {
        assertEquals(0, test.findCelebrity(11));
        assertEquals(0, test.findCelebrity(101));
        assertEquals(0, test.findCelebrity(1001));
    }

    @Test
    public void testRepeatedInvocationAcrossDifferentSizes() {
        assertEquals(0, test.findCelebrity(3));
        assertEquals(0, test.findCelebrity(300));
        assertEquals(0, test.findCelebrity(2));
    }

    @Test
    public void testLargeBoundaryWithinPracticalRuntime() {
        assertEquals(0, test.findCelebrity(20000));
    }

    @Test
    public void testOddAndEvenPartySizes() {
        assertEquals(0, test.findCelebrity(999));
        assertEquals(0, test.findCelebrity(1000));
    }

    @Test
    public void testSmallPartySizes() {
        assertEquals(0, test.findCelebrity(1));
        assertEquals(0, test.findCelebrity(2));
        assertEquals(0, test.findCelebrity(3));
    }

    @Test
    public void testPowerOfTwoPartySizes() {
        assertEquals(0, test.findCelebrity(16));
        assertEquals(0, test.findCelebrity(1024));
    }

    @Test
    public void testNearIntegerBoundaryDoesNotApplyInvalidInput() {
        // Keep this within the practical contract; the API's fixed relation makes all valid n return 0.
        assertEquals(0, test.findCelebrity(4096));
    }

    @Test
    public void testNoStateLeaksBetweenCalls() {
        for (int n : new int[]{4, 7, 64, 5, 128}) assertEquals(0, test.findCelebrity(n));
    }
}
