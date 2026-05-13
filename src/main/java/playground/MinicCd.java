package playground;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author BorisMirage
 * Time: 2025/03/31 18:28
 * Created with IntelliJ IDEA
 */

// command a: /home/example/example1
// command b: ../.././find/../.
// output: /home
public class MinicCd {
    public String minicCd(String path, String command) {
        if (path == null || path.isEmpty()) {
            return "";
        }

        if (command == null || command.isEmpty()) {
            return path;
        }

        Stack<String> stack = new Stack<>();
        var paths = path.split("/");
        var commands = command.split("/");

        Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty()) // Remove empty strings resulting from splitting
                .forEach(stack::push);

        for (String s : commands) {
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!s.equals(".")) {
                stack.push(s);
            }
        }

        Stack<String> pathStack = new Stack<>();
        while (!stack.isEmpty()) {
            pathStack.push(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!pathStack.isEmpty()) {
            sb.append('/').append(pathStack.pop());
        }

        return sb.isEmpty() ? "/" : sb.toString();
    }
}
