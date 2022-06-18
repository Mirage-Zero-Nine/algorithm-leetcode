package solution.others;

import java.util.*;

/**
 * Given an array of strings names of size n.
 * You will create n folders in your file system, at the ith minute, you will create a folder with the name names[i].
 * Since two files cannot have the same name, the system will have a suffix addition to its name in the form of (k).
 * k is the smallest positive integer such that the obtained name remains unique.
 * Return an array of strings where ans[i] is the actual name the system assign to the ith folder when you create it.
 *
 * @author BorisMirage
 * Time: 2020/06/20 19:35
 * Created with IntelliJ IDEA
 */

public class GetFolderNames_1487 {
    /**
     * Keep a hash map to count the appearance of key.
     * Keep a hash set to store the names has been added to output to avoid duplicate folder name.
     * Iterate each name in given array.
     * If the name itself has not been added, add it to set and add to hash map with count of 1.
     * If it has been added, then find the previous count and then try to find the first unused name to assign.
     *
     * @param names given names
     * @return an array of strings where ans[i] is the actual name the system assign to the ith folder
     */
    public String[] getFolderNames(String[] names) {
        HashMap<String, Integer> m = new HashMap<>();       // key is the original name, value is the appearance of key
        HashSet<String> set = new HashSet<>();              // store the created folder name to avoid duplicate
        String[] out = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            String current = names[i];
            if (!m.containsKey(current)) {
                m.put(current, 1);
            }

            if (set.add(current)) {
                out[i] = current;
            } else {
                StringBuilder sb = new StringBuilder();
                int count = m.get(current);       // get the previous frequency
                sb.append(current).append("(").append(count).append(")");

                while (set.contains(sb.toString())) {       // try to find non-duplicated name to assign
                    sb.deleteCharAt(sb.length() - 1);
                    while (Character.isDigit(sb.charAt(sb.length() - 1))) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.append(++count).append(")");
                }

                m.put(current, count);
                set.add(sb.toString());
                out[i] = sb.toString();
            }
        }

        return out;
    }
}
