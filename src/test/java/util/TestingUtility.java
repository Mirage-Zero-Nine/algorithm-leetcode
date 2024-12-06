package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author BorisMirage
 * Time: 2024/12/06 15:00
 * Created with IntelliJ IDEA
 */

public class TestingUtility {
    private static final String filePath = "src/test/java/util/LargeData.txt";

    /**
     * Execute test, measure the executing time in milliseconds as well.
     *
     * @param test                given test case
     * @param expectedExecuteTime expected executing time
     * @param <T>                 supplier type
     * @return test result
     * @throws Exception throws when actual executing time is longer than expected
     */
    public static <T> T executeTestWithMeasuringTime(Supplier<T> test, long expectedExecuteTime) throws Exception {
        long startTime = System.currentTimeMillis();
        T result = test.get();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + "ms");

        if (duration > expectedExecuteTime) {
            throw new Exception("Executing time longer than expected.");
        }

        return result;
    }

    /**
     * Convert data splited by "," to array.
     * Requires to specify the number of line since each line in the file is an array.
     *
     * @param index index of the line that needs be converted
     * @return converted array
     * @throws FileNotFoundException if data file is missing
     */
    public static int[] readData(int index) throws FileNotFoundException {
        try (Stream<String> lines = Files.lines(new File(filePath).toPath())) {
            return lines
                    .skip(index) // skip lines until reaches the given one
                    .findFirst()
                    .map(line -> {
                        return Arrays.stream(line.split(","))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                    })
                    .orElse(null);
        } catch (IOException ignored) {
            return null;
        }
    }
}
