package playground;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankServiceTest {
    @Test
    void readsStartingBalancesAndRejectsUnknownAccountLookup() {
        BankService bank = bank(100, 25);

        assertEquals(100, bank.getBalance("A"));
        assertEquals(25, bank.getBalance("B"));
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance("missing"));
        assertTrue(bank.getTransaction("missing").isEmpty());
    }

    @Test
    void depositsWholeCentsAndRecordsBeforeAndAfterBalances() {
        BankService bank = bank(40);

        BankService.OperationResult.Success success = success(
                bank.deposit("d1", "A", cents(35)));
        assertEquals(75, bank.getBalance("A"));
        assertEquals("d1", success.transaction().requestId());
        assertEquals(BankService.OperationType.DEPOSIT, success.transaction().type());
        assertEquals(List.of(new BankService.BalanceChange("A", 40, 75)),
                success.transaction().balanceChanges());
        assertEquals(Optional.of(success.transaction()),
                bank.getTransaction(success.transaction().id()));
        assertTrue(bank.getTransaction("d1").isEmpty());
    }

    @Test
    void successfulDepositRetryReturnsSameTransactionAndDoesNotCreditTwice() {
        BankService bank = bank(12);

        BankService.OperationResult first = bank.deposit("same", "A", cents(8));
        BankService.OperationResult retry = bank.deposit("same", "A", cents(8));

        assertEquals(first, retry);
        assertEquals(20, bank.getBalance("A"));
    }

    @Test
    void withdrawsExactBalanceAndRecordsTheChange() {
        BankService bank = bank(75);

        BankService.OperationResult.Success success = success(
                bank.withdraw("w1", "A", cents(75)));

        assertEquals(0, bank.getBalance("A"));
        assertEquals(BankService.OperationType.WITHDRAW, success.transaction().type());
        assertEquals(List.of(new BankService.BalanceChange("A", 75, 0)),
                success.transaction().balanceChanges());
    }

    @Test
    void successfulWithdrawalRetryDoesNotDebitTwice() {
        BankService bank = bank(30);

        BankService.OperationResult first = bank.withdraw("same", "A", cents(11));
        BankService.OperationResult retry = bank.withdraw("same", "A", cents(11));

        assertEquals(first, retry);
        assertEquals(19, bank.getBalance("A"));
    }

    @Test
    void insufficientWithdrawalLeavesBalancesUnchangedAndPriorTransactionFindable() {
        BankService bank = bank(9);
        BankService.Transaction prior = success(
                bank.deposit("prior", "A", cents(1))).transaction();

        BankService.OperationResult.Failure failure = failure(
                bank.withdraw("w1", "A", cents(11)));

        assertEquals(BankService.FailureReason.INSUFFICIENT_FUNDS, failure.reason());
        assertEquals(10, bank.getBalance("A"));
        assertEquals(Optional.of(prior), bank.getTransaction(prior.id()));
    }

    @Test
    void transfersBetweenAccountsAndRecordsBothBalanceChanges() {
        BankService bank = bank(90, 10);

        BankService.OperationResult.Success success = success(
                bank.transfer("t1", "A", "B", cents(35)));

        assertEquals(55, bank.getBalance("A"));
        assertEquals(45, bank.getBalance("B"));
        assertEquals(BankService.OperationType.TRANSFER, success.transaction().type());
        assertEquals(List.of(new BankService.BalanceChange("A", 90, 55),
                        new BankService.BalanceChange("B", 10, 45)),
                success.transaction().balanceChanges());
        assertEquals(100, bank.getBalance("A") + bank.getBalance("B"));
    }

    @Test
    void successfulTransferRetryDoesNotMoveMoneyAgain() {
        BankService bank = bank(70, 30);

        BankService.OperationResult first = bank.transfer("same", "A", "B", cents(20));
        BankService.OperationResult retry = bank.transfer("same", "A", "B", cents(20));

        assertEquals(first, retry);
        assertEquals(50, bank.getBalance("A"));
        assertEquals(50, bank.getBalance("B"));
    }

    @Test
    void insufficientTransferLeavesBalancesUnchangedAndPriorTransactionFindable() {
        BankService bank = bank(4, 8);
        BankService.Transaction prior = success(
                bank.transfer("prior", "B", "A", cents(1))).transaction();

        BankService.OperationResult.Failure failure = failure(
                bank.transfer("t1", "A", "B", cents(6)));

        assertEquals(BankService.FailureReason.INSUFFICIENT_FUNDS, failure.reason());
        assertEquals(5, bank.getBalance("A"));
        assertEquals(7, bank.getBalance("B"));
        assertEquals(Optional.of(prior), bank.getTransaction(prior.id()));
    }

    @Test
    void rejectsUnknownAccountsAndSameAccountTransferWithoutChangingState() {
        BankService bank = bank(20, 30);

        assertThrows(IllegalArgumentException.class,
                () -> bank.deposit("d", "missing", cents(1)));
        assertThrows(IllegalArgumentException.class,
                () -> bank.withdraw("w", "missing", cents(1)));
        assertThrows(IllegalArgumentException.class,
                () -> bank.transfer("t", "A", "missing", cents(1)));
        assertThrows(IllegalArgumentException.class,
                () -> bank.transfer("self", "A", "A", cents(1)));
        assertEquals(20, bank.getBalance("A"));
        assertEquals(30, bank.getBalance("B"));
    }

    @Test
    void rejectsNonPositiveAndFractionalAmounts() {
        BankService bank = bank(20, 30);

        assertThrows(IllegalArgumentException.class,
                () -> bank.deposit("zero", "A", cents(0)));
        assertThrows(IllegalArgumentException.class,
                () -> bank.withdraw("negative", "A", cents(-1)));
        assertThrows(IllegalArgumentException.class,
                () -> bank.transfer("zero-transfer", "A", "B", cents(0)));
        assertThrows(ArithmeticException.class,
                () -> bank.deposit("fraction", "A", new BigDecimal("1.5")));
        assertEquals(20, bank.getBalance("A"));
        assertEquals(30, bank.getBalance("B"));
    }

    @Test
    void eachSuccessfulRequestCreatesOneDistinctLedgerEntry() {
        BankService bank = bank(10, 5);

        BankService.Transaction deposit = success(bank.deposit("d", "A", cents(2))).transaction();
        BankService.Transaction withdrawal = success(bank.withdraw("w", "B", cents(1))).transaction();
        BankService.Transaction transfer = success(bank.transfer("t", "A", "B", cents(3))).transaction();

        assertNotSame(deposit, withdrawal);
        assertNotSame(deposit, transfer);
        assertNotSame(withdrawal, transfer);
        assertEquals(Optional.of(deposit), bank.getTransaction(deposit.id()));
        assertEquals(Optional.of(withdrawal), bank.getTransaction(withdrawal.id()));
        assertEquals(Optional.of(transfer), bank.getTransaction(transfer.id()));
        assertEquals(16, bank.getBalance("A") + bank.getBalance("B"));
    }

    @Test
    void transactionCopiesTheMutableInputList() {
        List<BankService.BalanceChange> changes = new ArrayList<>();
        changes.add(new BankService.BalanceChange("A", 1, 2));

        BankService.Transaction transaction = new BankService.Transaction(
                "tx", "request", BankService.OperationType.DEPOSIT, changes);
        changes.clear();

        assertEquals(List.of(new BankService.BalanceChange("A", 1, 2)),
                transaction.balanceChanges());
        assertThrows(UnsupportedOperationException.class,
                () -> transaction.balanceChanges().clear());
    }

    @Test
    void preservesCentAmountsAboveDoubleIntegerPrecision() {
        long amount = 9_007_199_254_740_993L;
        BankService bank = bank(0);

        success(bank.deposit("large-cents", "A", cents(amount)));

        assertEquals(amount, bank.getBalance("A"));
    }

    @Test
    @Timeout(10)
    void exhaustivelyChecksShortOperationSequencesAgainstIndependentBalanceModel() {
        // Enumerates 9 starting balance pairs and all 584 sequences of up to 3 operations.
        List<Operation> operations = List.of(
                new Operation(Kind.DEPOSIT, 0, 0, 1),
                new Operation(Kind.DEPOSIT, 1, 0, 2),
                new Operation(Kind.WITHDRAW, 0, 0, 1),
                new Operation(Kind.WITHDRAW, 1, 0, 2),
                new Operation(Kind.TRANSFER, 0, 1, 1),
                new Operation(Kind.TRANSFER, 1, 0, 2),
                new Operation(Kind.TRANSFER, 0, 1, 2),
                new Operation(Kind.TRANSFER, 1, 0, 1));

        for (int first = 0; first <= 2; first++) {
            for (int second = 0; second <= 2; second++) {
                for (int length = 1; length <= 3; length++) {
                    int sequenceCount = intPower(operations.size(), length);
                    for (int encoded = 0; encoded < sequenceCount; encoded++) {
                        runSequence(first, second, length, encoded, operations);
                    }
                }
            }
        }
    }

    @Test
    @Timeout(10)
    void processesASeveralThousandOperationWorkloadAndPreservesFunds() {
        int accountCount = 80;
        int transfers = 2_000;
        List<BankService.Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountCount; i++) {
            accounts.add(new BankService.Account("A" + i, 1_000));
        }
        BankService bank = new BankService(accounts);

        for (int i = 0; i < transfers; i++) {
            int from = i % accountCount;
            int to = (from + 1) % accountCount;
            assertInstanceOf(BankService.OperationResult.Success.class,
                    bank.transfer("stress-" + i, "A" + from, "A" + to, cents(1)));
        }

        long total = 0;
        for (int i = 0; i < accountCount; i++) {
            total += bank.getBalance("A" + i);
        }
        assertEquals(accountCount * 1_000L, total);
    }

    private static void runSequence(
            int first, int second, int length, int encoded, List<Operation> operations) {
        BankService bank = bank(first, second);
        long[] expected = {first, second};
        int remaining = encoded;
        for (int step = 0; step < length; step++) {
            Operation operation = operations.get(remaining % operations.size());
            remaining /= operations.size();
            long amount = operation.amount;
            boolean succeeds = operation.kind == Kind.DEPOSIT
                    || expected[operation.from] >= amount;
            String requestId = "seq-" + first + "-" + second + "-" + length + "-"
                    + encoded + "-" + step;
            BankService.OperationResult result;
            if (operation.kind == Kind.DEPOSIT) {
                result = bank.deposit(requestId, id(operation.from), cents(amount));
                expected[operation.from] += amount;
            } else if (operation.kind == Kind.WITHDRAW) {
                result = bank.withdraw(requestId, id(operation.from), cents(amount));
                if (succeeds) {
                    expected[operation.from] -= amount;
                }
            } else {
                result = bank.transfer(requestId, id(operation.from), id(operation.to), cents(amount));
                if (succeeds) {
                    expected[operation.from] -= amount;
                    expected[operation.to] += amount;
                }
            }

            String context = "balances=" + first + "," + second + ", length=" + length
                    + ", sequence=" + encoded + ", step=" + step + ", operation=" + operation;
            if (succeeds) {
                BankService.OperationResult.Success success = assertInstanceOf(
                        BankService.OperationResult.Success.class, result, context);
                assertEquals(requestId, success.transaction().requestId(), context);
                assertEquals(Optional.of(success.transaction()),
                        bank.getTransaction(success.transaction().id()), context);
            } else {
                BankService.OperationResult.Failure failure = assertInstanceOf(
                        BankService.OperationResult.Failure.class, result, context);
                assertEquals(BankService.FailureReason.INSUFFICIENT_FUNDS,
                        failure.reason(), context);
            }
            assertEquals(expected[0], bank.getBalance("A"), context);
            assertEquals(expected[1], bank.getBalance("B"), context);
        }
    }

    private static BankService bank(long... balances) {
        List<BankService.Account> accounts = new ArrayList<>();
        for (int i = 0; i < balances.length; i++) {
            accounts.add(new BankService.Account(id(i), balances[i]));
        }
        return new BankService(accounts);
    }

    private static String id(int index) {
        return index == 0 ? "A" : "B";
    }

    private static BigDecimal cents(long amount) {
        return BigDecimal.valueOf(amount);
    }

    private static BankService.OperationResult.Success success(BankService.OperationResult result) {
        return assertInstanceOf(BankService.OperationResult.Success.class, result);
    }

    private static BankService.OperationResult.Failure failure(BankService.OperationResult result) {
        return assertInstanceOf(BankService.OperationResult.Failure.class, result);
    }

    private static int intPower(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    private enum Kind { DEPOSIT, WITHDRAW, TRANSFER }

    private record Operation(Kind kind, int from, int to, long amount) { }
}
