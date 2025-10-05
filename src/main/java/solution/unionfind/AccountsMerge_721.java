package solution.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a list accounts, each element accounts[i] is a list of strings.
 * First element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * Merge these accounts.
 * Two accounts definitely belong to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * After merging the accounts, return the accounts in the following format:
 * First element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 * Note:
 * 1. The length of accounts will be in the range [1, 1000].
 * 2. The length of accounts[i] will be in the range [1, 10].
 * 3. The length of accounts[i][j] will be in the range [1, 30].
 *
 * @author BorisMirage
 * Time: 2019/07/02 16:27
 * Created with IntelliJ IDEA
 */

public class AccountsMerge_721 {
    /**
     * Merges accounts with common email addresses into a single account. Accounts are represented
     * as lists of strings, where the first element is the user's name and the rest are email addresses.
     * If two accounts share at least one email, they are considered to belong to the same person and are merged.
     * The method uses a Union-Find data structure to efficiently group accounts with common emails.
     * After processing all accounts, it collects the merged emails under each root account and returns a list of
     * lists where each list represents a distinct account with the person's name and all associated emails.
     * The Union-Find structure is used to track which accounts are connected by shared email addresses.
     * After performing union operations to group accounts, emails are collected and sorted for each root account.
     * Time Complexity:
     * - Union-Find operations (union/find) are amortized to O(α(n)), where α(n) is the inverse Ackermann function.
     * - Iterating over the accounts and emails has a time complexity of O(e log e), where e is the total number of emails.
     * - Therefore, the overall time complexity is O(e log e), where e is the total number of emails.
     * Space Complexity:
     * - The space complexity is O(e + n), where e is the total number of emails and n is the number of accounts.
     * - Space is used by the Union-Find data structure, email mapping, and result list.
     * @param accounts A list of accounts, where each account is a list where the first element is the account holder's name
     *                 and the rest are email addresses associated with that account.
     * @return A list of lists where each list represents a merged account, starting with the account holder's name followed
     *         by all associated email addresses. The emails are unique and sorted in lexicographical order.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // corner case
        if (accounts == null || accounts.isEmpty()) {
            return new ArrayList<>();
        }

        // create a new union find set with size of accounts (max number of possible names)
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        Map<String, Integer> map = new HashMap<>();

        // iter the list, if there are email addresses that're shared among different person, union them (their index)
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String address = accounts.get(i).get(j);

                // email addresses are unique, so if the email address has been seen before, they belong to same person
                if (map.containsKey(address)) {
                    // union the index of the email lists, i stands for the ith person in the list
                    uf.union(map.get(address), i);
                } else {
                    map.put(address, i);
                }
            }
        }

        Map<Integer, TreeSet<String>> merge = new HashMap<>();

        // iter all n people, if any of those are merged in the union-find operation, merge email to the final output
        for (int i = 0; i < n; i++) {

            // union-find find if it can be merged with another
            int root = uf.find(i);

            merge.putIfAbsent(root, new TreeSet<>());
            TreeSet<String> set = merge.get(root);
            set.addAll(accounts.get(i).subList(1, accounts.get(i).size()));
            merge.put(root, set);
        }

        // after previous iteration, the merge map stores all the indexes that are unique
        // add their name and email address to the output
        return merge.keySet().stream()
                .map(index -> {
                    List<String> emails = new ArrayList<>();
                    emails.add(accounts.get(index).getFirst());
                    emails.addAll(merge.get(index));
                    return emails;
                }).collect(Collectors.toList());
    }

    /**
     * Union-Find class maintains a collection of disjoint sets and supports efficient union and find operations to group accounts with shared email addresses.
     * The Union-Find structure uses path compression to optimize the find operation and union by rank (or size) to keep the tree flat and improve the performance of union operations.
     * The `find` method returns the root of the set to which a particular element belongs.
     * The `union` method merges two sets into one by linking the root of one set to the root of another.
     * Time Complexity:
     * - The `find` and `union` operations are nearly constant time, specifically O(α(n)), where α(n) is the inverse Ackermann function.
     * - In practice, these operations are extremely efficient, with amortized time complexity close to O(1).
     * Space Complexity:
     * - The space complexity is O(n), where n is the number of elements (accounts) being tracked in the Union-Find structure.
     */
    static class UnionFind {
        int[] parent;

        /**
         * Initializes a Union-Find data structure with the specified size.
         * Each element initially points to itself, representing a disjoint set.
         *
         * @param size The number of elements in the union-find structure. Each element is initially its own parent.
         */
        UnionFind(int size) {
            this.parent = IntStream.range(0, size).toArray();
        }

        /**
         * Finds the root of the set containing the specified element.
         * Implements path compression to flatten the structure, improving the efficiency of future operations.
         *
         * @param value The element whose root is to be found.
         * @return The root of the set containing the specified element.
         */
        int find(int value) {

            // if value is not its own parent, recursively find the root
            if (value != parent[value]) {
                // path compression: point the current value directly to the root
                parent[value] = find(parent[value]);
            }

            return parent[value];
        }

        /**
         * Unites the sets containing the two specified elements by linking one set's root to the other.
         * This operation merges two disjoint sets into one set.
         *
         * @param a The first element whose set is to be merged.
         * @param b The second element whose set is to be merged.
         */
        void union(int a, int b) {
            parent[find(a)] = parent[find(b)];
        }
    }
}
