package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DefangIPaddr1108Test {

    private final DefangIPaddr_1108 test = new DefangIPaddr_1108();

    @Test
    public void testHappyCases() {
        assertEquals("1[.]1[.]1[.]1", test.defangIPaddr("1.1.1.1"));
        assertEquals("255[.]100[.]50[.]0", test.defangIPaddr("255.100.50.0"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("0[.]0[.]0[.]0", test.defangIPaddr("0.0.0.0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("192[.]168[.]1[.]100", test.defangIPaddr("192.168.1.100"));
    }
}
