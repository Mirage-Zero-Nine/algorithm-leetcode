package solution.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * You are asked to design a file system which provides two functions:
 * - create(path, value): Creates a new path and associates a value to it if possible and returns True.
 * Returns False if the path already exists or its parent path doesn't exist.
 * - get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 * Implement the two functions.
 *
 * @author BorisMirage
 * Time: 2019/08/26 16:00
 * Created with IntelliJ IDEA
 */

public class FileSystem_1166 {

    private final Map<String, Integer> m = new HashMap<>();

    /**
     * Initialization of class.
     * Use a hash map to store the path and value.
     */
    public FileSystem_1166() {
        m.put("", -1);      // avoid initially when path is "/a" regarded as false
    }

    /**
     * Creates a new path and associates a value to it if possible and returns True.
     * The valid path's parent is the path before the last "/".
     * Hence, check parent and then put the path into map if it is valid.
     * If the path has already exist, return false.
     *
     * @param path  given path
     * @param value given value
     * @return true to create a new path with value, false if the path already exists or its parent path doesn't exist
     */
    public boolean create(String path, int value) {
        if (path.charAt(0) != '/') {
            return false;
        }
        String parent = path.substring(0, path.lastIndexOf("/"));
        if (!m.containsKey(parent)) {
            return false;
        }

        return m.putIfAbsent(path, value) == null;      // if the path exist, m.putIfAbsent(path, value) will be null
    }

    public int get(String path) {
        return m.getOrDefault(path, -1);
    }
}
