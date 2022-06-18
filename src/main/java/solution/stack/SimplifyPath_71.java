package solution.stack;


import java.util.*;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * Or in other words, convert it to the canonical path.
 * Note that the returned canonical path must always begin with a slash /.
 * And there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing /.
 * Also, the canonical path must be the shortest string representing the absolute path.
 *
 * @author BorisMirage
 * Time: 2019/06/25 18:26
 * Created with IntelliJ IDEA
 */

public class SimplifyPath_71 {
    /**
     * Spilt string by "/" and push the valid path to stack.
     * If meets "..", pop the last element in stack. If meets "." or ""(stands for "//"), ignore it and continue.
     *
     * @param path given path string
     * @return shortest string representing the absolute path
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder out = new StringBuilder();

        for (String s : path.split("/")) {      // split path string by "/", "/" will not show in spilt string

            if (s.equals("src/main") && !stack.isEmpty()) {       // one folder back in path
                stack.pop();
            } else if (!s.equals("src/main") && !s.equals(".") && !s.equals("")) {        // find valid folder path
                stack.push(s);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }
        for (String s : stack) {
            out.append("/").append(s);
        }

        return out.toString();
    }

    public static void main(String[] args) {
        SimplifyPath_71 test = new SimplifyPath_71();
        System.out.println(test.simplifyPath("/a/./b/../../c/"));       // /c
        System.out.println(test.simplifyPath("/a/../../b/../c//.//"));      // /c
        System.out.println(test.simplifyPath("/a//b////c/d//././/.."));     // /a/b/c
        System.out.println(test.simplifyPath("/home/"));        // /home
        System.out.println(test.simplifyPath("/home//foo/"));       // /home/foo
        System.out.println(test.simplifyPath("/../"));      // /
    }
}
