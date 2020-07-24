package Lib;

/**
 * Intervals that contain two elements.
 * Start: start value.
 * End: end value.
 */
public class Interval {
    public int start;
    public int end;

    /**
     * Constructor of Interval.
     */
    public Interval() {
        start = 0;
        end = 0;
    }

    /**
     * Overload constructor of Interval with parameter of start and end.
     *
     * @param start start
     * @param end   edn
     */
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
