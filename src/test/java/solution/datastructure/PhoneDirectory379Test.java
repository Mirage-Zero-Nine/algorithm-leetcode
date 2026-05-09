package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PhoneDirectory379Test {

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
}
