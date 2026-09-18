package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestoreIpAddresses_93Test {
    private final RestoreIpAddresses_93 solution = new RestoreIpAddresses_93();

    @Test
    void testBasic() {
        assertExactly("25525511135", "255.255.11.135", "255.255.111.35");
    }

    @Test
    void testAllZeros() {
        assertExactly("0000", "0.0.0.0");
    }

    @Test
    void testNoSolution() {
        assertExactly("256256256256");
    }

    @Test
    void testLongString() {
        assertExactly("101023", "1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3");
    }

    @Test
    void testShortString() {
        assertExactly("123");
    }

    @Test
    void testOneDigitInputCannotFormFourParts() {
        assertExactly("0");
    }

    @Test
    void testTwoDigitInputCannotFormFourParts() {
        assertExactly("00");
    }

    @Test
    void testTooLong() {
        assertExactly("1234567890123");
    }

    @Test
    void testAllOnes() {
        assertExactly("1111", "1.1.1.1");
    }

    @Test
    void testLeadingZeros() {
        assertExactly("010010", "0.10.0.10", "0.100.1.0");
    }

    @Test
    void testAllNines() {
        assertExactly("255255255255", "255.255.255.255");
    }

    @Test
    void testFourDigits() {
        assertExactly("2552", "2.5.5.2");
    }

    @Test
    void testTwelveDigits() {
        assertExactly("111111111111", "111.111.111.111");
    }

    @Test
    void testGiantAllValidSegments() {
        for (int i = 1000; i <= 1050; i++) {
            assertMatchesIndependentOracle(String.valueOf(i));
        }
    }

    @Test
    void testResultsAreValidIps() {
        assertMatchesIndependentOracle("25525511135");
    }

    @Test
    void testFourDigitMinimumValue() {
        assertExactly("0001", "0.0.0.1");
    }

    @Test
    void testFourDigitMaximumBoundary() {
        assertExactly("9999", "9.9.9.9");
    }

    @Test
    void testRepeatedZeroAndOneSegments() {
        assertMatchesIndependentOracle("100100100100");
    }

    @Test
    void testUpperOctetBoundary() {
        assertMatchesIndependentOracle("2552552550");
    }

    @Test
    void testTypicalAddressSpectrum() {
        assertMatchesIndependentOracle("19216811");
    }

    @Test
    void testMultipleThreeDigitSplits() {
        assertMatchesIndependentOracle("123123123123");
    }

    @Test
    void testZerosBetweenNonzeroDigits() {
        assertMatchesIndependentOracle("10203040");
    }

    @Test
    void testLeadingZeroCandidatesAreRejected() {
        assertMatchesIndependentOracle("001001");
    }

    @Test
    void testNoValidSplitAtMaximumUsefulLength() {
        assertExactly("999999999999");
    }

    @Test
    void testMaximumInputLengthHasNoAddress() {
        assertExactly("12345678901234567890");
    }

    @Test
    void testExhaustiveBinaryDigitsAtShortLengths() {
        for (int length = 4; length <= 7; length++) {
            int count = 1 << length;
            for (int value = 0; value < count; value++) {
                StringBuilder input = new StringBuilder(length);
                for (int bit = length - 1; bit >= 0; bit--) {
                    input.append((value & (1 << bit)) == 0 ? '0' : '1');
                }
                assertMatchesIndependentOracle(input.toString());
            }
        }
    }

    @Test
    void testDeterministicMixedDigits() {
        String[] inputs = {"172162541", "11011110", "111222333", "200200200200", "250250250250"};
        for (String input : inputs) {
            assertMatchesIndependentOracle(input);
        }
    }

    private void assertExactly(String input, String... expected) {
        List<String> actual = solution.restoreIpAddresses(input);
        Set<String> expectedSet = Set.of(expected);
        assertEquals(expectedSet, new HashSet<>(actual), input);
        assertEquals(expectedSet.size(), actual.size(), "duplicate output for " + input);
    }

    private void assertMatchesIndependentOracle(String input) {
        List<String> actual = solution.restoreIpAddresses(input);
        Set<String> expected = independentTripleCutOracle(input);
        assertEquals(expected, new HashSet<>(actual), input);
        assertEquals(expected.size(), actual.size(), "duplicate output for " + input);
        for (String address : actual) {
            String[] parts = address.split("\\.", -1);
            assertEquals(4, parts.length);
            for (String part : parts) {
                assertFalse(part.length() > 1 && part.charAt(0) == '0');
                assertTrue(Integer.parseInt(part) <= 255);
            }
        }
    }

    /** Enumerates every possible placement of the three dots independently of the production loops. */
    private Set<String> independentTripleCutOracle(String input) {
        Set<String> expected = new HashSet<>();
        for (int first = 1; first < input.length(); first++) {
            for (int second = first + 1; second < input.length(); second++) {
                for (int third = second + 1; third < input.length(); third++) {
                    String[] parts = {
                            input.substring(0, first), input.substring(first, second),
                            input.substring(second, third), input.substring(third)
                    };
                    if (validPart(parts[0]) && validPart(parts[1])
                            && validPart(parts[2]) && validPart(parts[3])) {
                        expected.add(String.join(".", parts));
                    }
                }
            }
        }
        return expected;
    }

    private boolean validPart(String part) {
        if (part.isEmpty() || (part.length() > 1 && part.charAt(0) == '0') || part.length() > 3) {
            return false;
        }
        int value = 0;
        for (int index = 0; index < part.length(); index++) {
            value = value * 10 + part.charAt(index) - '0';
        }
        return value <= 255;
    }
}
