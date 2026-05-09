package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GetRow119Test {

    private final GetRow_119 test = new GetRow_119();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 3, 3, 1), test.getRow(3));
        assertEquals(List.of(1, 4, 6, 4, 1), test.getRow(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(1), test.getRow(0));
        assertEquals(List.of(1, 1), test.getRow(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(1, 5, 10, 10, 5, 1), test.getRow(5));
    }
}
