package solution.greedy;

/**
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * For each move, you could choose m washing machines, pass one dress to one of its adjacent washing machines.
 * Given an integer array representing the number of dresses in each washing machine from left to right on the line.
 * Find the minimum number of moves to make all the washing machines have the same number of dresses.
 * If it is not possible to do it, return -1.
 *
 * @author BorisMirage
 * Time: 2020/03/15 11:39
 * Created with IntelliJ IDEA
 */

public class FindMinMoves_517 {
    /**
     * Greedy.
     * Note that all machines with at least one dress can pass one dress to its adjacent machine each time.
     * One machine can only pass one dress each time, but all machines with dresses can pass dresses.
     * For instance, [1, -2, 1] takes only one step, and [-1, 2 ,-1] takes two steps.
     * If there is one machine requires clothes, then it can only obtained from its adjacent machine.
     * Hence, the steps can be calculated by (previous dress needed + current dress having).
     *
     * @param machines given machines
     * @return minimum number of moves to make all the washing machines have the same number of dresses
     */
    public int findMinMoves(int[] machines) {

        /* Corner case */
        if (machines == null || machines.length == 0) {
            return -1;
        }

        int total = 0;
        for (int dresses : machines) {
            total += dresses;
        }

        if (total % machines.length != 0) {     // all machines should finally contains same number of dresses
            return -1;
        }

        int average = total / machines.length, required = 0, steps = 0;

        for (int dresses : machines) {
            required += dresses - average;          // how many dresses current machine needed
            steps = Math.max(Math.max(steps, Math.abs(required)), dresses - average);
        }

        return steps;
    }

    public static void main(String[] args) {
        System.out.println(new FindMinMoves_517().findMinMoves(new int[]{0, 0, 11, 5}));
    }
}
