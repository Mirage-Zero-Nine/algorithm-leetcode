package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleCourse_630Test {
    private final ScheduleCourse_630 solver = new ScheduleCourse_630();

    @Test public void testBasic() {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        assertEquals(3, solver.scheduleCourse(courses));
    }

    @Test public void testSingle() {
        int[][] courses = {{1, 2}};
        assertEquals(1, solver.scheduleCourse(courses));
    }

    @Test public void testOverBudget() {
        int[][] courses = {{5, 3}};  // course takes 5 days, closed day 3
        assertEquals(0, solver.scheduleCourse(courses));
    }

    @Test public void testAllFit() {
        int[][] courses = {{1, 2}, {1, 3}, {1, 4}};
        assertEquals(3, solver.scheduleCourse(courses));
    }

    @Test public void testReplaceLonger() {
        int[][] courses = {{3, 3}, {2, 4}};
        // can take 3,3 first but then 2,4 can't fit. Or take 2,4 first then 3,3 can't fit.
        // Max = 1
        assertEquals(1, solver.scheduleCourse(courses));
    }
}
