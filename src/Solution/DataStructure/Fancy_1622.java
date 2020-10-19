package Solution.DataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 * Implement the Fancy_1622 class:
 * Fancy_1622() Initializes the object with an empty sequence.
 * - void append(val) Appends an integer val to the end of the sequence.
 * - void addAll(inc) Increments all existing values in the sequence by an integer inc.
 * - void multAll(m) Multiplies all existing values in the sequence by an integer m.
 * - int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 10^9 + 7.
 * - If the index is greater or equal than the length of the sequence, return -1.
 *
 * @author BorisMirage
 * Time: 2020/10/17 11:13
 * Created with IntelliJ IDEA
 */

public class Fancy_1622 {
    long MOD = 1000000007;
    long addIncrement = 0, multipleIncrement = 1, inverseMultiple = 1;       // P = inverse of Q
    List<long[]> sequence = new ArrayList<>();
    long[] inv = new long[101];

    /**
     * If addAll called, then addIncrement += inc.
     * If multAll called, then multipleIncrement = sequence[i] * (mult * m) + (increment * m).
     * If getIndex called, then calculate the final result by sequence[i] * multipleIncrement + addIncrement.
     * However, if append called, then it should deduct the value in addIncrement and multipleIncrement.
     * Current addIncrement and multipleIncrement is not applicable for the newly append value.
     * Hence, the newly appended value should do the newVal -= increment first, then do newVal /= mult.
     * To reduce calculation on newVal /= mult, since there is mod on it, implement Fermat's little theorem:
     * newVal /= mult => newVal * mult^(-1)
     * a^(m - 1) ≡ 1 (mod m) => a^(-1) ≡ a^(m - 2) (mod m) => to get a^(-1) (mod m), calculate a^(m - 2) is enough.
     * In this case, mult will be a. And m should be a prime number, which is MOD, m - 2 is MOD - 2.
     */
    public Fancy_1622() {
        for (int i = 1; i <= 100; i++) {
            inv[i] = modPow(i);     // pre-cache a^(MOD-2)
        }
    }

    /**
     * Appends an integer val to the end of the sequence.
     *
     * @param val value to be appended
     */
    public void append(int val) {
        long[] tmp = new long[]{val, addIncrement, inverseMultiple};
        sequence.add(tmp);
    }

    /**
     * Increments all existing values in the sequence by an integer inc.
     *
     * @param inc increment value
     */
    public void addAll(int inc) {
        addIncrement = (addIncrement + inc) % MOD;
    }

    /**
     * Multiplies all existing values in the sequence by an integer m.
     *
     * @param m multiple value
     */
    public void multAll(int m) {
        addIncrement = (addIncrement * m) % MOD;
        multipleIncrement = (multipleIncrement * m) % MOD;
        inverseMultiple = (inverseMultiple * inv[m]) % MOD;
    }

    /**
     * Gets the current value at index idx (0-indexed) of the sequence modulo 10^9 + 7.
     *
     * @param i index
     * @return the current value at index idx
     */
    public int getIndex(int i) {
        if (i >= sequence.size()) {
            return -1;
        }

        long[] base = sequence.get(i);
        long multiple = (multipleIncrement * base[2]) % MOD;
        long increment = ((addIncrement - base[1] * multiple) % MOD + MOD) % MOD;
        return (int) ((base[0] * multiple + increment) % MOD);
    }

    /**
     * Do the a^(10^9 + 5).
     *
     * @param a value in Fermat's little theorem
     * @return a^(10^9 + 5)
     */
    private long modPow(long a) {
        long v = a;
        for (int i = 0; i < 9; i++) {       // x^(10^9)
            v = pow10(v);
        }
        long y = (a * a) % MOD;     // x^(10^9 + 2)
        y = (y * y) % MOD;          // x^(10^9 + 4)
        y = (y * a) % MOD;          // x^(10^9 + 5)
        return (v * y) % MOD;       // x^(10^9) * x^5 = x^(10^9 + 5)
    }

    /**
     * Calculate x^10.
     *
     * @param x given value
     * @return x^10
     */
    private long pow10(long x) {
        x = (x * x) % MOD;          // x^2
        long y = (x * x) % MOD;     // x^4
        y = (y * y) % MOD;          // x^8
        return (y * x) % MOD;       // x^8 * x^2 = x^10
    }
}
