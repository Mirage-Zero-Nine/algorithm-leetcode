package solution.others;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the i-th round, you toggle every i bulb.
 * For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 *
 * @author BorisMirage
 * Time: 2019/06/30 15:27
 * Created with IntelliJ IDEA
 */

public class BulbSwitch_319 {
    /**
     * Nth bulb is on iff n is power of a number.
     * Therefore, from n to 1, there will be sqrt(n) bulbs on.
     *
     * @param n n bulb
     * @return bulbs are on after n rounds
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
