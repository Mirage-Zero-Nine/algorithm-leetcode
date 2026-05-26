package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PhoneDirectory_379Test {

    @Test
    public void testHappyCases() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(3);
        assertEquals(0, pd.get());
        assertEquals(1, pd.get());
        assertTrue(pd.check(2));
        assertEquals(2, pd.get());
        assertFalse(pd.check(2));
        pd.release(2);
        assertTrue(pd.check(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(1);
        assertEquals(0, pd.get());
        assertEquals(-1, pd.get());
    }

    @Test
    public void testLargeCase() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(5);
        for (int i = 0; i < 5; i++) pd.get();
        assertEquals(-1, pd.get());
        pd.release(3);
        assertEquals(3, pd.get());
    }

    @Test
    public void testReleaseAndReuse() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(3);
        assertEquals(0, pd.get());
        assertEquals(1, pd.get());
        pd.release(0);
        assertEquals(0, pd.get());
    }

    @Test
    public void testCheckBeforeGet() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(4);
        assertTrue(pd.check(0));
        assertTrue(pd.check(1));
        assertTrue(pd.check(2));
        assertTrue(pd.check(3));
    }

    @Test
    public void testReleaseAlreadyAvailable() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(3);
        pd.release(0); // releasing a number that's already available should be no-op
        assertTrue(pd.check(0));
        assertEquals(0, pd.get());
    }

    @Test
    public void testAllNumbersTakenThenReleaseAll() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(3);
        pd.get(); pd.get(); pd.get();
        assertEquals(-1, pd.get());
        pd.release(0);
        pd.release(1);
        pd.release(2);
        assertTrue(pd.check(0));
        assertTrue(pd.check(1));
        assertTrue(pd.check(2));
    }

    @Test
    public void testGetAfterMultipleReleases() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(4);
        pd.get(); pd.get(); pd.get(); pd.get();
        pd.release(2);
        pd.release(0);
        // released numbers should be available
        int n1 = pd.get();
        int n2 = pd.get();
        assertTrue(n1 == 0 || n1 == 2);
        assertTrue(n2 == 0 || n2 == 2);
        assertEquals(-1, pd.get());
    }

    @Test
    public void testCheckAfterGet() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(2);
        pd.get();
        assertFalse(pd.check(0));
        assertTrue(pd.check(1));
    }

    @Test
    public void testGiantCase() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(1000);
        for (int i = 0; i < 1000; i++) {
            int num = pd.get();
            assertTrue(num >= 0 && num < 1000);
        }
        assertEquals(-1, pd.get());
        for (int i = 0; i < 1000; i++) pd.release(i);
        for (int i = 0; i < 1000; i++) {
            int num = pd.get();
            assertTrue(num >= 0 && num < 1000);
        }
        assertEquals(-1, pd.get());
    }

    @Test
    public void testDoubleRelease() {
        PhoneDirectory_379 pd = new PhoneDirectory_379(3);
        int n = pd.get();
        pd.release(n);
        pd.release(n); // double release should not corrupt state
        assertTrue(pd.check(n));
        int got = pd.get();
        assertEquals(n, got);
    }
}
