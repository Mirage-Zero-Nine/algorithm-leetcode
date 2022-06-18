package solution.binarysearch;

import library.VersionControl;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one.
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * @author BorisMirage
 * Time: 2019/06/22 16:16
 * Created with IntelliJ IDEA
 */

public class FirstBadVersion_278 extends VersionControl {
    /**
     * Binary search.
     * If middle one is good version, then first bad version is at left part.
     * Otherwise, it is at right part and may start at the middle one.
     *
     * @param n n versions from 1 to n
     * @return first bad version
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (!isBadVersion(mid)) {       // good version
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
