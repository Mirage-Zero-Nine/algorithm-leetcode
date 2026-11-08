package solutions.backtracking;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
    private final Queue<Token> pq;

    /**
     * Creates a manager whose map stores each token's latest generation or renewal time.
     * A min-heap stores immutable snapshots of those times; the fixed lifetime makes this
     * timestamp order also expiration order. Renewals leave old snapshots in the heap,
     * so cleanup removes a map entry only when it still matches the expired snapshot.
     * Counting performs cleanup; generation and renewal do not scan unrelated tokens.
     *
     * <p>The problem supplies increasing operation times and unique generated token IDs.
     * The heap also accepts generation timestamps in any order, but cleanup permanently
     * discards expired state: this manager does not answer historical queries or reorder
     * late renewal events. Equal operation times are supported with expiration first.</p>
     *
     * <p>Time complexity: O(1) to create the empty collections. Subsequent heap updates
     * take amortized O(log(h + 1)) time for h queued snapshots, with expected map access.</p>
     * <p>Auxiliary space complexity: O(1) initially. Retained storage grows to O(n + h)
     * for n map entries and h heap snapshots, including stale renewal snapshots that
     * remain until a count reaches their expiration times.</p>
     *
     * @param timeToLive fixed token lifetime in seconds, from 1 to 100,000,000
     */
    public AuthenticationManager_1797(int timeToLive) {
        ttl = timeToLive;
        map = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.currentTime));
    }

    /**
     * Records the token's generation time and queues its expiration snapshot. Updating
     * the map does not change existing heap priorities because snapshots are immutable.
     * This also replaces an existing ID, although the problem generates unique IDs.
     *
     * <p>Time complexity: O(log(h + 1)) amortized, with expected amortized map updates,
     * where h is the number of queued snapshots after insertion. Collection resizing
     * can make an individual call linear.</p>
     * <p>Auxiliary space complexity: O(n + h) temporarily in the worst case when backing
     * arrays resize, otherwise O(1) working space; n is the number of map entries.
     * The call retains one snapshot and at most one new map entry.</p>
     *
     * @param tokenId     token to create
     * @param currentTime generation time in seconds, from 1 to 100,000,000
     */
    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime);
        pq.add(new Token(tokenId, currentTime));
    }

    /**
     * Gives an existing, unexpired token a fresh lifetime starting at currentTime.
     * Missing tokens are ignored; expired tokens are removed rather than revived.
     * A successful renewal adds a new snapshot while leaving the old one for cleanup.
     *
     * <p>Time complexity: O(log(h + 1)) amortized for a successful renewal, with expected
     * amortized map access, where h is the resulting heap size. An ignored renewal
     * takes expected O(1) time; heap-array growth can make one successful call O(h).</p>
     * <p>Auxiliary space complexity: O(h) temporarily if the heap backing array grows,
     * otherwise O(1) working space. A successful renewal retains one extra snapshot.</p>
     *
     * @param tokenId     token to renew
     * @param currentTime renewal time in seconds, from 1 to 100,000,000
     */
    public void renew(String tokenId, int currentTime) {
        if (!map.containsKey(tokenId)) {
            return;
        }
        int tokenCreated = map.remove(tokenId);
        if (tokenCreated + ttl > currentTime) {
            map.put(tokenId, currentTime);
            pq.add(new Token(tokenId, currentTime));
        }
    }

    /**
     * Removes expired snapshots and returns the remaining map size. After cleanup,
     * every retained mapping has a latest snapshot that expires after currentTime,
     * so each mapping contributes exactly one live token, regardless of stale snapshots.
     * This mutates the manager and cannot be used to query a time before earlier cleanup.
     *
     * <p>Time complexity: O(1 + k log(h + 1)) expected, where h is the initial number
     * of queued snapshots and k is the number removed. Reading the final size is O(1).</p>
     * <p>Auxiliary space complexity: O(1) working space; cleanup releases retained
     * snapshots and expired map entries without building a list of live tokens.</p>
     *
     * @param currentTime counting time in seconds, from 1 to 100,000,000
     * @return number of tokens whose expiration time is strictly after currentTime
     */
    public int countUnexpiredTokens(int currentTime) {
        removeExpired(currentTime);
        return map.size();
    }

    /**
     * Discards the expired heap prefix. Since all tokens share the same lifetime,
     * the first unexpired snapshot proves that every later snapshot is unexpired too.
     * Matching both ID and timestamp protects tokens renewed to a later expiration.
     *
     * <p>Time complexity: O(1 + k log(h + 1)) expected for k removals from an initial
     * heap of h snapshots, including expected constant-time conditional map removals.
     * Each snapshot is polled at most once across all cleanup calls.</p>
     * <p>Auxiliary space complexity: O(1), using only one removed snapshot at a time.</p>
     *
     * @param current time at which expiration is evaluated, including the exact deadline
     */
    private void removeExpired(int current) {
        while (!pq.isEmpty() && (pq.peek().currentTime + ttl <= current)) {
            Token expired = pq.poll();
            // Remove the mapping only if this snapshot still describes its current lifetime.
            map.remove(expired.tokenId, expired.currentTime);
        }
    }

    /**
     * Immutable heap entry; its priority stays stable when the token's map value changes.
     */
    private record Token(String tokenId, int currentTime) {
    }
}
