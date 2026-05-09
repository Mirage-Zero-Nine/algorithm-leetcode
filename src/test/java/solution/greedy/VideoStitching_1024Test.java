package solution.greedy;

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
}
