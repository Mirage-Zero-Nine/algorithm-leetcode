package playground;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Build a small bank service as an extension to LeetCode 2043. The service
 * supports one currency and a fixed set of accounts held in memory. It is a
 * sequential program exercise, not a connection to a real bank.</p>
 *
 * <p>Each account has a unique ID and a non-negative balance. Store money as
 * integer cents; amounts must be positive whole cents. Support looking up an
 * account balance, depositing, withdrawing, transferring between accounts,
 * and looking up a transaction by its transaction ID.</p>
 *
 * <p>Reject operations for unknown accounts, withdrawals or transfers with
 * insufficient funds, and transfers from an account to itself. A rejected
 * operation must leave balances and the successful transaction ledger
 * unchanged. A successful transfer debits the source, credits the destination,
 * and records its transaction ID and balance changes together.</p>
 *
 * <p>Every mutation request has a request ID. Repeating the same successful
 * request with identical details returns its original result without applying
 * the operation again. The request ID and successful transaction record are
 * kept together with the balance change.</p>
 *
 * <p>For example, start with account {@code A} holding 1,000 cents and account
 * {@code B} holding 200 cents. A transfer request of 300 cents from {@code A} to
 * {@code B} with request ID {@code R1} leaves balances of 700 and 500
 * cents. Repeating that identical request leaves the balances unchanged and
 * returns its original result. A new transfer of 800 cents from {@code A} to
 * {@code B} fails for insufficient funds and changes nothing.</p>
 *
 * <p>Optional follow-up: request IDs could also identify failed attempts. For example, if {@code A}
 * starts with 50 cents, a 100-cent withdrawal using request ID {@code R1}
 * fails for insufficient funds. After a separate 100-cent deposit using
 * {@code R2}, {@code A} has 150 cents, but retrying the identical withdrawal
 * with {@code R1} still returns its original failure and leaves the balance
 * at 150 cents. A withdrawal under a new request ID may then succeed.</p>
 *
 * <p>Optional follow-up: reject a request ID reused for different details. Starting from any
 * balance, a 10-cent deposit into {@code A} with request ID {@code R3} may
 * succeed; attempting a 20-cent deposit with that same ID must be rejected
 * as a conflict, leaving the balance and successful transaction ledger as
 * they were after the first deposit.</p>
 *
 * <p>Tasks:</p>
 * <ul>
 *   <li>Choose the account, transaction, request, and result data structures.</li>
 *   <li>Implement account lookup, deposit, withdrawal, transfer, and transaction lookup.</li>
 *   <li>Test failed transfers, successful retries, and attempts to use fractional cents.</li>
 *   <li>As optional extensions, discuss replaying failed requests and rejecting conflicting
 *       request-ID reuse.</li>
 *   <li>Check that transfers conserve the total balance and that each ledger record matches the
 *       balance changes it represents.</li>
 *   <li>As a follow-up, explain how you would recover consistently after a crash during a
 *       transfer.</li>
 * </ul>
 */
public class BankService {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, Transaction> transactions = new ConcurrentHashMap<>();
    private final Map<String, OperationResult> requests = new ConcurrentHashMap<>();

    /**
     * Creates an in-memory bank from the supplied account snapshots. IDs are expected to be unique
     * and balances non-negative; the service stores one current snapshot per account. Operations
     * use expected constant-time map lookup, while construction visits every supplied account.
     *
     * <p>Time complexity: O(a), where {@code a} is the number of supplied accounts.</p>
     * <p>Auxiliary space complexity: O(1) temporary space, excluding retained service state.
     * Initially, the service retains O(a) account snapshots.</p>
     *
     * @param initialAccounts starting account IDs and balances
     * @throws NullPointerException if the collection or an account is {@code null}
     * @implNote The service retains current account snapshots, successful transactions, and
     * successful request results for its lifetime. After {@code t} successful mutations, the
     * transaction and request maps each contain at most {@code t} entries.
     */
    public BankService(Collection<Account> initialAccounts) {
        initialAccounts.forEach(account -> accounts.put(account.id, account));
    }

    /**
     * Returns the current balance for an existing account.
     *
     * <p>Time complexity: O(1) expected, for two hash-map lookups.</p>
     * <p>Auxiliary space complexity: O(1).</p>
     *
     * @param accountId ID of the account to inspect
     * @return its balance in cents
     * @throws IllegalArgumentException if the account does not exist
     */
    public long getBalance(String accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account ID does not exist.");
        }
        return accounts.get(accountId).balanceInCents();
    }

    /**
     * Deposits a positive whole-cent amount into an existing account. Repeating a successful
     * request ID returns the original result without applying the deposit a second time. Handling
     * retries after failure or rejecting reuse with different details are optional extensions.
     *
     * <p>Time complexity: O(1) expected, for a fixed number of map operations.</p>
     * <p>Auxiliary space complexity: O(1) temporary space, excluding entries retained for a
     * successful deposit.</p>
     *
     * @param requestId     identifier shared by retries of this exact request
     * @param accountId     account to credit
     * @param amountInCents positive whole-cent amount to deposit
     * @return the original or newly created operation result
     * @throws IllegalArgumentException if the account is unknown or the amount is not positive
     * @throws ArithmeticException      if the amount is fractional or does not fit in a {@code long}
     */
    public OperationResult deposit(
            @NonNull String requestId,
            @NonNull String accountId,
            @NonNull BigDecimal amountInCents) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Invalid account id");
        }
        if (amountInCents.longValueExact() <= 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (requests.containsKey(requestId)) {
            // Reuse the successful result so this request cannot apply the balance change twice.
            return requests.get(requestId);
        }

        long current = accounts.get(accountId).balanceInCents;
        long balance = current + amountInCents.longValueExact();
        // Replace the immutable balance snapshot; earlier transaction values remain unchanged.
        accounts.put(accountId, new Account(accountId, balance));
        OperationResult result = new OperationResult.Success(createTransaction(
                requestId,
                OperationType.DEPOSIT,
                List.of(new BalanceChange(accountId, current, balance))));
        requests.put(requestId, result);
        return result;
    }

    /**
     * Withdraws a positive whole-cent amount when the account has enough funds. Repeating a
     * successful request ID returns its original result without applying the withdrawal again.
     * Replay of failed requests and rejecting reuse with different details are optional extensions.
     *
     * <p>Time complexity: O(1) expected, for a fixed number of map operations.</p>
     * <p>Auxiliary space complexity: O(1) temporary space, excluding entries retained for a
     * successful withdrawal.</p>
     *
     * @param requestId     identifier shared by retries of this exact request
     * @param accountId     account to debit
     * @param amountInCents positive whole-cent amount to withdraw
     * @return the original or newly created result, or insufficient funds
     * @throws IllegalArgumentException if the account is unknown or the amount is not positive
     * @throws ArithmeticException      if the amount is fractional or does not fit in a {@code long}
     */
    public OperationResult withdraw(
            @NonNull String requestId,
            @NonNull String accountId,
            @NonNull BigDecimal amountInCents) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Invalid account id");
        }
        if (amountInCents.longValueExact() <= 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (requests.containsKey(requestId)) {
            // A successful retry returns its earlier result instead of changing the balance again.
            return requests.get(requestId);
        }
        long current = accounts.get(accountId).balanceInCents;
        if (current < amountInCents.longValueExact()) {
            return new OperationResult.Failure(FailureReason.INSUFFICIENT_FUNDS);
        }
        long balance = current - amountInCents.longValueExact();
        accounts.put(accountId, new Account(accountId, balance));
        OperationResult result = new OperationResult.Success(createTransaction(
                requestId,
                OperationType.WITHDRAW,
                List.of(new BalanceChange(accountId, current, balance))));
        requests.put(requestId, result);
        return result;
    }

    /**
     * Transfers a positive whole-cent amount between distinct existing accounts when the source
     * has enough funds. The source debit and destination credit preserve the total balance, and one
     * transaction records both changes. Repeating a successful request ID returns its original
     * result. Replay of failed requests and rejecting reuse with different details are optional
     * extensions.
     *
     * <p>Time complexity: O(1) expected, for a fixed number of map operations.</p>
     * <p>Auxiliary space complexity: O(1) temporary space, excluding entries retained for a
     * successful transfer.</p>
     *
     * @param requestId     identifier shared by retries of this exact request
     * @param fromAccountId account to debit
     * @param toAccountId   distinct account to credit
     * @param amountInCents positive whole-cent amount to transfer
     * @return the original or newly created result, or insufficient funds
     * @throws IllegalArgumentException if an account is unknown, the IDs match, or the amount is
     *                                  not positive
     * @throws ArithmeticException      if the amount is fractional or does not fit in a {@code long}
     */
    public OperationResult transfer(
            String requestId, String fromAccountId,
            String toAccountId, BigDecimal amountInCents) {
        if (!accounts.containsKey(fromAccountId) || !accounts.containsKey(toAccountId)) {
            throw new IllegalArgumentException("Invalid account id.");
        }
        if (amountInCents.longValueExact() <= 0) {
            throw new IllegalArgumentException("Amount must be non-negative.");
        }
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Account must be different.");
        }
        if (requests.containsKey(requestId)) {
            // A successful retry must not debit and credit the accounts a second time.
            return requests.get(requestId);
        }

        long currentFrom = accounts.get(fromAccountId).balanceInCents;
        if (currentFrom < amountInCents.longValueExact()) {
            return new OperationResult.Failure(FailureReason.INSUFFICIENT_FUNDS);
        }

        // Check funds before replacing either snapshot, so an insufficient transfer changes neither.
        long balanceFrom = currentFrom - amountInCents.longValueExact();
        long currentTo = accounts.get(toAccountId).balanceInCents;
        long balanceTo = currentTo + amountInCents.longValueExact();
        accounts.put(fromAccountId, new Account(fromAccountId, balanceFrom));
        accounts.put(toAccountId, new Account(toAccountId, balanceTo));
        OperationResult result = new OperationResult.Success(createTransaction(
                requestId,
                OperationType.TRANSFER,
                List.of(
                        new BalanceChange(fromAccountId, currentFrom, balanceFrom),
                        new BalanceChange(toAccountId, currentTo, balanceTo))));
        requests.put(requestId, result);
        return result;
    }

    /**
     * Looks up a successful transaction by its generated transaction ID; this ID is distinct from
     * the request ID used for retries.
     *
     * <p>Time complexity: O(1) expected, for one hash-map lookup.</p>
     * <p>Auxiliary space complexity: O(1).</p>
     *
     * @param transactionId generated ID of the transaction to find
     * @return the transaction, or empty when no successful transaction has that ID
     */
    public Optional<Transaction> getTransaction(@NonNull String transactionId) {
        return Optional.ofNullable(transactions.get(transactionId));
    }

    /**
     * Creates and indexes a successful operation's transaction record.
     *
     * <p>Time complexity: O(1) expected for a fixed-size list and hash-map insertion.</p>
     * <p>Auxiliary space complexity: O(1), since callers provide one or two balance changes;
     * the transaction remains retained by the service.</p>
     *
     * @param requestId request that produced the transaction
     * @param type      kind of successful operation
     * @param list      before-and-after balances for affected accounts
     * @return the indexed transaction
     */
    private Transaction createTransaction(
            String requestId, OperationType type, List<BalanceChange> list) {
        String uuid = UUID.randomUUID().toString();
        Transaction tx = new Transaction(uuid, requestId, type, list);
        transactions.put(uuid, tx);
        return tx;
    }

    /**
     * Initial account snapshot with a balance measured in whole cents. This immutable value can be
     * replaced to represent a later balance.
     */
    public record Account(String id, long balanceInCents) {
    }

    /**
     * One account's balance immediately before and after a transaction, in cents.
     */
    public record BalanceChange(
            String accountId, long balanceBeforeInCents, long balanceAfterInCents) {
    }

    /**
     * Record of a successful operation with balance changes stored as an immutable list. The
     * generated {@code id} is the lookup key for {@link BankService#getTransaction(String)}; the
     * caller-supplied {@code requestId} identifies retries of successful mutation requests.
     * Rejecting different details under a reused request ID is an optional follow-up.
     *
     * @param id             generated transaction lookup ID
     * @param requestId      caller-supplied mutation request and retry ID
     * @param type           operation represented by this transaction
     * @param balanceChanges immutable per-account before-and-after balances
     */
    public record Transaction(
            String id, String requestId, OperationType type, List<BalanceChange> balanceChanges) {
        /**
         * Stores an immutable snapshot of the supplied balance changes.
         *
         * <p>Time complexity: O(k), where {@code k} is the number of changes copied.</p>
         * <p>Auxiliary space complexity: O(k) temporary space in the worst case while copying;
         * the returned transaction retains O(k) space for its immutable snapshot.</p>
         *
         * @param id             generated transaction lookup ID
         * @param requestId      request ID that produced the transaction
         * @param type           successful operation type
         * @param balanceChanges affected account balances before and after the operation
         */
        public Transaction {
            balanceChanges = List.copyOf(balanceChanges);
        }
    }

    public sealed interface OperationResult {
        record Success(Transaction transaction)
                implements OperationResult {
        }

        record Failure(FailureReason reason)
                implements OperationResult {
        }
    }

    public enum FailureReason {
        INVALID_AMOUNT,
        UNKNOWN_ACCOUNT,
        INSUFFICIENT_FUNDS,
        SAME_ACCOUNT,
        REQUEST_ID_CONFLICT,
        BALANCE_OVERFLOW
    }

    public enum OperationType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
}
