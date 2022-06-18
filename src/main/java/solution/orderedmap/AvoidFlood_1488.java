package solution.orderedmap;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Your country has an infinite number of lakes.
 * Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water.
 * If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.
 * Given an integer array rains where:
 * 1. rains[i] > 0 means there will be rains over the rains[i] lake.
 * 2. rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 * 1. ans.length == rains.length
 * 2. ans[i] == -1 if rains[i] > 0.
 * 3. ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * 4. If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 * Notice that if you chose to dry a lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.
 *
 * @author BorisMirage
 * Time: 2020/07/26 16:28
 * Created with IntelliJ IDEA
 */

public class AvoidFlood_1488 {
    /**
     * Keep a tree set to store all the days that can dry water.
     * If the ith day rains at jth lake where it rained before, check the tree set if there exists a element within (i, j).
     * If it exists, remove it from tree set.
     * Otherwise, return empty array since there is no chance to dry the water.
     *
     * @param rains array contains rain at each day
     * @return array contains which lake to dry at ith day, or empty array if there is no chance to do so
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] out = new int[n];

        HashMap<Integer, Integer> m = new HashMap<>();      // key: cell # that ith day has rain, value: ith day
        TreeSet<Integer> dry = new TreeSet<>();           // all the days without raining

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {        // if no rain, then this day could be used to dry water
                dry.add(i);
                out[i] = 1;
            } else {        // if raining, check whether this cell contains water before, then check if it can be empty
                if (m.containsKey(rains[i])) {      // if this cell contains water
                    Integer tmp = dry.ceiling(m.get(rains[i]));       // find if there is a day could empty the water
                    if (tmp == null) {      // return empty array when there is no available day to dry the water
                        return new int[0];
                    }

                    out[tmp] = rains[i];        // use this day to dry the lake at rains[i]
                    dry.remove(tmp);
                }
                m.put(rains[i], i);
                out[i] = -1;
            }
        }

        return out;
    }
}
