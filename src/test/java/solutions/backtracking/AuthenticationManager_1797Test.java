package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        for (int time = 1_002; time <= 2_000; time++) {
            int count = manager.countUnexpiredTokens(time);
            assertEquals(Math.max(0, 1_100 - time), count);
        }
    }

    @Test
    void testDeterministicReferenceOracleForInterleavedOperations() {
        int ttl = 5;
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(ttl);
        Map<String, Integer> expectedExpiry = new HashMap<>();

        String[] actions = {"generate", "renew", "generate", "renew", "renew",
                "renew", "generate", "renew", "generate", "renew", "renew"};
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
    void testExpiredTokensAreRemovedBeforeGenerate() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(2);
        manager.generate("old", 1); // 3
        manager.generate("new", 2); // 4
        manager.generate("lat", 4); // old and new expire before this action
        assertEquals(1, manager.countUnexpiredTokens(5));
    }

    @Test
    void testExpiredTokensAreRemovedBeforeRenew() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.generate("old", 1); // 4
        manager.generate("live", 2); // 5
        manager.renew("live", 4);    // old removed; live -> 7
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
}
