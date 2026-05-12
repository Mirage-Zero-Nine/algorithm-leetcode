package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Generate118Test {

    private final Generate_118 test = new Generate_118();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1)),
            test.generate(5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.generate(0));
        assertEquals(List.of(List.of(1)), test.generate(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.generate(6).size());
    }
}
