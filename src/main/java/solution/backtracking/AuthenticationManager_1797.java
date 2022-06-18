package solution.backtracking;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * There is an authentication system that works with authentication tokens.
 * For each session, the user will receive a new authentication token that will expire timeToLive seconds after the currentTime.
 * If the token is renewed, the expiry time will be extended to expire timeToLive seconds after the (potentially different) currentTime.
 * Implement the AuthenticationManager class:
 * - AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
 * - generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
 * - renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime in seconds. If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
 * - countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
 * Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens), the expiration takes place before the other actions.
 *
 * @author BorisMirage
 * Time: 2021/09/30 22:36
 * Created with IntelliJ IDEA
 */

public class AuthenticationManager_1797 {
    private final int ttl;
    private final Map<String, Integer> map;

    /**
     * Keep a linked hash map to store the token and the ttl.
     * Evict all expired items in map no matter which API is called.
     *
     * @param timeToLive time to live for each token
     */
    public AuthenticationManager_1797(int timeToLive) {
        ttl = timeToLive;
        map = new LinkedHashMap<>(16, 0.75f, true); // need to keep access order
    }

    /**
     * Generate a new ttl for token.
     *
     * @param tokenId     token id
     * @param currentTime current time
     */
    public void generate(String tokenId, int currentTime) {
        evict(currentTime);
        map.put(tokenId, currentTime + ttl);
    }

    /**
     * Renew ttl for token id.
     *
     * @param tokenId     token id
     * @param currentTime current time
     */
    public void renew(String tokenId, int currentTime) {
        evict(currentTime);
        if (map.containsKey(tokenId)) {
            map.put(tokenId, currentTime + ttl);
        }
    }

    /**
     * Return how many unexpired tokens in the map.
     *
     * @param currentTime current time
     * @return number of unexpired tokens in the map
     */
    public int countUnexpiredTokens(int currentTime) {
        evict(currentTime);
        return map.size();
    }

    /**
     * Evict expired token in map.
     *
     * @param currentTime current time
     */
    private void evict(int currentTime) {
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (!map.isEmpty() && it.next().getValue() <= currentTime) {
            it.remove();
        }
    }
}
