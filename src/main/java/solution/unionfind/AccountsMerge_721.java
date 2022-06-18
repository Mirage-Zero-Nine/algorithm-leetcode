package solution.unionfind;

import java.util.*;

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
     * Union find.
     * First, traverse all email addresses, if email can not be found in map, put it with its index into map.
     * Otherwise, union the email to its parent index.
     * Then traverse every email list.
     * Find the parent of current list index and put all emails into the set list that belongs to key of its parent index.
     * Finally, traverse key set of disjoint set group.
     * Retrieve all emails from each parent index, and SORT them. Then adding the name at beginning of every sublist.
     *
     * @param accounts given user with emails, first element in each list is user and later is email
     * @return merged accounts
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        /* Corner case */
        if (accounts.size() == 0) {
            return new ArrayList<>();
        }

        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        HashMap<String, Integer> email = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {      // traverse all email addresses

                String address = accounts.get(i).get(j);

                if (email.containsKey(address)) {
                    uf.union(email.get(address), i);      // if address exist in map, union two address's parents
                } else {
                    email.put(address, i);        // otherwise, simply put address with its index (disjoint)
                }
            }
        }

        HashMap<Integer, TreeSet<String>> disjointSet = new HashMap<>();

        for (int i = 0; i < n; i++) {       // traverse every list in accounts

            int parent = uf.find(i);       // find root of each address list's owner for combination
            disjointSet.putIfAbsent(parent, new TreeSet<>());      // use TreeSet to pre-sort addresses

            TreeSet<String> currentSet = disjointSet.get(parent);

            for (int j = 1; j < accounts.get(i).size(); j++) {
                currentSet.add(accounts.get(i).get(j));     // add all non-duplicated addresses into set
            }

            disjointSet.put(parent, currentSet);        // put set back to its root owner
        }

        List<List<String>> result = new ArrayList<>();

        for (int index : disjointSet.keySet()) {
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(index).get(0));       // add name to head of list
            if (disjointSet.containsKey(index)) {       // add address
                temp.addAll(disjointSet.get(index));
            }
            result.add(temp);
        }

        return result;
    }

    /**
     * Union find.
     */
    static class UnionFind {
        int count;
        int[] parent;       // save each node's parent

        /**
         * Initialization of class.
         *
         * @param size count of nodes
         */
        UnionFind(int size) {
            this.count = size;
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;      // initially, each node is disjoint
            }
        }

        /**
         * Union two nodes.
         *
         * @param a first node
         * @param b second node
         */
        void union(int a, int b) {
            parent[find(a)] = parent[find(b)];      // mark second node's parent id with first one's parent id
        }

        /**
         * Find root of given node.
         *
         * @param x given node
         * @return root of node
         */
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);        // root is the node that id is matched to its parent node id
            }
            return parent[x];
        }
    }
}
