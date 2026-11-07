package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class OrderedStream_1656Test {

    @Test
    public void officialArrivalOrderReturnsOnlyContiguousChunks() throws Exception {
        runIsolated("scenarioOfficialArrivalOrderReturnsOnlyContiguousChunks");
    }

    private void scenarioOfficialArrivalOrderReturnsOnlyContiguousChunks() {
        OrderedStream_1656 stream = new OrderedStream_1656(5);

        assertEmpty(stream, 3, "C");
        assertChunk(stream, 1, "A");
        assertChunk(stream, 2, "B", "C");
        assertEmpty(stream, 5, "E");
        assertChunk(stream, 4, "D", "E");
    }

    @Test
    public void missingFirstIdKeepsLaterValuesWaiting() throws Exception {
        runIsolated("scenarioMissingFirstIdKeepsLaterValuesWaiting");
    }

    private void scenarioMissingFirstIdKeepsLaterValuesWaiting() {
        OrderedStream_1656 stream = new OrderedStream_1656(4);

        assertEmpty(stream, 2, "two");
        assertEmpty(stream, 3, "three");
        assertEmpty(stream, 4, "four");
        assertChunk(stream, 1, "one", "two", "three", "four");
    }

    @Test
    public void missingMiddleIdReleasesEveryWaitingSuffix() throws Exception {
        runIsolated("scenarioMissingMiddleIdReleasesEveryWaitingSuffix");
    }

    private void scenarioMissingMiddleIdReleasesEveryWaitingSuffix() {
        OrderedStream_1656 stream = new OrderedStream_1656(6);

        assertChunk(stream, 1, "A");
        assertEmpty(stream, 3, "C");
        assertEmpty(stream, 5, "E");
        assertEmpty(stream, 6, "F");
        assertChunk(stream, 2, "B", "C");
        assertChunk(stream, 4, "D", "E", "F");
    }

    @Test
    public void increasingArrivalProducesOneValuePerInsertion() throws Exception {
        runIsolated("scenarioIncreasingArrivalProducesOneValuePerInsertion");
    }

    private void scenarioIncreasingArrivalProducesOneValuePerInsertion() {
        OrderedStream_1656 stream = new OrderedStream_1656(8);

        for (int id = 1; id <= 8; id++) {
            assertChunk(stream, id, "value-" + id);
        }
    }

    @Test
    public void decreasingArrivalProducesOneMaximalFinalChunk() throws Exception {
        runIsolated("scenarioDecreasingArrivalProducesOneMaximalFinalChunk");
    }

    private void scenarioDecreasingArrivalProducesOneMaximalFinalChunk() {
        OrderedStream_1656 stream = new OrderedStream_1656(8);

        for (int id = 8; id >= 2; id--) {
            assertEmpty(stream, id, "value-" + id);
        }
        assertChunk(stream, 1, "value-1", "value-2", "value-3", "value-4",
                "value-5", "value-6", "value-7", "value-8");
    }

    @Test
    public void zigzagArrivalCombinesSeveralReadyRuns() throws Exception {
        runIsolated("scenarioZigzagArrivalCombinesSeveralReadyRuns");
    }

    private void scenarioZigzagArrivalCombinesSeveralReadyRuns() {
        OrderedStream_1656 stream = new OrderedStream_1656(7);

        assertEmpty(stream, 4, "D");
        assertChunk(stream, 1, "A");
        assertEmpty(stream, 7, "G");
        assertChunk(stream, 2, "B");
        assertEmpty(stream, 6, "F");
        assertChunk(stream, 3, "C", "D");
        assertChunk(stream, 5, "E", "F", "G");
    }

    @Test
    public void singletonStreamEmitsItsOnlyValue() throws Exception {
        runIsolated("scenarioSingletonStreamEmitsItsOnlyValue");
    }

    private void scenarioSingletonStreamEmitsItsOnlyValue() {
        OrderedStream_1656 stream = new OrderedStream_1656(1);

        assertChunk(stream, 1, "only");
    }

    @Test
    public void repeatedStringValuesRemainDistinctByTheirIds() throws Exception {
        runIsolated("scenarioRepeatedStringValuesRemainDistinctByTheirIds");
    }

    private void scenarioRepeatedStringValuesRemainDistinctByTheirIds() {
        OrderedStream_1656 stream = new OrderedStream_1656(5);

        assertEmpty(stream, 5, "same");
        assertEmpty(stream, 2, "same");
        assertChunk(stream, 1, "same", "same");
        assertEmpty(stream, 4, "same");
        assertChunk(stream, 3, "same", "same", "same");
    }

    @Test
    public void stateIsolatedBetweenInstances() throws Exception {
        runIsolated("scenarioStateIsolatedBetweenInstances");
    }

    private void scenarioStateIsolatedBetweenInstances() {
        OrderedStream_1656 first = new OrderedStream_1656(3);
        OrderedStream_1656 second = new OrderedStream_1656(3);

        assertEmpty(first, 2, "first-2");
        assertChunk(second, 1, "second-1");
        assertChunk(first, 1, "first-1", "first-2");
        assertEmpty(second, 3, "second-3");
        assertChunk(second, 2, "second-2", "second-3");
        assertChunk(first, 3, "first-3");
    }

    @Test
    public void alreadyReturnedPrefixDoesNotReappear() throws Exception {
        runIsolated("scenarioAlreadyReturnedPrefixDoesNotReappear");
    }

    private void scenarioAlreadyReturnedPrefixDoesNotReappear() {
        OrderedStream_1656 stream = new OrderedStream_1656(5);

        assertChunk(stream, 1, "A");
        assertChunk(stream, 2, "B");
        assertEmpty(stream, 5, "E");
        assertEmpty(stream, 4, "D");
        assertChunk(stream, 3, "C", "D", "E");
    }

    @Test
    public void seededRandomPermutationMatchesIndependentChunkOracle() throws Exception {
        runIsolated("scenarioSeededRandomPermutationMatchesIndependentChunkOracle");
    }

    private void scenarioSeededRandomPermutationMatchesIndependentChunkOracle() {
        int n = 97;
        List<Integer> order = permutation(n, 20261106L);
        assertPermutationMatchesOracle(n, order);
    }

    @Test
    public void exhaustivePermutationsThroughSevenIdsMatchEachChunk() throws Exception {
        runIsolated("scenarioExhaustivePermutationsThroughSevenIdsMatchEachChunk");
    }

    private void scenarioExhaustivePermutationsThroughSevenIdsMatchEachChunk() {
        for (int n = 1; n <= 7; n++) {
            List<Integer> order = new ArrayList<>();
            for (int id = 1; id <= n; id++) {
                order.add(id);
            }
            checkPermutations(n, order, 0);
        }
    }

    @Test
    public void maximumNIncreasingOrderEmitsEveryValueExactlyOnce() throws Exception {
        runIsolated("scenarioMaximumNIncreasingOrderEmitsEveryValueExactlyOnce");
    }

    private void scenarioMaximumNIncreasingOrderEmitsEveryValueExactlyOnce() {
        int n = 1000;
        OrderedStream_1656 stream = new OrderedStream_1656(n);

        for (int id = 1; id <= n; id++) {
            assertEquals(List.of("v" + id), stream.insert(id, "v" + id), "id=" + id);
        }
    }

    @Test
    public void maximumNDecreasingOrderReleasesOneThousandValuesAtTheEnd() throws Exception {
        runIsolated("scenarioMaximumNDecreasingOrderReleasesOneThousandValuesAtTheEnd");
    }

    private void scenarioMaximumNDecreasingOrderReleasesOneThousandValuesAtTheEnd() {
        int n = 1000;
        OrderedStream_1656 stream = new OrderedStream_1656(n);

        for (int id = n; id >= 2; id--) {
            assertEquals(List.of(), stream.insert(id, "v" + id), "id=" + id);
        }

        List<String> expected = new ArrayList<>();
        for (int id = 1; id <= n; id++) {
            expected.add("v" + id);
        }
        assertEquals(expected, stream.insert(1, "v1"));
    }

    private static void assertChunk(OrderedStream_1656 stream, int id, String... values) {
        assertEquals(List.of(values), stream.insert(id, values[0]), "id=" + id);
    }

    private static void assertEmpty(OrderedStream_1656 stream, int id, String value) {
        assertEquals(List.of(), stream.insert(id, value), "id=" + id);
    }

    private static List<Integer> permutation(int n, long seed) {
        List<Integer> order = new ArrayList<>();
        for (int id = 1; id <= n; id++) {
            order.add(id);
        }
        Collections.shuffle(order, new Random(seed));
        return order;
    }

    private static void assertPermutationMatchesOracle(int n, List<Integer> order) {
        OrderedStream_1656 stream = new OrderedStream_1656(n);
        String[] values = new String[n];
        boolean[] received = new boolean[n];
        int next = 0;
        for (int id : order) {
            String value = "value-" + id;
            values[id - 1] = value;
            received[id - 1] = true;
            List<String> expected = new ArrayList<>();
            while (next < n && received[next]) {
                expected.add(values[next++]);
            }
            assertEquals(expected, stream.insert(id, value), "order=" + order + ", id=" + id);
        }
    }

    private static void checkPermutations(int n, List<Integer> order, int index) {
        if (index == n) {
            assertPermutationMatchesOracle(n, order);
            return;
        }
        for (int i = index; i < n; i++) {
            Collections.swap(order, index, i);
            checkPermutations(n, order, index + 1);
            Collections.swap(order, index, i);
        }
    }

    private static void runIsolated(String scenarioMethod) throws Exception {
        if (Boolean.getBoolean("ordered.stream.worker")) {
            throw new IllegalStateException("Child scenario attempted to spawn another child");
        }
        String classPath = System.getProperty("surefire.test.class.path",
                System.getProperty("java.class.path"));
        Path output = Files.createTempFile("ordered-stream-test-", ".log");
        Process process = null;
        try {
            process = new ProcessBuilder(Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                    "-Dordered.stream.worker=true", "-cp", classPath,
                    Worker.class.getName(), scenarioMethod)
                    .redirectErrorStream(true).redirectOutput(output.toFile()).start();
            if (!process.waitFor(10, TimeUnit.SECONDS)) {
                stopAndReap(process);
                throw new AssertionError("Child JVM timed out for " + scenarioMethod);
            }
            if (process.exitValue() != 0) {
                throw new AssertionError("Child JVM failed for " + scenarioMethod + " (exit "
                        + process.exitValue() + ")\n" + Files.readString(output));
            }
        } catch (InterruptedException interrupted) {
            if (process != null && process.isAlive()) {
                stopAndReap(process);
            }
            Thread.currentThread().interrupt();
            throw interrupted;
        } finally {
            if (process != null && process.isAlive()) {
                stopAndReap(process);
            }
            Files.deleteIfExists(output);
        }
    }

    private static void stopAndReap(Process process) {
        process.destroyForcibly();
        boolean interrupted = false;
        while (process.isAlive()) {
            try {
                process.waitFor(2, TimeUnit.SECONDS);
            } catch (InterruptedException interruption) {
                interrupted = true;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public static class Worker {
        public static void main(String[] args) throws Exception {
            try {
                Method method = OrderedStream_1656Test.class.getDeclaredMethod(args[0]);
                method.setAccessible(true);
                method.invoke(new OrderedStream_1656Test());
            } catch (InvocationTargetException failure) {
                failure.getCause().printStackTrace(System.err);
                System.exit(1);
            }
        }
    }
}
