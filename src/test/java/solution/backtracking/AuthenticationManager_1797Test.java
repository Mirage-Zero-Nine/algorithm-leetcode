package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        manager.generate("token1", 1);
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenew() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("token1", 1);
        manager.renew("token1", 3);
        assertEquals(1, manager.countUnexpiredTokens(7));
    }

    @Test
    void testMultipleTokens() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(10);
        manager.generate("token1", 1);
        manager.generate("token2", 2);
        assertEquals(2, manager.countUnexpiredTokens(10));
    }

    @Test
    void testRenewNonExistent() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.renew("nonexistent", 1);
        assertEquals(0, manager.countUnexpiredTokens(1));
    }

    @Test
    void testExpirationAtExactTime() {
        // token expires AT time t means it's expired at t (value <= currentTime)
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("t1", 1); // expires at 6
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenewExpiredToken() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("t1", 1); // expires at 6
        manager.renew("t1", 7); // already expired
        assertEquals(0, manager.countUnexpiredTokens(7));
    }

    @Test
    void testMultipleGenerateAndExpire() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(3);
        manager.generate("a", 1); // expires at 4
        manager.generate("b", 2); // expires at 5
        manager.generate("c", 3); // expires at 6
        assertEquals(3, manager.countUnexpiredTokens(3));
        assertEquals(2, manager.countUnexpiredTokens(4));
        assertEquals(1, manager.countUnexpiredTokens(5));
        assertEquals(0, manager.countUnexpiredTokens(6));
    }

    @Test
    void testRenewExtendsLifetime() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("t1", 1); // expires at 6
        manager.renew("t1", 5);   // now expires at 10
        assertEquals(1, manager.countUnexpiredTokens(9));
        assertEquals(0, manager.countUnexpiredTokens(10));
    }

    @Test
    void testGenerateOverwritesExisting() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(5);
        manager.generate("t1", 1);
        manager.generate("t1", 3); // overwrite, expires at 8
        assertEquals(1, manager.countUnexpiredTokens(7));
    }

    @Test
    void testGiantScenario() {
        AuthenticationManager_1797 manager = new AuthenticationManager_1797(100);
        for (int i = 0; i < 1000; i++) {
            manager.generate("token" + i, i);
        }
        // at time 999, tokens generated from 900..999 are alive (expire at 1000..1099)
        assertEquals(100, manager.countUnexpiredTokens(999));
    }
}
