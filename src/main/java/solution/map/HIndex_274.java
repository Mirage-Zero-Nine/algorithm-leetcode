package solution.map;

/**
 * Given an array of citations (non-negative) of a researcher, write a function to compute the researcher's h-index.
 *
 * @author BorisMirage
 * Time: 2019/06/22 15:15
 * Created with IntelliJ IDEA
 */

public class HIndex_274 {
    /**
     * Bucket sorting to reduce memory consumption comparing to hash map.
     * Sort the array descending order, give each a index start from `1`.
     * From right to left, find the `last` number `>=` its index, the result is its index.
     * At least n papers has n citations, therefore, create a new int array from 0 to n.
     * The index of array is the paper with # of citation, value is how many paper has this # of citations.
     * If there is a paper that has larger citations of n, then put at the end of array.
     * The reason is that "at least n papers has n citations" makes H-index at most n.
     *
     * @param citations given citations array
     * @return H-index
     */
    public int hIndex(int[] citations) {


        int[] buckets = new int[citations.length + 1];

        for (int c : citations) {
            if (c >= citations.length) {
                buckets[citations.length]++;
            } else {
                buckets[c]++;
            }
        }

        int count = 0;
        for (int i = citations.length; i > -1; i--) {
            count += buckets[i];
            if (count >= i) {       // at least N papers has N citations
                return i;       // found highest H-index
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        HIndex_274 test = new HIndex_274();
        System.out.println(test.hIndex(new int[]{1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8, 9, 10, 11}));
    }
}

