package solutions.design;

/**
 * You have been tasked with writing a program for a popular bank that will automate all its incoming transactions (transfer, deposit, and withdraw).
 * The bank has n accounts numbered from 1 to n.
 * The initial balance of each account is stored in a 0-indexed integer array balance, with the (i + 1)th account having an initial balance of balance[i].
 * Execute all the valid transactions. A transaction is valid if:
 * The given account number(s) are between 1 and n, and
 * The amount of money withdrawn or transferred from is less than or equal to the balance of the account.
 * Implement the Bank class:
 * Bank(long[] balance) Initializes the object with the 0-indexed integer array balance.
 * boolean transfer(int account1, int account2, long money) Transfers money dollars from the account numbered account1 to the account numbered account2. Return true if the transaction was successful, false otherwise.
 * boolean deposit(int account, long money) Deposit money dollars into the account numbered account. Return true if the transaction was successful, false otherwise.
 * boolean withdraw(int account, long money) Withdraw money dollars from the account numbered account. Return true if the transaction was successful, false otherwise.
 *
 * @author BorisMirage
 * Time: 2026/11/10 21:24
 * Created with IntelliJ IDEA
 */

public class Bank_2043 {
    private final long[] balance;

    /**
     * Keeps the supplied balances array as the bank's storage, without copying
     * it. Account {@code i} is stored at {@code balance[i - 1]}, and each
     * operation checks its account numbers before indexing. A successful
     * withdrawal or transfer is allowed only when the source has enough funds;
     * a transfer then subtracts and adds the same amount, preserving the total
     * balance. Since the array is shared, changes made through the caller's
     * original reference are visible to this bank, and bank transactions are
     * visible through that reference. The problem bounds keep balances and
     * transaction amounts within {@code long} range.
     *
     * <p>Time complexity: O(1), because construction only stores one reference,
     * regardless of the number {@code n} of accounts.</p>
     * <p>Auxiliary space complexity: O(1), because no copy or other storage is
     * allocated; the supplied {@code n}-element array remains caller-owned
     * storage.</p>
     *
     * @param balance initial balances for accounts 1 through {@code balance.length};
     *                this exact array is retained and shared
     */
    public Bank_2043(long[] balance) {
        this.balance = balance;
    }

    /**
     * Transfers funds only after confirming both account numbers are valid and
     * the source can cover the amount. A same-account transfer succeeds without
     * changing the array, provided the source has enough funds. Failed requests
     * leave all balances unchanged. The official contract uses nonnegative
     * amounts and keeps balances within {@code long} range.
     *
     * <p>Time complexity: O(1), because the method checks at most two indexes
     * and updates at most two array entries.</p>
     * <p>Auxiliary space complexity: O(1), because it uses only scalar
     * parameters and no storage proportional to {@code n}, the account count.</p>
     *
     * @param account1 source account number, from 1 through the account count
     * @param account2 destination account number, from 1 through the account count
     * @param money    nonnegative amount to move
     * @return {@code true} if the transfer succeeds; otherwise {@code false}
     */
    public boolean transfer(int account1, int account2, long money) {
        if (account1 <= 0 || account1 > balance.length || account2 <= 0 || account2 > balance.length || balance[account1 - 1] < money) {
            return false;
        }
        if (account1 == account2) {
            return true;
        }
        // Update only after every failure condition has been checked, so a
        // rejected transfer can never leave a partially changed pair of accounts.
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }

    /**
     * Adds the amount to a valid account. An invalid account is rejected without
     * changing any balance; with the problem's nonnegative amounts and value
     * bounds, the addition remains representable as a {@code long}.
     *
     * <p>Time complexity: O(1), because it checks and updates one array entry.</p>
     * <p>Auxiliary space complexity: O(1), because it creates no additional
     * storage as the number {@code n} of accounts grows.</p>
     *
     * @param account account number, from 1 through the account count
     * @param money   nonnegative amount to add
     * @return {@code true} if the deposit succeeds; otherwise {@code false}
     */
    public boolean deposit(int account, long money) {
        if (account <= 0 || account > balance.length) {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }

    /**
     * Removes the amount only when the account is valid and has enough funds.
     * The balance check happens before subtraction, so a rejected withdrawal
     * does not alter the account.
     *
     * <p>Time complexity: O(1), because it checks and updates one array entry.</p>
     * <p>Auxiliary space complexity: O(1), because it creates no additional
     * storage as the number {@code n} of accounts grows.</p>
     *
     * @param account account number, from 1 through the account count
     * @param money   nonnegative amount to remove
     * @return {@code true} if the withdrawal succeeds; otherwise {@code false}
     */
    public boolean withdraw(int account, long money) {
        if (account <= 0 || account > balance.length || balance[account - 1] < money) {
            return false;
        }
        balance[account - 1] -= money;
        return true;
    }
}
