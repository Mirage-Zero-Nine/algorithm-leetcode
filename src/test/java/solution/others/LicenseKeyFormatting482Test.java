package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LicenseKeyFormatting482Test {

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
}
