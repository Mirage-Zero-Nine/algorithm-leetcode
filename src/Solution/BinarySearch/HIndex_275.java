package Solution.BinarySearch;

/**
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher.
 * Write a function to compute the researcher's h-index.
 *
 * @author BorisMirage
 * Time: 2019/06/22 15:37
 * Created with IntelliJ IDEA
 */

public class HIndex_275 {
    /**
     * Binary search.
     * 1. N papers having at least N citations -> return N.
     * 2. N papers that have more than N citations -> search right part as N should be larger.
     * 3. N papers that have less than N citations -> search left part as N should be smaller.
     *
     * @param citations given citations array
     * @return H-index
     */
    public int hIndex(int[] citations) {

        /* Corner case */
        if (citations.length < 1) {
            return 0;
        }

        int left = 0, right = citations.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (citations[mid] == citations.length - mid) {
                return citations[mid];       // N papers having at least N citations
            } else if (citations[mid] > citations.length - mid) {       //  N papers that have more than N citations
                right = mid - 1;        // searching in the left half
            } else {        // N papers that have less than N citations
                left = mid + 1;
            }
        }

        return citations.length - (right + 1);      // find largest citation paper
    }
}
