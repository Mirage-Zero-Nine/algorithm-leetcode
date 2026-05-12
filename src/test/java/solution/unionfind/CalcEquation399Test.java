package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CalcEquation399Test {

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
}
