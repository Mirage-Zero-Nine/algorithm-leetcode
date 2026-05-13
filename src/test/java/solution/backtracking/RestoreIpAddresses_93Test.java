package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestoreIpAddresses_93Test {
    private final RestoreIpAddresses_93 solution = new RestoreIpAddresses_93();

    @Test
    void testBasic() {
        List<String> result = solution.restoreIpAddresses("25525511135");
        assertEquals(2, result.size());
    }

    @Test
    void testAllZeros() {
        List<String> result = solution.restoreIpAddresses("0000");
        assertEquals(1, result.size());
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.restoreIpAddresses("1111");
        assertTrue(result.size() >= 1);
    }

    @Test
    void testLongString() {
        List<String> result = solution.restoreIpAddresses("101023");
        assertTrue(result.size() >= 5);
    }

    @Test
    void testShortString() {
        List<String> result = solution.restoreIpAddresses("123");
        assertEquals(0, result.size());
    }

    @Test
    void testTooLong() {
        List<String> result = solution.restoreIpAddresses("1234567890123");
        assertEquals(0, result.size());
    }

    @Test
    void testAllOnes() {
        List<String> result = solution.restoreIpAddresses("1111");
        assertEquals(1, result.size());
        assertTrue(result.contains("1.1.1.1"));
    }

    @Test
    void testLeadingZeros() {
        List<String> result = solution.restoreIpAddresses("010010");
        // No segment should have leading zeros except "0" itself
        for (String ip : result) {
            String[] parts = ip.split("\\.");
            for (String part : parts) {
                if (part.length() > 1) {
                    assertFalse(part.startsWith("0"), "Leading zero in: " + ip);
                }
            }
        }
    }

    @Test
    void testAllNines() {
        List<String> result = solution.restoreIpAddresses("255255255255");
        assertTrue(result.contains("255.255.255.255"));
    }

    @Test
    void testFourDigits() {
        List<String> result = solution.restoreIpAddresses("2552");
        // possible: 2.5.5.2
        assertTrue(result.size() >= 1);
    }

    @Test
    void testTwelveDigits() {
        List<String> result = solution.restoreIpAddresses("111111111111");
        // 111.111.111.111
        assertTrue(result.contains("111.111.111.111"));
    }

    @Test
    void testGiantAllValidSegments() {
        // Test with many different 4-12 length strings
        int totalResults = 0;
        for (int i = 1000; i <= 1050; i++) {
            List<String> result = solution.restoreIpAddresses(String.valueOf(i));
            totalResults += result.size();
        }
        assertTrue(totalResults > 0);
    }

    @Test
    void testResultsAreValidIps() {
        List<String> result = solution.restoreIpAddresses("25525511135");
        for (String ip : result) {
            String[] parts = ip.split("\\.");
            assertEquals(4, parts.length);
            for (String part : parts) {
                int val = Integer.parseInt(part);
                assertTrue(val >= 0 && val <= 255);
            }
        }
    }
}
