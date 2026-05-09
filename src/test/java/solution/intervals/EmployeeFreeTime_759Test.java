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
}
