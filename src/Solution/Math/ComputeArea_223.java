package Solution.Math;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *
 * @author BorisMirage
 * Time: 2019/07/06 16:25
 * Created with IntelliJ IDEA
 */

public class ComputeArea_223 {
    /**
     * Find four point of overlap rectangle (if exist), and remove this area.
     * Four point is found by compare each rectangle's coord and obtain the "centrally" one.
     * Left and bottom should be larger to be closer to "center".
     * Right and top should be smaller to be closer to "center".
     *
     * @param A bottom left corner x for first rectangle
     * @param B bottom left corner y for first rectangle
     * @param C top right corner x for first rectangle
     * @param D top right corner y for first rectangle
     * @param E bottom left corner x for second rectangle
     * @param F bottom left corner y for second rectangle
     * @param G top right corner x for second rectangle
     * @param H top right corner y for second rectangle
     * @return total area of rectangles
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int area1 = (C - A) * (D - B);      // area of first rectangle
        int area2 = (G - E) * (H - F);      // area of second rectangle

        int left = Math.max(A, E);      // left and bottom should be larger to be closer to "center"
        int right = Math.min(G, C);     // right and top should be smaller to be closer to "center"
        int bottom = Math.max(F, B);
        int top = Math.min(D, H);

        int overlap = 0;
        if (right > left && top > bottom) {     // found overlap
            overlap = (right - left) * (top - bottom);
        }

        return area1 + area2 - overlap;
    }
}
