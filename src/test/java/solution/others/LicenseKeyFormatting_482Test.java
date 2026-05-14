package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LicenseKeyFormatting_482Test {

    private final LicenseKeyFormatting_482 test = new LicenseKeyFormatting_482();

    @Test
    public void testHappyCases() {
        assertEquals("5F3Z-2E9W", test.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        assertEquals("2-5G-3J", test.licenseKeyFormatting("2-5g-3-J", 2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("A", test.licenseKeyFormatting("a", 1));
        assertEquals("AB", test.licenseKeyFormatting("a-b", 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals("ABCDE-FGHIJ", test.licenseKeyFormatting("abcde-fghij", 5));
    }

    @Test
    public void testAllDashesExceptOne() {
        assertEquals("A", test.licenseKeyFormatting("---a---", 3));
    }

    @Test
    public void testGroupSizeOne() {
        assertEquals("A-B-C-D", test.licenseKeyFormatting("abcd", 1));
    }

    @Test
    public void testNoDashes() {
        assertEquals("ABC-DEF", test.licenseKeyFormatting("abcdef", 3));
    }

    @Test
    public void testAllUpperCase() {
        assertEquals("AA-AA", test.licenseKeyFormatting("AA-AA", 2));
    }

    @Test
    public void testFirstGroupShorter() {
        assertEquals("A-BCDE", test.licenseKeyFormatting("a-b-c-d-e", 4));
    }

    @Test
    public void testNegativeDashesOnly() {
        assertEquals("", test.licenseKeyFormatting("---", 2));
    }

    @Test
    public void testGiantCase() {
        String input = "a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a-a";
        String result = test.licenseKeyFormatting(input, 4);
        // 50 'a' chars, grouped by 4 from right: 2-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA
        assertEquals(50 + 12, result.length()); // 50 chars + 12 dashes
        assertEquals("AA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA-AAAA", result);
    }
}
