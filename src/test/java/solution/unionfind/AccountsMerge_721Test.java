package solution.unionfind;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountsMerge_721Test {

    // Helper method to check the equality of two lists of lists
    private boolean listsAreEqual(List<List<String>> expected, List<List<String>> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            List<String> expectedList = expected.get(i);
            List<String> actualList = actual.get(i);
            if (!expectedList.equals(actualList)) {
                return false;
            }
        }
        return true;
    }

    @Test
    void testAccountsMerge_multipleAccountsWithSharedEmail() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Accounts with shared email addresses
        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Lists.newArrayList("John", "johnsmith@mail.com", "john00@mail.com"),
                Lists.newArrayList("Mary", "mary@mail.com")
        );

        // Expected result after merging the accounts
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                Lists.newArrayList("Mary", "mary@mail.com")
        );

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_noSharedEmail() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Accounts with no shared emails
        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com"),
                Lists.newArrayList("Mary", "mary@mail.com"),
                Lists.newArrayList("Paul", "paul@mail.com")
        );

        // Expected result (no merging)
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com"),
                Lists.newArrayList("Mary", "mary@mail.com"),
                Lists.newArrayList("Paul", "paul@mail.com")
        );

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_multipleEmailsShared() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Accounts with multiple shared emails
        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Lists.newArrayList("John", "john00@mail.com", "johnsmith@mail.com"),
                Lists.newArrayList("John", "johnnybravo@mail.com")
        );

        // Expected result after merging the accounts
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                Lists.newArrayList("John", "johnnybravo@mail.com")
        );

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_duplicateEmails() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Accounts with duplicate emails in the same account
        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com", "johnsmith@mail.com"),
                Lists.newArrayList("John", "johnsmith@mail.com", "john00@mail.com")
        );

        // Expected result (duplicate emails are merged)
        List<List<String>> expected = Lists.newArrayList(
                Collections.singleton(Lists.newArrayList("John", "john00@mail.com", "johnsmith@mail.com"))
        );

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_singleAccount() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Single account with one email
        List<List<String>> accounts = Lists.newArrayList(
                Collections.singleton(Lists.newArrayList("John", "johnsmith@mail.com"))
        );

        // Expected result (same as input)
        List<List<String>> expected = Lists.newArrayList(
                Collections.singleton(Lists.newArrayList("John", "johnsmith@mail.com"))
        );

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_emptyInput() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Empty input
        List<List<String>> accounts = Lists.newArrayList();

        // Expected result (empty list)
        List<List<String>> expected = Lists.newArrayList();

        List<List<String>> result = solution.accountsMerge(accounts);

        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testAccountsMerge_nullInput() {
        AccountsMerge_721 solution = new AccountsMerge_721();
        List<List<String>> result = solution.accountsMerge(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAccountsMerge_chainMerge() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // Three accounts linked in a chain: A shares with B, B shares with C
        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "a@mail.com", "b@mail.com"),
                Lists.newArrayList("John", "b@mail.com", "c@mail.com"),
                Lists.newArrayList("John", "c@mail.com", "d@mail.com")
        );

        List<List<String>> result = solution.accountsMerge(accounts);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).size()); // name + 4 emails
    }

    @Test
    void testAccountsMerge_sameNameDifferentPeople() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        List<List<String>> accounts = Lists.newArrayList(
                Lists.newArrayList("John", "john1@mail.com"),
                Lists.newArrayList("John", "john2@mail.com")
        );

        List<List<String>> result = solution.accountsMerge(accounts);
        assertEquals(2, result.size());
    }

    @Test
    void testAccountsMerge_giantCase() {
        AccountsMerge_721 solution = new AccountsMerge_721();

        // 100 accounts all sharing one common email
        List<List<String>> accounts = new java.util.ArrayList<>();
        for (int i = 0; i < 100; i++) {
            accounts.add(Lists.newArrayList("User", "common@mail.com", "unique" + i + "@mail.com"));
        }

        List<List<String>> result = solution.accountsMerge(accounts);
        assertEquals(1, result.size());
        assertEquals(102, result.get(0).size()); // name + common + 100 unique
    }
}

