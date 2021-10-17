package Solution.Math;

/**
 * You are given a large sample of integers in the range [0, 255].
 * Since the sample is so large, it is represented by an array where count[k] is the number of times that k appears in the sample.
 * Calculate the following statistics:
 * - minimum: The minimum element in the sample.
 * - maximum: The maximum element in the sample.
 * - mean: The average of the sample, calculated as the total sum of all elements divided by the total number of elements.
 * - median:
 * 1. If the sample has an odd number of elements, then the median is the middle element once the sample is sorted.
 * 2. If the sample has an even number of elements, then the median is the average of the two middle elements once the sample is sorted.
 * - mode: The number that appears the most in the sample. It is guaranteed to be unique.
 * Return the statistics of the sample as an array of floating-point numbers [minimum, maximum, mean, median, mode].
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * @author BorisMirage
 * Time: 2021/10/17 14:03
 * Created with IntelliJ IDEA
 */

public class SampleStats_1093 {
    /**
     * Two loops.
     * First loop find the min, max, average and mode of the array.
     * Second loop to find the median of the array by finding two candidates of median: (total + 1) / 2, total / 2 + 1.
     *
     * @param count given array
     * @return the statistics of the sample as an array of floating-point numbers [minimum, maximum, mean, median, mode]
     */
    public double[] sampleStats(int[] count) {

        /* Corner case */
        if (count == null || count.length == 0) {
            return new double[5];
        }

        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, mode = 0, mean, median;
        int frequency = 0;
        long totalElements = 0, sum = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                min = Math.min(i, min);
                max = Math.max(i, max);
                sum += (long) count[i] * i;
                if (count[i] > frequency) {
                    frequency = count[i];
                    mode = i;
                }
                totalElements += count[i];
            }
        }

        mean = sum / (double) totalElements;
        median = findMedian(count, totalElements, sum);

        return new double[]{min, max, mean, median, mode};
    }

    /**
     * Find median from given array.
     * The idea is to find the two possible candidate of median: (total + 1) / 2 and total / 2 + 1.
     * If the number of elements in array is even, then (total + 1) / 2 < total / 2 + 1.
     * If the number of elements in array is odd, then (total + 1) / 2 == total / 2 + 1.
     * Traverse the array and find the value of two candidate of median.
     *
     * @param count         given array
     * @param totalElements number of elements in given array
     * @param sum           sum of all the elements in the array
     * @return median from the given array
     */
    private double findMedian(int[] count, long totalElements, long sum) {
        int middle1 = (int) ((totalElements + 1) / 2), middle2 = (int) (totalElements / 2 + 1), currentCountSum = 0;
        double median = totalElements == 1 ? sum : 0; // if there is only one element, then the median is the only element

        for (int i = 0; totalElements > 1 && i < count.length; i++) {
            int next = currentCountSum + count[i];
            if (currentCountSum < middle1 && next >= middle1) { // if middle is under current element
                median += i / 2d;
            }
            if (currentCountSum < middle2 && next >= middle2) {
                median += i / 2d;
            }
            currentCountSum += count[i];
        }

        return median;
    }
}
