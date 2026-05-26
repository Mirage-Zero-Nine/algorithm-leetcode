package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoStitching_1024Test {
    private final VideoStitching_1024 solver = new VideoStitching_1024();

    @Test public void testBasic() {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        assertEquals(3, solver.videoStitching(clips, 10));
    }

    @Test public void testImpossible() {
        int[][] clips = {{0, 1}, {1, 2}};
        assertEquals(-1, solver.videoStitching(clips, 5));
    }

    @Test public void testSingleClip() {
        int[][] clips = {{0, 10}};
        assertEquals(1, solver.videoStitching(clips, 10));
    }

    @Test public void testBacktracking() {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        assertEquals(3, solver.backtracking(clips, 10));
    }

    @Test public void testBacktrackingSingle() {
        int[][] clips = {{0, 10}};
        assertEquals(1, solver.backtracking(clips, 10));
    }

    @Test public void testNoStartAtZero() {
        int[][] clips = {{1, 5}, {3, 7}};
        assertEquals(-1, solver.videoStitching(clips, 7));
    }

    @Test public void testTZero() {
        int[][] clips = {{0, 5}};
        assertEquals(0, solver.videoStitching(clips, 0));
    }

    @Test public void testOverlapping() {
        int[][] clips = {{0, 4}, {2, 8}};
        assertEquals(2, solver.videoStitching(clips, 8));
    }

    @Test public void testManyClips() {
        int[][] clips = {{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}};
        assertEquals(3, solver.videoStitching(clips, 9));
    }

    @Test public void testGapInMiddle() {
        int[][] clips = {{0, 3}, {5, 10}};
        assertEquals(-1, solver.videoStitching(clips, 10));
    }

    @Test public void testGiantCase() {
        int n = 50;
        int[][] clips = new int[n][2];
        for (int i = 0; i < n; i++) {
            clips[i] = new int[]{i, i + 5};
        }
        assertEquals(10, solver.videoStitching(clips, 50));
    }
}
