package Solution.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.
 * The average score is calculated using integer division.
 *
 * @author BorisMirage
 * Time: 2019/07/17 20:03
 * Created with IntelliJ IDEA
 */

public class HighFive_1086 {
    /**
     * Use a hash map and linked list to store score.
     *
     * @param items given students id and score
     * @return the average score of each student's top five scores in the order of each student's id
     */
    public int[][] highFive(int[][] items) {

        HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();

        for (int[] item : items) {
            if (m.containsKey(item[0])) {
                m.get(item[0]).add(item[1]);
            } else {

                m.put(item[0], new ArrayList<Integer>() {{
                    add(item[1]);
                }});
            }
        }

        int[][] out = new int[m.size()][2];

        for (Integer key : m.keySet()) {
            int ave = 0;

            ArrayList<Integer> l = m.get(key);
            Collections.sort(l);

            for (int i = l.size() - 1; i >= l.size() - 5; i--) {
                ave += l.get(i);
            }
            ave = ave / 5;

            out[key - 1][0] = key;
            out[key - 1][1] = ave;
        }
        return out;
    }
}
