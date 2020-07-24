package Playground;


/**
 * @author BorisMirage
 * Time: 2018/03/26 11:40
 * Created with IntelliJ IDEA
 */

public class MergeTwoSortedArray {

    public int[] MergeTwoSortedArray(int[] a1, int[] a2) {

        if (a1.length == 0) {
            return a2;
        }
        if (a2.length == 0) {
            return a1;
        }

        int i1 = 0, i2 = 0;
        int[] mergeArray = new int[a1.length + a2.length];

        for (int i = 0; i < mergeArray.length; i++) {

            if (i1 == a1.length) {
                mergeArray[i] = a2[i2];
                i2 += 1;
            } else if (i2 == a2.length) {
                mergeArray[i] = a1[i1];
                i1 += 1;
            } else {
                mergeArray[i] = Math.min(a1[i1], a2[i2]);
                if (mergeArray[i] == a1[i1]) {
                    i1 += 1;
                } else {
                    i2 += 1;
                }
            }
        }
        return mergeArray;
    }
}
