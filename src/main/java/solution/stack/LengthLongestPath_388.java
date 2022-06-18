package solution.stack;

import java.util.Stack;

/**
 * Given a string representing the file system in the above format.
 * Return the length of the longest absolute path to file in the abstracted file system.
 * If there is no file in the system, return 0.
 * Note:
 * 1. The name of a file contains at least a . and an extension.
 * 2. The name of a directory or sub-directory will not contain a ..
 * 3. Time complexity required: O(n) where n is the size of the input string.
 *
 * @author BorisMirage
 * Time: 2019/06/26 15:54
 * Created with IntelliJ IDEA
 */

public class LengthLongestPath_388 {
    /**
     * Use a stack to store length from root to current file.
     *
     * @param input string representing the file system
     * @return length of the longest absolute path to file
     */
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();      // store length
        stack.push(0);
        int max = 0;

        for (String s : input.split("\n")) {

            int level = s.lastIndexOf("\t") + 1;      // # of \t, representing the level of sub folder
            while (level + 1 < stack.size()) {
                stack.pop();        // pop elements that are under same parent but not in same sub folder
            }
            int length = 0;

            if (!stack.isEmpty()) {     // current sub folder or file path length
                length = stack.peek() + s.length() - level + 1;     // each file path requires a additional "/"
            }
            stack.push(length);
            if (s.contains(".") && !stack.isEmpty()) {      // a valid path requires a file in it
                max = Math.max(max, stack.peek() - 1);      // remove the "/" at the beginning of path
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LengthLongestPath_388 test = new LengthLongestPath_388();
        System.out.println(test.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }
}
