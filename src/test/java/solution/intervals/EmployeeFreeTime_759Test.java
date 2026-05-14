package solution.intervals;

import library.Interval;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeFreeTime_759Test {
    private final EmployeeFreeTime_759 solver = new EmployeeFreeTime_759();

    @Test public void testBasic() {
        List<List<Interval>> schedule = Arrays.asList(
                Arrays.asList(new Interval(1, 2), new Interval(5, 6)),
                Collections.singletonList(new Interval(1, 3)),
                Collections.singletonList(new Interval(4, 10))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertEquals(1, res.size());
        assertEquals(3, res.get(0).start);
        assertEquals(4, res.get(0).end);
    }

    @Test public void testTwoGaps() {
        List<List<Interval>> schedule = Arrays.asList(
                Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
                Collections.singletonList(new Interval(2, 4)),
                Arrays.asList(new Interval(2, 5), new Interval(9, 12))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertEquals(2, res.size());
        assertEquals(5, res.get(0).start);
        assertEquals(6, res.get(0).end);
        assertEquals(7, res.get(1).start);
        assertEquals(9, res.get(1).end);
    }

    @Test public void testNoFreeTime() {
        List<List<Interval>> schedule = Arrays.asList(
                Collections.singletonList(new Interval(0, 10)),
                Collections.singletonList(new Interval(0, 10))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertTrue(res.isEmpty());
    }

    @Test public void testEmpty() {
        List<Interval> res = solver.employeeFreeTime(Collections.emptyList());
        assertTrue(res.isEmpty());
    }

    @Test public void testNull() {
        List<Interval> res = solver.employeeFreeTime(null);
        assertTrue(res.isEmpty());
    }

    @Test public void testSingleEmployee() {
        List<List<Interval>> schedule = Collections.singletonList(
                Arrays.asList(new Interval(1, 3), new Interval(5, 7))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertEquals(1, res.size());
        assertEquals(3, res.get(0).start);
        assertEquals(5, res.get(0).end);
    }

    @Test public void testAdjacentIntervals() {
        // [1,3] and [3,5] touch at 3, so no free time between them
        List<List<Interval>> schedule = Arrays.asList(
                Collections.singletonList(new Interval(1, 3)),
                Collections.singletonList(new Interval(3, 5))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertTrue(res.isEmpty());
    }

    @Test public void testMultipleGaps() {
        List<List<Interval>> schedule = Arrays.asList(
                Arrays.asList(new Interval(1, 2), new Interval(5, 6), new Interval(9, 10)),
                Arrays.asList(new Interval(3, 4), new Interval(7, 8))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertEquals(4, res.size());
        assertEquals(2, res.get(0).start);
        assertEquals(3, res.get(0).end);
    }

    @Test public void testAllOverlapping() {
        List<List<Interval>> schedule = Arrays.asList(
                Collections.singletonList(new Interval(1, 10)),
                Collections.singletonList(new Interval(2, 8)),
                Collections.singletonList(new Interval(3, 7))
        );
        List<Interval> res = solver.employeeFreeTime(schedule);
        assertTrue(res.isEmpty());
    }

    @Test public void testGiantCase() {
        int size = 500;
        List<Interval> emp1 = new java.util.ArrayList<>();
        List<Interval> emp2 = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) {
            emp1.add(new Interval(i * 4, i * 4 + 1));
            emp2.add(new Interval(i * 4 + 2, i * 4 + 3));
        }
        List<List<Interval>> schedule = Arrays.asList(emp1, emp2);
        List<Interval> res = solver.employeeFreeTime(schedule);
        // Gaps between each pair: [i*4+1, i*4+2] and [i*4+3, (i+1)*4]
        assertTrue(res.size() > 0);
    }
}
