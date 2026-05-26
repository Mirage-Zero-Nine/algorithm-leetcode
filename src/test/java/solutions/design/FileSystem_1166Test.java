package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FileSystem_1166Test {

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

    @Test
    public void testGetUnknownPathReturnsNegativeOne() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertEquals(-1, fs.get("/missing"));
    }

    @Test
    public void testCreateRequiresLeadingSlash() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertFalse(fs.create("a", 1));
        assertEquals(-1, fs.get("a"));
    }

    @Test
    public void testCreateChildAfterParentExists() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/x", 10));
        assertTrue(fs.create("/x/y", 20));
        assertEquals(20, fs.get("/x/y"));
    }

    @Test
    public void testCreateDuplicatePathFails() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/dup", 1));
        assertFalse(fs.create("/dup", 2));
        assertEquals(1, fs.get("/dup"));
    }

    @Test
    public void testDeepNestedCreation() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/a", 1));
        assertTrue(fs.create("/a/b", 2));
        assertTrue(fs.create("/a/b/c", 3));
        assertTrue(fs.create("/a/b/c/d", 4));
        assertEquals(4, fs.get("/a/b/c/d"));
    }

    @Test
    public void testImplementationAllowsRootPathCreation() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/", 99));
        assertEquals(99, fs.get("/"));
    }

    @Test
    public void testGiantPathSetCreation() {
        FileSystem_1166 fs = new FileSystem_1166();
        assertTrue(fs.create("/n0", 0));
        for (int i = 1; i <= 500; i++) {
            assertTrue(fs.create("/n0/n" + i, i));
        }
        assertEquals(500, fs.get("/n0/n500"));
        assertEquals(-1, fs.get("/n0/n501"));
    }
}
