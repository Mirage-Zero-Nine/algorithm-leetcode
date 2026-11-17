package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class Bank_2043Test {

    @Test
    public void depositsWithdrawalsAndTransfersUpdateBalances() {
        long[] balances = {10, 20, 30};
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.deposit(1, 5));
        assertTrue(bank.withdraw(2, 7));
        assertTrue(bank.transfer(1, 3, 8));
        assertArrayEquals(new long[]{7, 13, 38}, balances);
    }

    @Test
    public void rejectsInvalidAccountNumbersWithoutChangingBalances() {
        long[] balances = {5, 8};
        Bank_2043 bank = new Bank_2043(balances);
        assertFalse(bank.deposit(0, 1));
        assertFalse(bank.deposit(3, 1));
        assertFalse(bank.withdraw(0, 1));
        assertFalse(bank.withdraw(3, 1));
        assertFalse(bank.transfer(0, 1, 1));
        assertFalse(bank.transfer(1, 3, 1));
        assertFalse(bank.transfer(3, 0, 1));
        assertArrayEquals(new long[]{5, 8}, balances);
    }

    @Test
    public void rejectsInsufficientWithdrawalsAndTransfersAtomically() {
        long[] balances = {4, 9};
        Bank_2043 bank = new Bank_2043(balances);
        assertFalse(bank.withdraw(1, 5));
        assertFalse(bank.transfer(1, 2, 5));
        assertArrayEquals(new long[]{4, 9}, balances);
        assertTrue(bank.withdraw(1, 4));
        assertArrayEquals(new long[]{0, 9}, balances);
    }

    @Test
    public void zeroAmountTransactionsSucceedOnValidAccounts() {
        long[] balances = {0, 12};
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.deposit(1, 0));
        assertTrue(bank.withdraw(1, 0));
        assertTrue(bank.transfer(1, 2, 0));
        assertTrue(bank.transfer(2, 2, 0));
        assertArrayEquals(new long[]{0, 12}, balances);
    }

    @Test
    public void sameAccountTransferChecksFundsButDoesNotChangeBalance() {
        long[] balances = {6};
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.transfer(1, 1, 6));
        assertFalse(bank.transfer(1, 1, 7));
        assertArrayEquals(new long[]{6}, balances);
    }

    @Test
    public void callerAndBankShareTheSuppliedBalanceArray() {
        long[] balances = {10, 20};
        Bank_2043 bank = new Bank_2043(balances);
        balances[0] = 3;
        assertFalse(bank.withdraw(1, 4));
        assertTrue(bank.deposit(2, 2));
        assertArrayEquals(new long[]{3, 22}, balances);
        balances[1] = 0;
        assertFalse(bank.withdraw(2, 1));
    }

    @Test
    public void supportsSingleAccountAndRepeatedOperations() {
        long[] balances = {2};
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.withdraw(1, 1));
        assertFalse(bank.withdraw(1, 2));
        assertTrue(bank.deposit(1, 4));
        assertTrue(bank.transfer(1, 1, 5));
        assertFalse(bank.transfer(1, 1, 6));
        assertArrayEquals(new long[]{5}, balances);
    }

    @Test
    public void supportsMaximumInitialBalancesAndTransactionAmount() {
        long[] balances = {1_000_000_000_000L, 1_000_000_000_000L};
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.transfer(1, 2, 1_000_000_000_000L));
        assertTrue(bank.withdraw(2, 1_000_000_000_000L));
        assertArrayEquals(new long[]{0, 1_000_000_000_000L}, balances);
    }

    @Test
    public void balancesCanAccumulateAcrossTheMaximumOperationCount() {
        long[] balances = {1_000_000_000_000L, 0};
        Bank_2043 bank = new Bank_2043(balances);
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            for (int call = 0; call < 10_000; call++) {
                failIfInterrupted();
                if ((call & 1) == 0) {
                    assertTrue(bank.transfer(1, 2, 1_000_000_000_000L), "transfer " + call);
                } else {
                    assertTrue(bank.transfer(2, 1, 1_000_000_000_000L), "transfer " + call);
                }
            }
        });
        assertArrayEquals(new long[]{1_000_000_000_000L, 0}, balances);
    }

    @Test
    public void maximumAccountCountCanBeAddressedAtBothEnds() {
        long[] balances = new long[100_000];
        balances[0] = 1_000_000_000_000L;
        balances[balances.length - 1] = 1_000_000_000_000L;
        Bank_2043 bank = new Bank_2043(balances);
        assertTrue(bank.transfer(1, 100_000, 1_000_000_000_000L));
        assertTrue(bank.withdraw(100_000, 1_000_000_000_000L));
        assertArrayEquals(new long[]{0, 0, 0}, Arrays.copyOf(balances, 3));
        assertEquals(0, balances[99_998]);
        assertEquals(1_000_000_000_000L, balances[99_999]);
    }

    @Test
    public void deterministicMixedTransactionsMatchIndependentBalanceModel() {
        long[] actualBalances = {14, 0, 21, 9, 3};
        long[] expectedBalances = actualBalances.clone();
        Bank_2043 bank = new Bank_2043(actualBalances);
        Random random = new Random(2043);
        StringBuilder history = new StringBuilder();
        for (int call = 0; call < 2_000; call++) {
            int operation = random.nextInt(3);
            int account1 = random.nextInt(7) - 1;
            int account2 = random.nextInt(7) - 1;
            long money = random.nextInt(30);
            history.append(call).append(": ").append(operation).append(' ')
                    .append(account1).append(' ').append(account2).append(' ').append(money).append(';');
            boolean expected;
            boolean actual;
            if (operation == 0) {
                expected = valid(account1, expectedBalances.length)
                        && valid(account2, expectedBalances.length)
                        && expectedBalances[account1 - 1] >= money;
                actual = bank.transfer(account1, account2, money);
                if (expected && account1 != account2) {
                    expectedBalances[account1 - 1] -= money;
                    expectedBalances[account2 - 1] += money;
                }
            } else if (operation == 1) {
                expected = valid(account1, expectedBalances.length);
                actual = bank.deposit(account1, money);
                if (expected) {
                    expectedBalances[account1 - 1] += money;
                }
            } else {
                expected = valid(account1, expectedBalances.length)
                        && expectedBalances[account1 - 1] >= money;
                actual = bank.withdraw(account1, money);
                if (expected) {
                    expectedBalances[account1 - 1] -= money;
                }
            }
            assertEquals(expected, actual, "history=" + history);
            assertArrayEquals(expectedBalances, actualBalances, "history=" + history);
        }
    }

    @Test
    @Timeout(10)
    public void exhaustivelyChecksShortTransactionsAgainstIndependentModel() {
        // Enumerates every 1..3-operation sequence from 12 valid operation
        // descriptors over two accounts with starting balances 0..2.
        // Larger histories, maximum values, and invalid indices are explicit above.
        for (long first = 0; first <= 2; first++) {
            for (long second = 0; second <= 2; second++) {
                for (int length = 1; length <= 3; length++) {
                    int count = 1;
                    for (int i = 0; i < length; i++) {
                        count *= 12;
                    }
                    for (int encoded = 0; encoded < count; encoded++) {
                        int code = encoded;
                        long[] actual = {first, second};
                        long[] expected = actual.clone();
                        Bank_2043 bank = new Bank_2043(actual);
                        StringBuilder sequence = new StringBuilder();
                        for (int step = 0; step < length; step++) {
                            failIfInterrupted();
                            int action = code % 12;
                            code /= 12;
                            int kind = action / 4;
                            int account = (action / 2) % 2 + 1;
                            long amount = action % 2;
                            int destination = account == 1 ? 2 : 1;
                            sequence.append("(").append(kind).append(',').append(account)
                                    .append(',').append(destination).append(',').append(amount).append(')');
                            boolean expectedResult;
                            boolean actualResult;
                            if (kind == 0) {
                                expectedResult = expected[account - 1] >= amount;
                                actualResult = bank.withdraw(account, amount);
                                if (expectedResult) expected[account - 1] -= amount;
                            } else if (kind == 1) {
                                expectedResult = true;
                                actualResult = bank.deposit(account, amount);
                                expected[account - 1] += amount;
                            } else {
                                expectedResult = expected[account - 1] >= amount;
                                actualResult = bank.transfer(account, destination, amount);
                                if (expectedResult) {
                                    expected[account - 1] -= amount;
                                    expected[destination - 1] += amount;
                                }
                            }
                            assertEquals(expectedResult, actualResult, "initial=" + first + "," + second
                                    + ", sequence=" + sequence);
                            assertArrayEquals(expected, actual, "initial=" + first + "," + second
                                    + ", sequence=" + sequence);
                        }
                    }
                }
            }
        }
    }

    private static boolean valid(int account, int count) {
        return account >= 1 && account <= count;
    }

    private static void failIfInterrupted() {
        if (Thread.interrupted()) {
            throw new AssertionError("test workload was interrupted before it completed");
        }
    }
}
