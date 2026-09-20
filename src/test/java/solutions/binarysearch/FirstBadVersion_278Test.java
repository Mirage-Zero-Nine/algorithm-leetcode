package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests the binary search against a deterministic monotone {@code isBadVersion} API. */
public class FirstBadVersion_278Test {

    @Test
    public void testOfficialExample() {
        assertFirstBadVersion(5, 4);
    }

    @Test
    public void testSingleVersionIsBad() {
        assertFirstBadVersion(1, 1);
    }

    @Test
    public void testTwoVersionsFirstIsBad() {
        assertFirstBadVersion(2, 1);
    }

    @Test
    public void testTwoVersionsLastIsBad() {
        assertFirstBadVersion(2, 2);
    }

    @Test
    public void testOddRangeMiddleIsFirstBad() {
        assertFirstBadVersion(5, 3);
    }

    @Test
    public void testEvenRangeMiddleIsFirstBad() {
        assertFirstBadVersion(6, 4);
    }

    @Test
    public void testFirstBadAtBeginningOfLargerRange() {
        assertFirstBadVersion(100, 1);
    }

    @Test
    public void testFirstBadAtEndOfLargerRange() {
        assertFirstBadVersion(100, 100);
    }

    @Test
    public void testFirstBadImmediatelyAfterBeginning() {
        assertFirstBadVersion(100, 2);
    }

    @Test
    public void testFirstBadImmediatelyBeforeEnd() {
        assertFirstBadVersion(100, 99);
    }

    @Test
    public void testEveryThresholdInSmallRange() {
        for (int n = 1; n <= 16; n++) {
            for (int bad = 1; bad <= n; bad++) {
                assertFirstBadVersion(n, bad);
            }
        }
    }

    @Test
    public void testEveryThresholdInNonPowerOfTwoRange() {
        for (int bad = 1; bad <= 31; bad++) {
            assertFirstBadVersion(31, bad);
        }
    }

    @Test
    public void testPowerOfTwoBoundaryThresholds() {
        int n = 1024;
        assertFirstBadVersion(n, 1);
        assertFirstBadVersion(n, 2);
        assertFirstBadVersion(n, 512);
        assertFirstBadVersion(n, 513);
        assertFirstBadVersion(n, 1023);
        assertFirstBadVersion(n, 1024);
    }

    @Test
    public void testLargeRangeWithMiddleThreshold() {
        assertFirstBadVersion(1_000_000, 500_001);
    }

    @Test
    public void testLargeRangeWithFirstBadAtOne() {
        assertFirstBadVersion(1_000_000, 1);
    }

    @Test
    public void testLargeRangeWithLastBadVersion() {
        assertFirstBadVersion(1_000_000, 1_000_000);
    }

    @Test
    public void testIntegerMaximumWithFirstBadAtMaximum() {
        assertFirstBadVersion(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void testIntegerMaximumWithFirstBadAtOne() {
        assertFirstBadVersion(Integer.MAX_VALUE, 1);
    }

    @Test
    public void testIntegerMaximumWithFirstBadAtMidpoint() {
        assertFirstBadVersion(Integer.MAX_VALUE, 1_073_741_824);
    }

    @Test
    public void testIntegerMaximumWithFirstBadJustBelowMidpoint() {
        assertFirstBadVersion(Integer.MAX_VALUE, 1_073_741_823);
    }

    @Test
    public void testIntegerMaximumWithArbitraryThreshold() {
        assertFirstBadVersion(Integer.MAX_VALUE, 123_456_789);
    }

    @Test
    public void testQueriesRespectVersionDomainAndMonotonicity() {
        VersionOracle oracle = new VersionOracle(10_000, 4_321);

        assertEquals(4_321, oracle.firstBadVersion(10_000));
        assertTrue(oracle.calls.stream().allMatch(version -> version >= 1 && version <= 10_000));
        assertTrue(oracle.calls.stream().noneMatch(version -> version < 4_321
                && oracle.isBadAccordingToContract(version)));
        assertTrue(oracle.calls.stream().noneMatch(version -> version >= 4_321
                && !oracle.isBadAccordingToContract(version)));
    }

    @Test
    public void testBinarySearchUsesLogarithmicNumberOfApiCalls() {
        int n = Integer.MAX_VALUE;
        VersionOracle oracle = new VersionOracle(n, 987_654_321);

        assertEquals(987_654_321, oracle.firstBadVersion(n));
        assertTrue(oracle.calls.size() <= ceilLog2(n),
                () -> "API calls: " + oracle.calls.size());
    }

    @Test
    public void testIndependentApiInstancesDoNotShareCallState() {
        VersionOracle firstApi = new VersionOracle(50, 7);
        VersionOracle secondApi = new VersionOracle(50, 44);

        assertEquals(7, firstApi.firstBadVersion(50));
        assertEquals(44, secondApi.firstBadVersion(50));
        assertEquals(7, new VersionOracle(50, 7).firstBadVersion(50));
    }

    @Test
    public void testSameOracleCanBeReusedAfterChangingItsConfiguredBadVersion() {
        VersionOracle oracle = new VersionOracle(64, 9);

        assertEquals(9, oracle.firstBadVersion(64));
        oracle.setFirstBadVersion(56);
        assertEquals(56, oracle.firstBadVersion(64));
        oracle.setFirstBadVersion(1);
        assertEquals(1, oracle.firstBadVersion(64));
    }

    @Test
    public void testAllGoodPrefixAndBadSuffixAreObserved() {
        VersionOracle oracle = new VersionOracle(257, 129);

        assertEquals(129, oracle.firstBadVersion(257));
        assertTrue(oracle.calls.stream().anyMatch(version -> version < 129));
        assertTrue(oracle.calls.stream().anyMatch(version -> version >= 129));
        assertTrue(oracle.calls.stream().allMatch(version ->
                oracle.isBadAccordingToContract(version) == (version >= 129)));
    }

    @Test
    public void testNonPowerOfTwoNearIntegerLimit() {
        int n = Integer.MAX_VALUE - 1;
        assertFirstBadVersion(n, n - 1);
    }

    @Test
    public void testLargeThresholdSweepAcrossSearchBoundaries() {
        int n = 65_537;
        int[] thresholds = {1, 2, 3, 32_768, 32_769, 65_535, 65_536, 65_537};
        for (int bad : thresholds) {
            assertFirstBadVersion(n, bad);
        }
    }

    private void assertFirstBadVersion(int n, int expectedFirstBadVersion) {
        VersionOracle oracle = new VersionOracle(n, expectedFirstBadVersion);

        assertEquals(expectedFirstBadVersion, oracle.firstBadVersion(n));
        assertTrue(oracle.calls.stream().allMatch(version -> version >= 1 && version <= n),
                () -> "Out-of-range API query for n=" + n + ": " + oracle.calls);
        assertTrue(oracle.calls.size() <= ceilLog2(n),
                () -> "Too many API calls for n=" + n + ": " + oracle.calls.size());
    }

    private static int ceilLog2(int value) {
        if (value <= 1) {
            return 0;
        }
        return 32 - Integer.numberOfLeadingZeros(value - 1);
    }

    /** A valid LeetCode-style monotone API with observable, independently checked calls. */
    private static final class VersionOracle extends FirstBadVersion_278 {
        private final int versionCount;
        private int firstBad;
        private final List<Integer> calls = new ArrayList<>();

        private VersionOracle(int versionCount, int firstBad) {
            this.versionCount = versionCount;
            this.firstBad = firstBad;
        }

        @Override
        public boolean isBadVersion(int version) {
            if (version < 1 || version > versionCount) {
                throw new AssertionError("API called with invalid version " + version);
            }
            calls.add(version);
            return isBadAccordingToContract(version);
        }

        private boolean isBadAccordingToContract(int version) {
            return version >= firstBad;
        }

        private void setFirstBadVersion(int firstBad) {
            this.firstBad = firstBad;
            calls.clear();
        }
    }
}
