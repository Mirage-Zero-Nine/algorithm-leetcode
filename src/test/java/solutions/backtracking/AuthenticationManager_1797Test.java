package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Timeout(15)
@ExtendWith(AuthenticationManager_1797Test.ProcessTimeoutExtension.class)
class AuthenticationManager_1797Test {

    @Test
    void testBasicOperations() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.renew("aaa", 1);
        manager.generate("aaa", 2);
        assertEquals(1, manager.countUnexpiredTokens(6));
    }

    @Test
    void testExpiration() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("aa", 1);
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenew() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("aa", 1);
        manager.renew("aa", 3);
        assertEquals(1, manager.countUnexpiredTokens(7));
    }

    @Test
    void testMultipleTokens() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(10);
        manager.generate("aa", 1);
        manager.generate("bb", 2);
        assertEquals(2, manager.countUnexpiredTokens(10));
    }

    @Test
    void testRenewNonExistent() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.renew("miss", 1);
        assertEquals(0, manager.countUnexpiredTokens(2));
    }

    @Test
    void testExpirationAtExactTime() {
        // token expires AT time t means it's expired at t (value <= currentTime)
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("aa", 1); // expires at 6
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenewExpiredToken() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("aa", 1); // expires at 6
        manager.renew("aa", 7); // already expired
        assertEquals(0, manager.countUnexpiredTokens(8));
    }

    @Test
    void testMultipleGenerateAndExpire() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.generate("a", 1); // expires at 4
        manager.generate("b", 2); // expires at 5
        manager.generate("c", 3); // expires at 6
        assertEquals(2, manager.countUnexpiredTokens(4));
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenewExtendsLifetime() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("aa", 1); // expires at 6
        manager.renew("aa", 5);   // now expires at 10
        assertEquals(1, manager.countUnexpiredTokens(9));
        assertEquals(0, manager.countUnexpiredTokens(10));
    }

    @Test
    void testGiantScenario() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(100);
        for (int i = 1; i <= 1000; i++) {
            manager.generate(tokenFor(i), i);
        }
        // at time 1001, tokens generated from 902..1000 are alive (expire at 1002..1100)
        assertEquals(99, manager.countUnexpiredTokens(1001));
    }

    @Test
    void testMaximumTwoThousandCalls() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(100);
        for (int i = 1; i <= 1_000; i++) {
            manager.generate(tokenFor(i), i);
        }
        assertEquals(99, manager.countUnexpiredTokens(1_001));
        // Include construction in the 2,000-call budget.
        for (int time = 1_002; time <= 1_999; time++) {
            int count = manager.countUnexpiredTokens(time);
            assertEquals(Math.max(0, 1_100 - time), count);
        }
    }

    @Test
    void testDeterministicReferenceOracleForInterleavedOperations() {
        int ttl = 5;
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(ttl);
        Map<String, Integer> expectedExpiry = new HashMap<>();

        String[] actions = {"generate", "renew", "generate", "renew", "renew", "renew", "generate", "renew", "generate", "renew", "renew"};
        String[] tokens = {"aa", "aa", "bb", "bb", "aa", "bb", "cc", "cc", "dd", "dd", "miss"};
        for (int operation = 0; operation < actions.length; operation++) {
            int actionTime = operation * 2 + 1;
            removeExpired(expectedExpiry, actionTime);
            if (actions[operation].equals("generate")) {
                manager.generate(tokens[operation], actionTime);
                expectedExpiry.put(tokens[operation], actionTime + ttl);
            } else {
                String token = tokens[operation];
                manager.renew(token, actionTime);
                if (expectedExpiry.containsKey(token)) {
                    expectedExpiry.put(token, actionTime + ttl);
                }
            }

            int countTime = actionTime + 1;
            removeExpired(expectedExpiry, countTime);
            assertEquals(expectedExpiry.size(), manager.countUnexpiredTokens(countTime));
        }
    }

    @Test
    void testLeetCodeExample() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.renew("aaa", 1);
        manager.generate("aaa", 2);
        assertEquals(1, manager.countUnexpiredTokens(6));
        manager.generate("bbb", 7);
        manager.renew("aaa", 8);
        manager.renew("bbb", 10);
        assertEquals(0, manager.countUnexpiredTokens(15));
    }

    @Test
    void testInitiallyEmptyAtSeveralTimes() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(9);
        assertEquals(0, manager.countUnexpiredTokens(1));
        assertEquals(0, manager.countUnexpiredTokens(50));
        manager.renew("miss", 51);
        assertEquals(0, manager.countUnexpiredTokens(52));
    }

    @Test
    void testTtlOneExpiresAtNextSecond() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(1);
        manager.generate("a", 1);
        assertEquals(0, manager.countUnexpiredTokens(2));
    }

    @Test
    void testRenewAtLastUnexpiredSecond() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(4);
        manager.generate("a", 1); // expiry 5
        manager.renew("a", 4);    // expiry 8
        assertEquals(1, manager.countUnexpiredTokens(7));
        assertEquals(0, manager.countUnexpiredTokens(8));
    }

    @Test
    void testRenewAtExactExpiryIsIgnored() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(4);
        manager.generate("a", 1); // expiry 5
        manager.renew("a", 5);
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenewDoesNotCreateToken() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.renew("a", 1);
        manager.renew("b", 2);
        assertEquals(0, manager.countUnexpiredTokens(3));
    }

    @Test
    void testRenewOneOfSeveralTokens() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1); // 6
        manager.generate("b", 2); // 7
        manager.generate("c", 3); // 8
        manager.renew("b", 4);     // 9
        assertEquals(3, manager.countUnexpiredTokens(5));
        assertEquals(2, manager.countUnexpiredTokens(7));
        assertEquals(1, manager.countUnexpiredTokens(8));
        assertEquals(0, manager.countUnexpiredTokens(9));
    }

    @Test
    void testCountRemovesExpiredTokensAfterGeneration() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(2);
        manager.generate("old", 1); // 3
        manager.generate("new", 2); // 4
        manager.generate("lat", 4); // old and new are expired; the count cleans them up
        assertEquals(1, manager.countUnexpiredTokens(5));
    }

    @Test
    void testCountRemovesExpiredTokensAfterRenewal() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.generate("old", 1); // 4
        manager.generate("live", 2); // 5
        manager.renew("live", 4);    // live -> 7; the next count cleans up old
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(7));
    }

    @Test
    void testTokensWithDifferentExpiryTimesExpireIndependently() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(10);
        manager.generate("a", 1); // 11
        manager.generate("b", 5); // 15
        manager.generate("c", 9); // 19
        assertEquals(3, manager.countUnexpiredTokens(10));
        assertEquals(2, manager.countUnexpiredTokens(11));
        assertEquals(1, manager.countUnexpiredTokens(15));
        assertEquals(0, manager.countUnexpiredTokens(19));
    }

    @Test
    void testRenewedTokenSurvivesOtherTokensExpiry() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(6);
        manager.generate("a", 1); // 7
        manager.generate("b", 2); // 8
        manager.renew("a", 3);    // 9
        assertEquals(2, manager.countUnexpiredTokens(4));
        assertEquals(1, manager.countUnexpiredTokens(8));
        assertEquals(0, manager.countUnexpiredTokens(9));
    }

    @Test
    void testRepeatedCountsAreIdempotent() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1);
        assertEquals(1, manager.countUnexpiredTokens(2));
        assertEquals(1, manager.countUnexpiredTokens(3));
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testLargeTimeValuesWithinConstraints() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(99_999_999);
        manager.generate("a", 1); // 100,000,000
        assertEquals(1, manager.countUnexpiredTokens(99_999_999));
        assertEquals(0, manager.countUnexpiredTokens(100_000_000));
    }

    @Test
    void testMaximumTimeToLiveAndCurrentTime() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(100_000_000);
        manager.generate("a", 1);
        assertEquals(1, manager.countUnexpiredTokens(100_000_000));
    }

    @Test
    void testManyRenewalsOnlyKeepOneToken() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(10);
        manager.generate("a", 1);
        manager.renew("a", 2);
        manager.renew("a", 3);
        manager.renew("a", 4);
        assertEquals(1, manager.countUnexpiredTokens(13));
        assertEquals(0, manager.countUnexpiredTokens(14));
    }

    @Test
    void testRenewMissingIdDoesNotDisturbExistingTokens() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1);
        manager.generate("b", 2);
        manager.renew("miss", 3);
        assertEquals(2, manager.countUnexpiredTokens(4));
        assertEquals(0, manager.countUnexpiredTokens(7));
    }

    @Test
    void testLargeNumberOfTokensExpiresInOrder() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        for (int i = 1; i <= 50; i++) {
            manager.generate(tokenFor(i), i);
        }
        assertEquals(2, manager.countUnexpiredTokens(51));
        assertEquals(1, manager.countUnexpiredTokens(52));
        assertEquals(0, manager.countUnexpiredTokens(53));
    }

    @Test
    void testTokenIdLengthBoundariesAndMinimumCurrentTime() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(2);
        manager.generate("a", 1);
        manager.generate("abcde", 2);

        assertEquals(1, manager.countUnexpiredTokens(3));
        assertEquals(0, manager.countUnexpiredTokens(4));
    }

    @Test
    void testMaximumCurrentTimeWithSmallTtl() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(2);
        manager.generate("abcde", 99_999_998);

        assertEquals(1, manager.countUnexpiredTokens(99_999_999));
        assertEquals(0, manager.countUnexpiredTokens(100_000_000));
    }

    @Test
    void testLongIdlePeriodExpiresAllBeforeNewGeneration() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("old", 1);
        assertEquals(0, manager.countUnexpiredTokens(1_000));

        manager.generate("new", 1_001);
        assertEquals(1, manager.countUnexpiredTokens(1_005));
        assertEquals(0, manager.countUnexpiredTokens(1_006));
    }

    @Test
    void testRenewedEntriesRemainCorrectlyOrderedForEviction() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1); // expires at 6
        manager.generate("b", 2); // expires at 7
        manager.generate("c", 3); // expires at 8
        manager.renew("a", 4);     // new snapshot expires at 9; the old one remains
        manager.renew("b", 5);     // new snapshot expires at 10

        assertEquals(3, manager.countUnexpiredTokens(7));
        assertEquals(2, manager.countUnexpiredTokens(8));
        assertEquals(1, manager.countUnexpiredTokens(9));
        assertEquals(0, manager.countUnexpiredTokens(10));
    }

    @Test
    void testExpiredRenewalDoesNotAffectOtherLiveTokens() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(4);
        manager.generate("old", 1);     // expires at 5
        manager.generate("live", 2);    // expires at 6
        assertEquals(2, manager.countUnexpiredTokens(3));
        manager.renew("old", 5);        // expires before this action; ignored

        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testUnknownRenewalDoesNotCreateOrExtendAnythingAfterCleanup() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.generate("live", 1); // expires at 4
        manager.renew("miss", 2);
        assertEquals(1, manager.countUnexpiredTokens(3));

        manager.renew("miss", 4);
        assertEquals(0, manager.countUnexpiredTokens(5));
    }

    @Test
    void testIndependentManagersDoNotShareTokenState() {
        AuthenticationManager_1797 first = new AuthenticationManager_1797(5);
        AuthenticationManager_1797 second = new AuthenticationManager_1797(5);
        first.generate("same", 1);

        assertEquals(1, first.countUnexpiredTokens(2));
        assertEquals(0, second.countUnexpiredTokens(2));
    }

    @Test
    void testReportedExpiredRenewalRegression() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(20);
        assertEquals(0, manager.countUnexpiredTokens(1));
        manager.generate("coif", 3);
        manager.renew("usmpu", 4);
        manager.renew("jhn", 5);
        assertEquals(1, manager.countUnexpiredTokens(10));
        assertEquals(1, manager.countUnexpiredTokens(12));
        manager.generate("oh", 16);
        assertEquals(2, manager.countUnexpiredTokens(21));
        manager.renew("coif", 23);
        manager.renew("cbh", 24);
        manager.generate("icg", 28);

        // coif's stale snapshot must neither crash cleanup nor delete oh or icg.
        assertEquals(2, manager.countUnexpiredTokens(29));
        assertEquals(1, manager.countUnexpiredTokens(36));
        assertEquals(0, manager.countUnexpiredTokens(48));
    }

    @Test
    void testRenewedHeapHeadDoesNotBlockAnotherTokensExpiration() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1);
        manager.generate("b", 2);
        manager.renew("a", 3);

        assertEquals(1, manager.countUnexpiredTokens(7));
        assertEquals(0, manager.countUnexpiredTokens(8));
    }

    @Test
    void testMaximumRenewalTimeAndTtlStayWithinIntegerRange() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(100_000_000);
        manager.generate("a", 99_999_997);
        manager.renew("a", 99_999_998);
        manager.generate("b", 99_999_999);

        assertEquals(2, manager.countUnexpiredTokens(100_000_000));
    }

    @Test
    void testOutOfOrderGenerationSnapshotsBeforeLaterCounts() {
        // Supported extension: generation order varies, but no query asks for historical state.
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 10);
        manager.generate("b", 2);
        manager.generate("c", 7);

        assertEquals(2, manager.countUnexpiredTokens(11));
        assertEquals(1, manager.countUnexpiredTokens(12));
        assertEquals(0, manager.countUnexpiredTokens(15));
    }

    @Test
    void testEqualTimestampSnapshotsExpireWithoutDependingOnHeapTieOrder() {
        // Supported extension: equal times, including a renewal with the same captured timestamp.
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1);
        manager.generate("b", 1);
        manager.renew("a", 1);

        assertEquals(2, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRegeneratedIdKeepsOnlyItsLatestLifetime() {
        // Supported extension: generate replaces an existing ID, beyond the problem's unique IDs.
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("a", 1);
        manager.generate("b", 2);
        manager.generate("a", 3);

        assertEquals(2, manager.countUnexpiredTokens(6));
        assertEquals(1, manager.countUnexpiredTokens(7));
        assertEquals(0, manager.countUnexpiredTokens(8));
    }

    @Test
    void testRenewalHeavyMaximumCallBudgetDrainsAllStaleSnapshots() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(2_000);
        manager.generate("a", 1);
        for (int time = 2; time <= 1_997; time++) {
            manager.renew("a", time);
        }
        // 1 construction + 1 generation + 1,996 renewals + 2 counts = 2,000 calls.
        assertEquals(1, manager.countUnexpiredTokens(2_001));
        assertEquals(0, manager.countUnexpiredTokens(3_997));
    }

    @Test
    void testExhaustiveShortOperationSequencesAgainstReference() {
        // All prefixes of length 0..4: five actions, gaps 1..2, and TTL 1..3.
        // Longer histories, larger time gaps, and full value bounds have separate cases.
        assertEquals(33_333, enumerateOperationPrefixes(new ArrayList<>(), 4, 0, 0));
    }

    @Test
    void testSeededInterleavedHistoriesAgainstReference() {
        Random random = new Random(1_797);
        int[] lifetimes = {1, 2, 5, 100, 100_000_000};
        for (int trial = 0; trial < 100; trial++) {
            int ttl = lifetimes[trial % lifetimes.length];
            List<Operation> operations = new ArrayList<>();
            int time = random.nextInt(98_000_000) + 1;
            int generated = 0;
            for (int index = 0; index < 200; index++) {
                time += random.nextInt(1_000) + 1;
                switch (random.nextInt(3)) {
                    case 0 -> operations.add(new Operation(Kind.GENERATE, tokenFor(generated++), time));
                    case 1 ->
                            operations.add(new Operation(Kind.RENEW, random.nextBoolean() && generated > 0 ? tokenFor(random.nextInt(generated)) : "miss", time));
                    default -> operations.add(new Operation(Kind.COUNT, null, time));
                }
            }
            assertMatchesReference(ttl, operations, "seed=1797 trial=" + trial);
        }
    }

    private static int enumerateOperationPrefixes(List<Operation> operations, int remaining, int previousTime, int generated) {
        for (int ttl = 1; ttl <= 3; ttl++) {
            assertMatchesReference(ttl, operations, "exhaustive");
        }
        int scenarios = 3;
        if (remaining == 0) {
            return scenarios;
        }
        for (int gap = 1; gap <= 2; gap++) {
            int time = previousTime + gap;
            Operation[] choices = {new Operation(Kind.GENERATE, tokenFor(generated), time), new Operation(Kind.RENEW, "a", time), new Operation(Kind.RENEW, "b", time), new Operation(Kind.RENEW, "miss", time), new Operation(Kind.COUNT, null, time)};
            for (Operation choice : choices) {
                operations.add(choice);
                scenarios += enumerateOperationPrefixes(operations, remaining - 1, time, generated + (choice.kind() == Kind.GENERATE ? 1 : 0));
                operations.removeLast();
            }
        }
        return scenarios;
    }

    private static void assertMatchesReference(int ttl, List<Operation> operations, String context) {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(ttl);
        Map<String, Integer> expectedExpiry = new HashMap<>();
        String scenario = context + " ttl=" + ttl + " operations=" + operations;
        // This oracle stores deadlines and scans all entries; it has no heap or stale snapshots.
        for (Operation operation : operations) {
            removeExpired(expectedExpiry, operation.time());
            switch (operation.kind()) {
                case GENERATE -> {
                    manager.generate(operation.token(), operation.time());
                    expectedExpiry.put(operation.token(), operation.time() + ttl);
                }
                case RENEW -> {
                    manager.renew(operation.token(), operation.time());
                    if (expectedExpiry.containsKey(operation.token())) {
                        expectedExpiry.put(operation.token(), operation.time() + ttl);
                    }
                }
                case COUNT ->
                        assertEquals(expectedExpiry.size(), manager.countUnexpiredTokens(operation.time()), scenario + " at " + operation.time());
            }
        }
        int finalTime = operations.isEmpty() ? 1 : operations.getLast().time() + 1;
        removeExpired(expectedExpiry, finalTime);
        assertEquals(expectedExpiry.size(), manager.countUnexpiredTokens(finalTime), scenario + " final count at " + finalTime);
    }

    private enum Kind {GENERATE, RENEW, COUNT}

    private record Operation(Kind kind, String token, int time) {
    }

    private static String tokenFor(int number) {
        StringBuilder token = new StringBuilder();
        do {
            token.append((char) ('a' + number % 26));
            number = number / 26 - 1;
        } while (number >= 0);
        return token.toString();
    }

    private static void removeExpired(Map<String, Integer> expiry, int currentTime) {
        Iterator<Map.Entry<String, Integer>> iterator = expiry.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() <= currentTime) {
                iterator.remove();
            }
        }
    }

    // Heap cleanup does not honor interruption, so a child JVM bounds every test's execution.
    static final class ProcessTimeoutExtension implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> context, ExtensionContext extensionContext) throws Throwable {
            Path log = Files.createTempFile("authentication-manager-1797-test-", ".log");
            Process process = null;
            try {
                process = new ProcessBuilder(System.getProperty("java.home") + "/bin/java", "-cp", System.getProperty("surefire.test.class.path", System.getProperty("java.class.path")), AuthenticationManager_1797Test.class.getName(), context.getExecutable().getName()).redirectErrorStream(true).redirectOutput(log.toFile()).start();
                if (!process.waitFor(8, TimeUnit.SECONDS)) {
                    fail("test exceeded enforceable 8-second process limit: " + context.getExecutable().getName());
                }
                if (process.exitValue() != 0) {
                    fail("child test failed: " + context.getExecutable().getName() + System.lineSeparator() + Files.readString(log));
                }
                invocation.skip();
            } finally {
                try {
                    if (process != null && process.isAlive()) {
                        process.destroyForcibly();
                        if (!process.waitFor(2, TimeUnit.SECONDS)) {
                            fail("child test could not be terminated: " + context.getExecutable().getName());
                        }
                    }
                } finally {
                    Files.deleteIfExists(log);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("missing test method");
        }
        try {
            AuthenticationManager_1797Test.class.getDeclaredMethod(args[0]).invoke(new AuthenticationManager_1797Test());
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception.getCause());
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
