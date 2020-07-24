package Solution.DynamicProgramming;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 * We choose some of the books to place on this shelf, then build another level of shelf of the bookcase.
 * We repeat this process until there are no more books to place.
 * Note again that at each step of the above process, the order of the books is the same as the given sequence of books.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 * @author BorisMirage
 * Time: 2019/09/18 17:15
 * Created with IntelliJ IDEA
 */

public class MinHeightShelves_1105 {
    /**
     * Dynamic programming.
     * There are two possible conditions of placing books:
     * 1. Place current book into a new shelf level: dp[i] = dp[i - 1] + books[i][1]
     * 2. Place book with previous books to previous level, or a new level: dp[i] = Math.min(dp[i], dp[j - 1] + height)
     * height is the book height from (i - 1)th book to the book that can not be fitted into same level.
     * sum(book[j][0], book[j + 1][0], ..., book[1][0]) <= shelf_width.
     *
     * @param books       given books list
     * @param shelf_width limit of level of each shelf
     * @return minimum possible height that the total bookshelf can be after placing shelves
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int width = books[i - 1][0];
            int height = books[i - 1][1];
            dp[i] = dp[i - 1] + height;

            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; --j) {
                height = Math.max(height, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
                width += books[j - 1][0];
            }
        }

        return dp[n];
    }
}
