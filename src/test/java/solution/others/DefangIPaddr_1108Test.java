package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DefangIPaddr_1108Test {

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

    @Test
    public void testMaxValues() {
        assertEquals("255[.]255[.]255[.]255", test.defangIPaddr("255.255.255.255"));
    }

    @Test
    public void testLocalhost() {
        assertEquals("127[.]0[.]0[.]1", test.defangIPaddr("127.0.0.1"));
    }

    @Test
    public void testNoDots() {
        assertEquals("12345", test.defangIPaddr("12345"));
    }

    @Test
    public void testSingleChar() {
        assertEquals("1", test.defangIPaddr("1"));
    }

    @Test
    public void testOnlyDots() {
        assertEquals("[.][.][.]", test.defangIPaddr("..."));
    }

    @Test
    public void testPrivateNetwork() {
        assertEquals("10[.]0[.]0[.]1", test.defangIPaddr("10.0.0.1"));
    }

    @Test
    public void testGiantRepeated() {
        StringBuilder input = new StringBuilder();
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            if (i > 0) {
                input.append(".");
                expected.append("[.]");
            }
            input.append("1");
            expected.append("1");
        }
        assertEquals(expected.toString(), test.defangIPaddr(input.toString()));
    }
}
