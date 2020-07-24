package Solution.DataStructure;

/**
 * Design a Phone Directory which supports the following operations:
 * 1. get: Provide a number which is not assigned to anyone.
 * 2. check: Check if a number is available or not.
 * 3. release: Recycle or release a number.
 *
 * @author BorisMirage
 * Time: 2020/04/04 11:23
 * Created with IntelliJ IDEA
 */

public class PhoneDirectory_379 {
    private int[] nextNumber;       // stores next available number
    private int current;

    /**
     * Constructor.
     * Use an array to store next available number. The index of array is the number.
     * If the index (phone number) is available, then it stores the next available number (index).
     * If a number is taken, then mark this position to -1.
     *
     * @param maxNumbers the maximum numbers that can be stored in the phone directory
     */
    public PhoneDirectory_379(int maxNumbers) {
        nextNumber = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            nextNumber[i] = (i + 1) % maxNumbers;       // the array stores next available number
        }
        current = 0;      // initially, 0 is the first available number
    }

    /**
     * Provide a number which is not assigned to anyone.
     * Return the current available number. Then set element under current number array to -1, which is invalid.
     * Find next available number by setting current number to next available number in array.
     *
     * @return an available number, return -1 if none is available.
     */
    public int get() {
        if (nextNumber[current] == -1) {        // if next available number does not exist
            return -1;
        }

        int out = current;                  // otherwise, return the current available number
        current = nextNumber[current];      // set current number to next available number
        nextNumber[out] = -1;               // set current number used

        return out;
    }

    /**
     * Check if a number is available or not.
     *
     * @param number checking number
     * @return if the number is available or not
     */
    public boolean check(int number) {
        return nextNumber[number] != -1;
    }

    /**
     * Recycle or release a number.
     * Set nextNumber[number] to current index, then move index to number.
     * The index points at the current available number.
     *
     * @param number recycle number
     */
    public void release(int number) {
        if (nextNumber[number] != -1) {
            return;
        }

        nextNumber[number] = current;
        current = number;
    }
}
