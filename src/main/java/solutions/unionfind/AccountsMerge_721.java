package solutions.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
     * Groups accounts that share an email address and returns one sorted account per person.
     *
     * <p>The first value in each input account is its name; all remaining values are email
     * addresses.  Two rows belong to the same person when their email-address sets are connected
     * through one or more shared addresses.  A name by itself never creates a connection, so two
     * people with the same name remain separate.  Every output row contains the name from its
     * representative account followed by each distinct email in lexicographic order.  The output
     * row order is unspecified, and this method does not mutate the supplied account lists.</p>
     *
     * <p>The algorithm first uses a disjoint-set structure to connect account indexes while
     * scanning emails, then collects the emails belonging to each final root in {@link TreeSet}s.
     * If {@code n} is the number of accounts and {@code e} is the total number of email entries,
     * auxiliary space is {@code O(n + e)};
     * Path compression makes repeated lookups fast in practice, but this implementation does not
     * use union by rank, so the safe worst-case bound for the union-find work is
     * {@code O(n(n + e))}; this also accounts for resolving every account when there are no
     * email entries.
     * a find can follow a chain of up to {@code n} account indexes.  The recursive find also uses
     * {@code O(n)} temporary stack space in that worst case.  Sorting the collected emails adds
     * {@code O(e log(e + 1))}, so the safe overall running time is
     * {@code O(n(n + e) + e log(e + 1))}.  The
     * documented input limits are {@code 1 <= n <= 1000}, {@code 2 <= account.size() <= 10}, and
     * name/email lengths from 1 through 30 characters.  This implementation also returns an empty
     * list for null or empty input and preserves name-only rows, both intentional extensions covered
     * by the local tests.</p>
     *
     * @param accounts accounts whose first element is a name and remaining elements are emails
     * @return merged accounts with unique, sorted emails; output account order is unspecified
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // The problem normally supplies at least one account, but returning an empty result keeps
        // the method total for callers that pass null or an empty collection.
        if (accounts == null || accounts.isEmpty()) {
            return new ArrayList<>();
        }

        UnionFind uf = new UnionFind(accounts.size());
        Map<String, Integer> map = new HashMap<>();

        // The outer stream visits each account index.  skip(1) deliberately ignores the name,
        // because equal names do not prove that two rows belong to the same person.  For each
        // email, putIfAbsent records its first owner and returns an earlier owner when the email
        // was already seen.  That returned index is unioned with the current index, which also
        // handles transitive bridges: A shares one email with B and B shares another with C.
        IntStream.range(0, accounts.size()).forEach(i ->
                accounts.get(i).stream()
                        .skip(1)
                        .forEach(s -> {
                            var existingAccount = map.putIfAbsent(s, i);
                            if (existingAccount != null) {
                                uf.union(existingAccount, i);
                            }
                        }));

        Map<Integer, TreeSet<String>> merged = new HashMap<>();

        // A second index stream resolves every account to its compressed root.  Each stream
        // element is only an account index; computeIfAbsent turns that index into one sorted set
        // per connected component, and addAll contributes the account's email sublist.  TreeSet
        // both removes duplicate email entries and keeps the output order required by the problem.
        IntStream.range(0, accounts.size()).forEach(i ->
                merged.computeIfAbsent(
                                uf.find(i),
                                _ -> new TreeSet<>())
                        .addAll(accounts.get(i).subList(1, accounts.get(i).size()))
        );

        // The final pipeline consumes Map.Entry<Integer, TreeSet<String>> elements.  map creates
        // one row per component: Stream.of supplies its representative name, concat appends the
        // already sorted email stream, and the inner toList materializes that row.  The outer
        // toList materializes all rows.  HashMap iteration order is unspecified, so only email
        // order within a row is part of the contract.
        return merged.entrySet().stream()
                .map(entry -> Stream.concat(
                                Stream.of(accounts.get(entry.getKey()).getFirst()),
                                entry.getValue().stream())
                        .toList())
                .toList();
    }

    private static class UnionFind {
        int[] parents;

        /**
         * Starts with one singleton component per account index.
         *
         * @param size number of account indexes to track
         */
        UnionFind(int size) {
            this.parents = IntStream.range(0, size).toArray();
        }

        /**
         * Returns the component root and compresses the traversed path so later lookups are faster.
         *
         * @param a account index to resolve
         * @return the representative index of {@code a}'s component
         */
        int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }
            return parents[a];
        }

        /**
         * Connects the two components by linking the first root to the second root. Path
         * compression in {@link #find(int)} shortens paths when they are subsequently visited;
         * repeated connections are harmless because both indexes are resolved first.
         */
        void union(int a, int b) {
            parents[find(a)] = parents[find(b)];
        }
    }
}
