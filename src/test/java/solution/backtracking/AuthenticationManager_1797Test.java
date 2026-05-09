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
}
