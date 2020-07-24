package Solution.Greedy;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order.
 * Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n.
 * It means between two same tasks, there must be at least n time that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * @author BorisMirage
 * Time: 2020/03/10 21:06
 * Created with IntelliJ IDEA
 */

public class LeastInterval_621 {
    /**
     * Greedy.
     * Find the max number of tasks appeared in given array, and the number of how many tasks at this count.
     * The time should at least be n to complete the tasks, and any time more than n is caused by idle.
     * The idle is caused by interval of same task.
     * Assume there are n tasks, m elements are most frequent element, each has k elements..
     * To finish all tasks, at least k intervals will be create, since same task should be executed after cool down.
     * Each cool down contains n time slot to execute other task, and m - 1 most frequent tasks should be added first.
     * If the n - (m - 1) time slot can contains all other tasks, idle interval created.
     * Therefore, to find the total time consuming, find each idle slot that caused by most frequent element.
     *
     * @param tasks given tasks array
     * @param n     interval between same task
     * @return the least number of intervals the CPU will take to finish all the given tasks
     */
    public int leastInterval(char[] tasks, int n) {
        int max = 0;            // max appearance in array
        int maxCount = 0;       // how many elements with this frequency
        int[] count = new int[26];

        for (char c : tasks) {
            count[c - 'A']++;
            if (max == count[c - 'A']) {
                maxCount++;
            } else if (max < count[c - 'A']) {
                maxCount = 1;
                max++;
            }
        }

        int slots = (max - 1) * (n - (maxCount - 1));           // max - 1: last period will not cause idle
        int availableTasks = tasks.length - max * maxCount;     // number of tasks that can be executed during idle
        int idle = Math.max(0, slots - availableTasks);         // if idle is more than available tasks, then more time required

        return tasks.length + idle;
    }
}
