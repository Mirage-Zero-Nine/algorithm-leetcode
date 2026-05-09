package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeMap981Test {

    @Test
    public void testHappyCases() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("foo", "bar", 1);
        assertEquals("bar", tm.get("foo", 1));
        assertEquals("bar", tm.get("foo", 3));
        tm.set("foo", "bar2", 4);
        assertEquals("bar2", tm.get("foo", 4));
        assertEquals("bar2", tm.get("foo", 5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TimeMap_981 tm = new TimeMap_981();
        assertEquals("", tm.get("foo", 1));
        tm.set("foo", "bar", 5);
        assertEquals("", tm.get("foo", 3));
    }

    @Test
    public void testLargeCase() {
        TimeMap_981 tm = new TimeMap_981();
        for (int i = 1; i <= 10; i++) tm.set("key", "val" + i, i);
        assertEquals("val10", tm.get("key", 10));
        assertEquals("val5", tm.get("key", 5));
    }
}
