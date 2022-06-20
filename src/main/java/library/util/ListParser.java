package library.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parse integer list (in string format) to actual integer list.
 *
 * @author BorisMirage
 * Time: 2022/06/18 21:32
 * Created with IntelliJ IDEA
 */

public class ListParser {
    private static final Pattern pattern = Pattern.compile("\\[[\\s*\\d+,]+[\\s*\\d+]+]");

    /**
     * Parse 1d integer list string to list.
     *
     * @param listString list in string format
     * @return parsed list
     */
    public static List<Integer> parseList(String listString) {
        if (listString == null || listString.length() < 3) {
            throw new InvalidParameterException("Invalid list: " + listString);
        }

        return Arrays.stream(listString.substring(1, listString.length() - 1).split(","))
                .map(value -> {
                    if (value == null || value.length() == 0) {
                        throw new InvalidParameterException("Invalid integer value: " + value);
                    }
                    return Integer.parseInt(value.trim());
                })
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> parse2dList(String listString) {
        Matcher matcher = pattern.matcher(listString);
        List<List<Integer>> output = new ArrayList<>();
        while (matcher.find()) {
            output.add(parseList(matcher.group()));
        }

        return output;
    }
}
