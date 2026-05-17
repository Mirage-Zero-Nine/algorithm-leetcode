package solution.unionfind;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountsMerge_721Test {

    private final AccountsMerge_721 solution = new AccountsMerge_721();

    // Helper to build mutable list of lists (needed since solution may modify input)
    private List<List<String>> accounts(List<String>... lists) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> l : lists) {
            result.add(new ArrayList<>(l));
        }
        return result;
    }

    // Normalize results for order-independent comparison
    private Set<List<String>> normalize(List<List<String>> result) {
        Set<List<String>> set = new HashSet<>();
        for (List<String> account : result) {
            List<String> sorted = new ArrayList<>();
            sorted.add(account.get(0));
            List<String> emails = new ArrayList<>(account.subList(1, account.size()));
            Collections.sort(emails);
            sorted.addAll(emails);
            set.add(sorted);
        }
        return set;
    }

    // --- Existing tests (preserved) ---

    @Test
    void testAccountsMerge_multipleAccountsWithSharedEmail() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testAccountsMerge_noSharedEmail() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("Paul", "paul@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("Paul", "paul@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testAccountsMerge_multipleEmailsShared() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "john00@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testAccountsMerge_duplicateEmails() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "johnsmith@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testAccountsMerge_singleAccount() {
        List<List<String>> input = accounts(List.of("John", "johnsmith@mail.com"));
        Set<List<String>> expected = Set.of(List.of("John", "johnsmith@mail.com"));
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testAccountsMerge_emptyInput() {
        List<List<String>> result = solution.accountsMerge(new ArrayList<>());
        assertTrue(result.isEmpty());
    }

    @Test
    void testAccountsMerge_nullInput() {
        List<List<String>> result = solution.accountsMerge(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAccountsMerge_chainMerge() {
        List<List<String>> input = accounts(
                List.of("John", "a@mail.com", "b@mail.com"),
                List.of("John", "b@mail.com", "c@mail.com"),
                List.of("John", "c@mail.com", "d@mail.com")
        );
        List<List<String>> result = solution.accountsMerge(input);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).size()); // name + 4 emails
    }

    @Test
    void testAccountsMerge_sameNameDifferentPeople() {
        List<List<String>> input = accounts(
                List.of("John", "john1@mail.com"),
                List.of("John", "john2@mail.com")
        );
        assertEquals(2, solution.accountsMerge(input).size());
    }

    @Test
    void testAccountsMerge_giantCase() {
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add(new ArrayList<>(List.of("User", "common@mail.com", "unique" + i + "@mail.com")));
        }
        List<List<String>> result = solution.accountsMerge(input);
        assertEquals(1, result.size());
        assertEquals(102, result.get(0).size()); // name + common + 100 unique
    }

    // --- NEW tests ---

    @Test
    void testSingleAccountNoEmails() {
        List<List<String>> input = accounts(List.of("Alice"));
        List<List<String>> result = solution.accountsMerge(input);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).get(0));
        assertEquals(1, result.get(0).size());
    }

    @Test
    void testSingleAccountOneEmail() {
        List<List<String>> input = accounts(List.of("Bob", "bob@mail.com"));
        Set<List<String>> expected = Set.of(List.of("Bob", "bob@mail.com"));
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testTwoAccountsNoSharedEmails() {
        List<List<String>> input = accounts(
                List.of("Alice", "alice@mail.com"),
                List.of("Bob", "bob@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Alice", "alice@mail.com"),
                List.of("Bob", "bob@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testTwoAccountsShareOneEmail() {
        List<List<String>> input = accounts(
                List.of("Alice", "shared@mail.com", "a1@mail.com"),
                List.of("Alice", "shared@mail.com", "a2@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Alice", "a1@mail.com", "a2@mail.com", "shared@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testThreeAccountsChainMerge() {
        // A-B share email, B-C share email -> all merge into one
        List<List<String>> input = accounts(
                List.of("X", "a@x.com", "ab@x.com"),
                List.of("X", "ab@x.com", "bc@x.com"),
                List.of("X", "bc@x.com", "c@x.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("X", "a@x.com", "ab@x.com", "bc@x.com", "c@x.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testSameNameNoSharedEmailsNotMerged() {
        // LeetCode quirk: same name does NOT mean same person
        List<List<String>> input = accounts(
                List.of("John", "john1@mail.com"),
                List.of("John", "john2@mail.com"),
                List.of("John", "john3@mail.com")
        );
        assertEquals(3, solution.accountsMerge(input).size());
    }

    @Test
    void testDuplicateEmailsInternally() {
        // Account has the same email listed multiple times
        List<List<String>> input = accounts(
                List.of("Eve", "eve@mail.com", "eve@mail.com", "extra@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Eve", "eve@mail.com", "extra@mail.com")
        );
        assertEquals(expected, normalize(solution.accountsMerge(input)));
    }

    @Test
    void testOutputEmailsSortedAndNameFirst() {
        List<List<String>> input = accounts(
                List.of("Zara", "z@mail.com", "a@mail.com", "m@mail.com")
        );
        List<List<String>> result = solution.accountsMerge(input);
        assertEquals(1, result.size());
        assertEquals("Zara", result.get(0).get(0));
        List<String> emails = result.get(0).subList(1, result.get(0).size());
        List<String> sorted = new ArrayList<>(emails);
        Collections.sort(sorted);
        assertEquals(sorted, emails, "Emails must be in sorted order");
    }

    @Test
    void testLargeInput50AccountsRandomShared() {
        Random rng = new Random(42L);
        int n = 50;
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> account = new ArrayList<>();
            account.add("User" + i);
            account.add("unique" + i + "@test.com");
            account.add("shared" + rng.nextInt(20) + "@test.com");
            account.add("shared" + rng.nextInt(20) + "@test.com");
            input.add(account);
        }

        List<List<String>> result = solution.accountsMerge(input);

        // Reference union-find to compute expected group count
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        java.util.Map<String, Integer> emailOwner = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < input.get(i).size(); j++) {
                String email = input.get(i).get(j);
                if (emailOwner.containsKey(email)) {
                    refUnion(parent, emailOwner.get(email), i);
                } else {
                    emailOwner.put(email, i);
                }
            }
        }
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) roots.add(refFind(parent, i));

        assertEquals(roots.size(), result.size());

        // Verify all emails present and sorted
        Set<String> allResultEmails = new HashSet<>();
        for (List<String> account : result) {
            assertTrue(account.size() >= 1);
            List<String> emails = account.subList(1, account.size());
            List<String> sorted = new ArrayList<>(emails);
            Collections.sort(sorted);
            assertEquals(sorted, emails, "Emails must be sorted");
            allResultEmails.addAll(emails);
        }
        assertEquals(emailOwner.keySet(), allResultEmails);
    }

    private int refFind(int[] parent, int x) {
        if (parent[x] != x) parent[x] = refFind(parent, parent[x]);
        return parent[x];
    }

    private void refUnion(int[] parent, int a, int b) {
        parent[refFind(parent, a)] = refFind(parent, b);
    }
}
