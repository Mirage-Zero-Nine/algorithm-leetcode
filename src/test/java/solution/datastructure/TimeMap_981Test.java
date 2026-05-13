package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeMap_981Test {

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

    @Test
    public void testGetNonExistentKey() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("a", "val", 1);
        assertEquals("", tm.get("b", 1));
        assertEquals("", tm.get("", 1));
    }

    @Test
    public void testMultipleKeys() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("a", "val_a", 1);
        tm.set("b", "val_b", 2);
        assertEquals("val_a", tm.get("a", 1));
        assertEquals("val_b", tm.get("b", 2));
        assertEquals("", tm.get("b", 1));
    }

    @Test
    public void testOverwriteAtSameTimestamp() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("key", "first", 1);
        tm.set("key", "second", 1);
        assertEquals("second", tm.get("key", 1));
    }

    @Test
    public void testGetExactTimestamp() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("k", "v1", 10);
        tm.set("k", "v2", 20);
        tm.set("k", "v3", 30);
        assertEquals("v1", tm.get("k", 10));
        assertEquals("v2", tm.get("k", 20));
        assertEquals("v3", tm.get("k", 30));
    }

    @Test
    public void testGetBetweenTimestamps() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("k", "v1", 10);
        tm.set("k", "v2", 20);
        assertEquals("v1", tm.get("k", 15));
        assertEquals("v2", tm.get("k", 25));
    }

    @Test
    public void testGetBeforeAnyTimestamp() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("k", "v1", 10);
        assertEquals("", tm.get("k", 5));
        assertEquals("", tm.get("k", 9));
    }

    @Test
    public void testEmptyValueString() {
        TimeMap_981 tm = new TimeMap_981();
        tm.set("k", "", 1);
        assertEquals("", tm.get("k", 1));
        assertEquals("", tm.get("k", 5));
    }

    @Test
    public void testGiantCase() {
        TimeMap_981 tm = new TimeMap_981();
        for (int i = 1; i <= 10000; i++) {
            tm.set("key", "val" + i, i);
        }
        assertEquals("val10000", tm.get("key", 10000));
        assertEquals("val5000", tm.get("key", 5000));
        assertEquals("val1", tm.get("key", 1));
        assertEquals("val9999", tm.get("key", 9999));
        assertEquals("", tm.get("key", 0));
    }
}
