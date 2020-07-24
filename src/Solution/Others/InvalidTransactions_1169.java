package Solution.Others;

import java.util.*;

/**
 * A transaction is possibly invalid if:
 * 1. the amount exceeds $1000, or;
 * 2. If it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * Each transaction string consists of comma separated values representing name, time, amount, and city.
 * Given a list of transactions, return a list of transactions that are possibly invalid.
 * You may return the answer in any order.
 *
 * @author BorisMirage
 * Time: 2019/08/24 19:36
 * Created with IntelliJ IDEA
 */

public class InvalidTransactions_1169 {
    /**
     * Brute force.
     *
     * @param transactions given transaction
     * @return list of transactions that are possibly invalid
     */
    public List<String> invalidTransactions(String[] transactions) {
        List<String> out = new ArrayList<>();
        int n = transactions.length;

        /* Corner case */
        if (n == 0) {
            return out;
        }

        String[] name = new String[n], city = new String[n];
        int[] time = new int[n], money = new int[n];
        boolean[] add = new boolean[n];
        for (int i = 0; i < n; i++) {
            String[] items = transactions[i].split(",");
            name[i] = items[0];
            time[i] = Integer.parseInt(items[1]);
            money[i] = Integer.parseInt(items[2]);
            city[i] = items[3];
        }

        for (int i = 0; i < n; i++) {
            if (money[i] > 1000) {
                add[i] = true;
            }
            for (int j = i + 1; j < n; j++) {
                if (name[i].equals(name[j]) && Math.abs(time[i] - time[j]) <= 60 && !city[i].equals(city[j])) {
                    add[i] = true;
                    add[j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (add[i]) {
                out.add(transactions[i]);
            }
        }
        return out;
    }
}
