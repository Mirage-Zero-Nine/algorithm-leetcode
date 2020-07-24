package Solution.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1.
 * Any square sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.
 * Return the maximum possible number of ones that the matrix M can have.
 *
 * @author BorisMirage
 * Time: 2019/09/12 18:29
 * Created with IntelliJ IDEA
 */

public class MaximumNumberOfOnes_1183 {
    /**
     * If creating the first square matrix, the big matrix will be the copies of this one (translation copies).
     * The value of each location in the square matrix will appear at multiple locations in the big matrix.
     * Count the number of locations and sort them.
     * Then assign the ones in the square matrix with more occurrences with 1.
     *
     * @param width      given width of matrix M
     * @param height     given height of matrix M
     * @param sideLength given sub square matrix length
     * @param maxOnes    limit of ones shown in sub matrix
     * @return maximum possible number of ones that the matrix M can have
     */
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {

        /* Corner case */
        if (maxOnes == 0) {
            return 0;
        }

        ArrayList<Integer> l = new ArrayList<>();

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                l.add(((width - i - 1) / sideLength + 1) * ((height - j - 1) / sideLength + 1));
            }
        }

        Collections.sort(l);
        int max = 0;
        int n = l.size() - 1;

        for (int i = 0; i < maxOnes; i++) {
            max += l.get(n - i);        // count max ones from the largest appearance
        }

        return max;
    }
}

