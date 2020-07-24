package Solution.Math;

/**
 * You are playing the following Nim Game with your friend:
 * There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones.
 * The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
 * Both of you are very clever and have optimal strategies for the game.
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 *
 * @author BorisMirage
 * Time: 2019/09/16 16:23
 * Created with IntelliJ IDEA
 */

public class CanWinNim_292 {
    /**
     * The first one who got the number that is multiple of 4 (i.e. n % 4 == 0) will lost, otherwise he/she will win.
     * Base case: n = 4, the second player would always be able to pick the remaining number.
     * For 4 < n < 8, the first player can reduce the initial number into 4 accordingly and win this game.
     * i.e. The numbers 5, 6, 7 are winning numbers for any player who got it first.
     * Now to the beginning of the next cycle, n = 8.
     * No matter which number that the first player picks, it would always leave the winning numbers (5, 6, 7) to the second player.
     * Following the second case, numbers between 8 and 12 are winning numbers for the first player again.
     * Because the first player can always reduce the number into the death number 8.
     *
     * @param n given start number
     * @return whether you can win the game given the number of stones in the heap
     */
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}
