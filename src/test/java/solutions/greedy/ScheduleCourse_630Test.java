package solutions.greedy;

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
        assertEquals(1, solver.scheduleCourse(courses));
    }

    @Test public void testEmpty() {
        int[][] courses = {};
        assertEquals(0, solver.scheduleCourse(courses));
    }

    @Test public void testTwoCoursesExactFit() {
        int[][] courses = {{2, 5}, {3, 5}};
        assertEquals(2, solver.scheduleCourse(courses));
    }

    @Test public void testAllOverBudget() {
        int[][] courses = {{10, 5}, {20, 3}, {15, 2}};
        assertEquals(0, solver.scheduleCourse(courses));
    }

    @Test public void testSameDeadline() {
        int[][] courses = {{1, 10}, {2, 10}, {3, 10}, {4, 10}};
        // Take 1+2+3+4=10 exactly fits deadline 10
        assertEquals(4, solver.scheduleCourse(courses));
    }

    @Test public void testGiantCase() {
        int n = 1000;
        int[][] courses = new int[n][2];
        for (int i = 0; i < n; i++) {
            courses[i] = new int[]{1, n};
        }
        assertEquals(n, solver.scheduleCourse(courses));
    }
    @Test public void testAdditionalExact() { assertEquals(1, solver.scheduleCourse(new int[][]{{2, 2}})); }
    @Test public void testAdditionalTwoFit() { assertEquals(2, solver.scheduleCourse(new int[][]{{1, 2}, {2, 3}})); }
    @Test public void testAdditionalReplace() { assertEquals(2, solver.scheduleCourse(new int[][]{{5, 5}, {2, 2}, {2, 4}})); }
    @Test public void testAdditionalDeadline() { assertEquals(3, solver.scheduleCourse(new int[][]{{1, 3}, {1, 3}, {1, 3}})); }
    @Test public void testAdditionalLate() { assertEquals(2, solver.scheduleCourse(new int[][]{{2, 2}, {3, 10}, {5, 5}})); }
    @Test public void testAdditionalOne() { assertEquals(1, solver.scheduleCourse(new int[][]{{10, 10}})); }
    @Test public void testAdditionalImpossible() { assertEquals(0, solver.scheduleCourse(new int[][]{{2, 1}, {3, 2}})); }
    @Test public void testAdditionalFour() { assertEquals(4, solver.scheduleCourse(new int[][]{{1, 10}, {1, 10}, {1, 10}, {1, 10}})); }
    @Test public void testAdditionalOrder() { assertEquals(2, solver.scheduleCourse(new int[][]{{3, 4}, {1, 2}, {2, 5}})); }
    @Test public void testAdditionalZeroDuration() { assertEquals(2, solver.scheduleCourse(new int[][]{{0, 0}, {1, 1}})); }
}
