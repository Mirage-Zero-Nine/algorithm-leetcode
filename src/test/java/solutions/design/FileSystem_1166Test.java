package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Tests the hash-map file-system implementation with independent state models.
 *
 * <p>The official problem calls the methods {@code createPath} and {@code get}; this
 * repository's adapter exposes the former as {@code create}. Valid LeetCode paths
 * begin with {@code /}, contain lowercase components, and have an existing parent.
 * The implementation also retains the historical sentinel-root behavior for creating
 * {@code /}, which is covered separately as an implementation edge.
 */
public class FileSystem_1166Test {

    @Test
    public void testOfficialSinglePathExample() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/a", 1));
        assertEquals(1, fs.get("/a"));
    }

    @Test
    public void testOfficialNestedPathAndMissingParentExample() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/leet", 1));
        assertTrue(fs.create("/leet/code", 2));
        assertEquals(2, fs.get("/leet/code"));
        assertFalse(fs.create("/c/d", 1));
        assertEquals(-1, fs.get("/c"));
    }

    @Test
    public void testDuplicateCreationPreservesOriginalValue() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/dup", 1));
        assertFalse(fs.create("/dup", 2));
        assertEquals(1, fs.get("/dup"));
    }

    @Test
    public void testSiblingPathsAreIndependent() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/a", 10));
        assertTrue(fs.create("/b", 20));
        assertTrue(fs.create("/a/left", 30));
        assertTrue(fs.create("/a/right", 40));
        assertEquals(10, fs.get("/a"));
        assertEquals(20, fs.get("/b"));
        assertEquals(30, fs.get("/a/left"));
        assertEquals(40, fs.get("/a/right"));
        assertEquals(-1, fs.get("/b/left"));
    }

    @Test
    public void testDeepNestedCreationAndEveryAncestor() {
        FileSystem_1166 fs = new FileSystem_1166();

        String[] paths = {"/a", "/a/b", "/a/b/c", "/a/b/c/d", "/a/b/c/d/e"};
        for (int i = 0; i < paths.length; i++) {
            assertTrue(fs.create(paths[i], i + 1));
        }
        for (int i = 0; i < paths.length; i++) {
            assertEquals(i + 1, fs.get(paths[i]));
        }
    }

    @Test
    public void testCreationRequiresTheImmediateParent() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertFalse(fs.create("/a/b/c", 3));
        assertEquals(-1, fs.get("/a"));
        assertEquals(-1, fs.get("/a/b"));
        assertTrue(fs.create("/a", 1));
        assertFalse(fs.create("/a/b/c", 3));
        assertTrue(fs.create("/a/b", 2));
        assertTrue(fs.create("/a/b/c", 3));
        assertEquals(3, fs.get("/a/b/c"));
    }

    @Test
    public void testPathPrefixIsNotAnExistingParent() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/app", 1));
        assertFalse(fs.create("/apple/child", 2));
        assertFalse(fs.create("/appx/child", 3));
        assertEquals(-1, fs.get("/apple"));
        assertEquals(-1, fs.get("/appx"));
    }

    @Test
    public void testMissingGetsReturnNegativeOneWithoutChangingState() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertEquals(-1, fs.get("/missing"));
        assertEquals(-1, fs.get("/missing/child"));
        assertTrue(fs.create("/missing", 7));
        assertEquals(7, fs.get("/missing"));
        assertEquals(-1, fs.get("/missing/child"));
    }

    @Test
    public void testZeroAndNegativeValuesAreStoredExactly() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/zero", 0));
        assertTrue(fs.create("/negative", -7));
        assertTrue(fs.create("/negative/child", -1));
        assertEquals(0, fs.get("/zero"));
        assertEquals(-7, fs.get("/negative"));
        assertEquals(-1, fs.get("/negative/child"));
        assertFalse(fs.create("/negative/child", 8));
        assertEquals(-1, fs.get("/negative/child"));
    }

    @Test
    public void testIntegerBoundaryValues() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/min", Integer.MIN_VALUE));
        assertTrue(fs.create("/max", Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, fs.get("/min"));
        assertEquals(Integer.MAX_VALUE, fs.get("/max"));
    }

    @Test
    public void testCreateRejectsPathWithoutLeadingSlash() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertFalse(fs.create("a", 1));
        assertFalse(fs.create("a/b", 2));
        assertEquals(-1, fs.get("a"));
        assertEquals(-1, fs.get("a/b"));
    }

    @Test
    public void testHistoricalRootSentinelCanBeCreatedAndRead() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertEquals(-1, fs.get("/"));
        assertTrue(fs.create("/", 99));
        assertEquals(99, fs.get("/"));
        assertFalse(fs.create("/", 100));
    }

    @Test
    public void testRootSentinelStillAllowsTopLevelPaths() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/", 1));
        assertTrue(fs.create("/child", 2));
        assertTrue(fs.create("/another", 3));
        assertEquals(1, fs.get("/"));
        assertEquals(2, fs.get("/child"));
        assertEquals(3, fs.get("/another"));
    }

    @Test
    public void testFailedCreateDoesNotPreventLaterCreation() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertFalse(fs.create("/parent/child", 1));
        assertTrue(fs.create("/parent", 2));
        assertTrue(fs.create("/parent/child", 3));
        assertTrue(fs.create("/parent/child/grandchild", 4));
        assertFalse(fs.create("/parent/child/grandchild", 5));
        assertEquals(4, fs.get("/parent/child/grandchild"));
    }

    @Test
    public void testComponentBoundariesRemainDistinct() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/a", 1));
        assertTrue(fs.create("/aa", 2));
        assertTrue(fs.create("/a/ab", 3));
        assertFalse(fs.create("/a/abc/grandchild", 4));
        assertEquals(1, fs.get("/a"));
        assertEquals(2, fs.get("/aa"));
        assertEquals(3, fs.get("/a/ab"));
    }

    @Test
    public void testSameInstanceCanInterleaveBranchesAndGets() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/left", 1));
        assertEquals(1, fs.get("/left"));
        assertTrue(fs.create("/right", 2));
        assertEquals(-1, fs.get("/left/missing"));
        assertTrue(fs.create("/left/nested", 3));
        assertEquals(2, fs.get("/right"));
        assertEquals(3, fs.get("/left/nested"));
    }

    @Test
    public void testSeparateInstancesDoNotSharePaths() {
        FileSystem_1166 first = new FileSystem_1166();
        FileSystem_1166 second = new FileSystem_1166();

        assertTrue(first.create("/sharedname", 11));
        assertEquals(11, first.get("/sharedname"));
        assertEquals(-1, second.get("/sharedname"));
        assertTrue(second.create("/sharedname", 22));
        assertEquals(22, second.get("/sharedname"));
        assertEquals(11, first.get("/sharedname"));
    }

    @Test
    public void testMaximumPathLengthBoundary() {
        FileSystem_1166 fs = new FileSystem_1166();
        String first = "/" + "a".repeat(99);
        String second = "/a/" + "b".repeat(97);

        assertEquals(100, first.length());
        assertEquals(100, second.length());
        assertTrue(fs.create("/a", 0));
        assertTrue(fs.create(first, 1));
        assertTrue(fs.create(second, 2));
        assertEquals(0, fs.get("/a"));
        assertEquals(1, fs.get(first));
        assertEquals(2, fs.get(second));
    }

    @Test
    public void testWideSetOfLowercaseSiblingPaths() {
        FileSystem_1166 fs = new FileSystem_1166();

        assertTrue(fs.create("/root", 0));
        for (int i = 0; i < 500; i++) {
            String child = "/root/" + lowerCaseName(i);
            assertTrue(fs.create(child, i));
            assertEquals(i, fs.get(child));
        }
        assertEquals(-1, fs.get("/root/zzzzzzzzzz"));
    }

    @Test
    public void testSeededOperationSequenceMatchesIndependentMapOracle() {
        FileSystem_1166 fs = new FileSystem_1166();
        Map<String, Integer> oracle = new HashMap<>();
        oracle.put("", -1);
        String[] paths = {
            "/a", "/b", "/a/x", "/a/y", "/b/x", "/a/x/i", "/b/x/j",
            "/missing/child", "/a/x/i/deep", "/b/y", "/a/y/z", "/z"
        };
        Random random = new Random(1166L);

        for (int i = 0; i < 800; i++) {
            String path = paths[random.nextInt(paths.length)];
            if (random.nextBoolean()) {
                int value = random.nextInt(2_000_001) - 1_000_000;
                assertEquals(oracleCreate(oracle, path, value), fs.create(path, value),
                    "create operation " + i + " for " + path);
            } else {
                assertEquals(oracleGet(oracle, path), fs.get(path),
                    "get operation " + i + " for " + path);
            }
        }

        for (String path : paths) {
            assertEquals(oracleGet(oracle, path), fs.get(path), "final value for " + path);
        }
    }

    @Test
    public void testSeededIndependentOracleWithManyGeneratedPaths() {
        FileSystem_1166 fs = new FileSystem_1166();
        Map<String, Integer> oracle = new HashMap<>();
        oracle.put("", -1);
        Random random = new Random(20261166L);
        String[] roots = {"/alpha", "/beta", "/gamma", "/delta"};

        for (String root : roots) {
            assertEquals(oracleCreate(oracle, root, root.length()), fs.create(root, root.length()));
        }
        for (int i = 0; i < 700; i++) {
            String root = roots[random.nextInt(roots.length)];
            String path = root + "/" + lowerCaseName(random.nextInt(140));
            int value = random.nextInt(100_001);
            assertEquals(oracleCreate(oracle, path, value), fs.create(path, value));
            assertEquals(oracleGet(oracle, path), fs.get(path));
        }
        for (Map.Entry<String, Integer> entry : oracle.entrySet()) {
            if (!entry.getKey().isEmpty()) {
                assertEquals(entry.getValue(), fs.get(entry.getKey()));
            }
        }
    }

    @Test
    public void testMaximumTenThousandCallWorkload() {
        FileSystem_1166 fs = new FileSystem_1166();
        String[] paths = new String[5_000];
        paths[0] = "/a";
        assertTrue(fs.create(paths[0], 0));
        for (int i = 1; i < paths.length; i++) {
            paths[i] = "/a/" + lowerCaseName(i);
            assertTrue(fs.create(paths[i], i));
        }
        for (int i = 0; i < paths.length; i++) {
            assertEquals(i, fs.get(paths[i]));
        }
    }

    private static boolean oracleCreate(Map<String, Integer> oracle, String path, int value) {
        if (!path.startsWith("/")) {
            return false;
        }
        String parent = path.substring(0, path.lastIndexOf('/'));
        if (!oracle.containsKey(parent) || oracle.containsKey(path)) {
            return false;
        }
        oracle.put(path, value);
        return true;
    }

    private static int oracleGet(Map<String, Integer> oracle, String path) {
        return oracle.getOrDefault(path, -1);
    }

    private static String lowerCaseName(int value) {
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) ('a' + value % 26));
            value = value / 26 - 1;
        } while (value >= 0);
        return result.reverse().toString();
    }
}
