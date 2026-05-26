package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CalcEquation_399Test {

    @Test
    public void testHappyCases() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b"), List.of("b", "c")),
            new double[]{2.0, 3.0},
            List.of(List.of("a", "c"), List.of("b", "a"))
        );
        assertEquals(6.0, result[0], 0.0001);
        assertEquals(0.5, result[1], 0.0001);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b")),
            new double[]{2.0},
            List.of(List.of("a", "e"), List.of("a", "a"))
        );
        assertEquals(-1.0, result[0], 0.0001);
        assertEquals(1.0, result[1], 0.0001);
    }

    @Test
    public void testLargeCase() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("c", "d")),
            new double[]{2.0, 3.0, 4.0},
            List.of(List.of("a", "d"), List.of("d", "a"))
        );
        assertEquals(24.0, result[0], 0.0001);
        assertEquals(1.0 / 24.0, result[1], 0.0001);
    }

    @Test
    public void testSelfDivision() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("x", "y")),
            new double[]{5.0},
            List.of(List.of("x", "x"), List.of("y", "y"))
        );
        assertEquals(1.0, result[0], 0.0001);
        assertEquals(1.0, result[1], 0.0001);
    }

    @Test
    public void testUnknownVariable() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b")),
            new double[]{2.0},
            List.of(List.of("z", "a"), List.of("z", "z"))
        );
        assertEquals(-1.0, result[0], 0.0001);
        assertEquals(-1.0, result[1], 0.0001);
    }

    @Test
    public void testDisconnectedComponents() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b"), List.of("c", "d")),
            new double[]{2.0, 3.0},
            List.of(List.of("a", "d"), List.of("c", "b"))
        );
        assertEquals(-1.0, result[0], 0.0001);
        assertEquals(-1.0, result[1], 0.0001);
    }

    @Test
    public void testTransitiveChain() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("c", "d"), List.of("d", "e")),
            new double[]{2.0, 3.0, 4.0, 5.0},
            List.of(List.of("a", "e"))
        );
        assertEquals(120.0, result[0], 0.0001);
    }

    @Test
    public void testReverseDivision() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(List.of("a", "b")),
            new double[]{3.0},
            List.of(List.of("b", "a"))
        );
        assertEquals(1.0 / 3.0, result[0], 0.0001);
    }

    @Test
    public void testEmptyEquations() {
        CalcEquation_399 test = new CalcEquation_399();
        double[] result = test.calcEquation(
            List.of(),
            new double[]{},
            List.of(List.of("a", "b"))
        );
        assertArrayEquals(new double[]{}, result);
    }

    @Test
    public void testGiantCase() {
        // Chain: x0/x1=2, x1/x2=2, ..., x(n-1)/xn=2 => x0/xn = 2^n
        CalcEquation_399 test = new CalcEquation_399();
        int n = 20;
        List<List<String>> equations = new ArrayList<>();
        double[] values = new double[n];
        for (int i = 0; i < n; i++) {
            equations.add(List.of("x" + i, "x" + (i + 1)));
            values[i] = 2.0;
        }
        double[] result = test.calcEquation(equations, values, List.of(List.of("x0", "x" + n)));
        assertEquals(Math.pow(2, n), result[0], 0.0001);
    }
}
