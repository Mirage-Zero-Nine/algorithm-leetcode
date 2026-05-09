package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FileSystem1166Test {

    @Test
    public void testHappyCases() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/a", 1));
        assertEquals(1, fs.get("/a"));
        assertTrue(fs.create("/a/b", 2));
        assertEquals(2, fs.get("/a/b"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertFalse(fs.create("/a/b", 1));
        assertEquals(-1, fs.get("/nonexistent"));
        assertTrue(fs.create("/a", 1));
        assertFalse(fs.create("/a", 2));
    }

    @Test
    public void testLargeCase() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/leet", 1));
        assertTrue(fs.create("/leet/code", 2));
        assertFalse(fs.create("/c/d", 1));
        assertEquals(2, fs.get("/leet/code"));
    }
}
