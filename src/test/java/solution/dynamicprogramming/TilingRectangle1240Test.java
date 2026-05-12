package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TilingRectangle1240Test {

    @Test
    public void testHappyCases() {
        assertEquals(3, new TilingRectangle_1240().tilingRectangle(2, 3));
        assertEquals(5, new TilingRectangle_1240().tilingRectangle(5, 8));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(1, 1));
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(3, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, new TilingRectangle_1240().tilingRectangle(11, 13));
    }
}
