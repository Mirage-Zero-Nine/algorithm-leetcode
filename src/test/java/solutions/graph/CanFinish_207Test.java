package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;

/**
 * Every generated graph is a named dynamic JUnit test. A persistent child JVM evaluates one graph
 * per request, retaining per-case diagnostics while making noninterruptible solution loops killable.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CanFinish_207Test {
    private static final long TIMEOUT = TimeUnit.SECONDS.toNanos(2);
    private Worker worker;

    @TestFactory
    Stream<DynamicTest> everyGraphIsIndividuallyReported() {
        return scenarios().map(s -> DynamicTest.dynamicTest(s.name, () -> {
            Outcome actual = worker().run(s);
            assertEquals(s.hash, actual.hash, s.name + " hash-map");
            assertEquals(s.matrix, actual.matrix, s.name + " matrix");
            if (s.mutation) assertEquals(true, actual.unchanged, s.name + " input mutation");
            if (s.state) {
                assertEquals(false, actual.firstHash, s.name + " first hash-map call");
                assertEquals(false, actual.firstMatrix, s.name + " first matrix call");
            }
        }));
    }

    @AfterAll
    void stopWorker() {
        if (worker != null) worker.close();
    }

    private Worker worker() {
        return worker == null ? (worker = new Worker()) : worker;
    }

    private static Stream<Scenario> scenarios() {
        List<Scenario> all = new ArrayList<>();
        add(all, "contract-single-course", 1, new int[][]{}, true, true, false);
        add(all, "contract-single-dependency", 2, new int[][]{{1, 0}}, true, true, false);
        add(all, "contract-linear-dag", 3, new int[][]{{1, 0}, {2, 1}}, true, true, false);
        add(all, "contract-branching-dag", 5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}}, true, true, false);
        add(all, "contract-disconnected-dag", 4, new int[][]{{1, 0}, {3, 2}}, true, true, false);
        add(all, "contract-diamond-dag", 4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, true, true, false);
        add(all, "contract-self-loop", 1, new int[][]{{0, 0}}, false, false, false);
        add(all, "contract-two-course-cycle", 2, new int[][]{{1, 0}, {0, 1}}, false, false, false);
        add(all, "contract-four-course-cycle", 4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {0, 3}}, false, false, false);
        add(all, "contract-one-cyclic-component", 6, new int[][]{{1, 0}, {2, 1}, {4, 3}, {5, 4}, {3, 5}}, false, false, false);
        add(all, "contract-many-prerequisites", 4, new int[][]{{3, 0}, {3, 1}, {3, 2}}, true, true, false);
        add(all, "contract-many-courses-no-edges", 4, new int[][]{}, true, true, false);
        add(all, "extension-zero-courses", 0, new int[][]{}, false, true, false);
        add(all, "contract-input-is-unchanged", 3, new int[][]{{1, 0}, {2, 1}, {2, 0}}, true, true, true);
        addState(all, "state-hash-and-matrix-cycle-then-dag");
        add(all, "duplicates-repeated-dag-edge", 2, new int[][]{{1, 0}, {1, 0}, {1, 0}}, true, true, false);
        add(all, "duplicates-repeated-chain-edges", 4, new int[][]{{1, 0}, {1, 0}, {2, 1}, {2, 1}, {3, 2}}, true, true, false);
        add(all, "duplicates-repeated-cycle-edges", 3, new int[][]{{1, 0}, {1, 0}, {0, 1}, {0, 1}}, false, false, false);
        add(all, "duplicates-repeated-converging-edges", 3, new int[][]{{1, 0}, {2, 1}, {2, 1}, {2, 0}, {2, 0}}, true, true, false);
        for (int n = 1; n <= 3; n++)
            for (int mask = 0; mask < (1 << (n * n)); mask++) {
                List<int[]> e = maskEdges(n, mask, true);
                add(all, "exhaustive-n" + n + "-mask" + mask, n, e.toArray(new int[0][]), oracle(n, e), oracle(n, e), false);
            }
        for (int mask = 0; mask < (1 << 12); mask++) {
            List<int[]> e = maskEdges(4, mask, false);
            add(all, "exhaustive-n4-mask" + mask, 4, e.toArray(new int[0][]), oracle(4, e), oracle(4, e), false);
        }
        Random r = new Random(207L);
        for (int k = 0; k < 100; k++) {
            int n = 1 + r.nextInt(12);
            List<int[]> e = new ArrayList<>();
            boolean[][] seen = new boolean[n][n];
            for (int i = 0; i < n * 3; i++) {
                int c = r.nextInt(n), p = r.nextInt(n);
                if (!seen[c][p]) {
                    seen[c][p] = true;
                    e.add(new int[]{c, p});
                }
            }
            Collections.shuffle(e, r);
            add(all, "random-seed207-case" + k, n, e.toArray(new int[0][]), oracle(n, e), oracle(n, e), false);
        }
        int n = 2000;
        int[][] dag = new int[5000][2];
        int k = 0;
        for (int p = 0; p < n && k < dag.length; p++)
            for (int c = p + 1; c < n && k < dag.length; c++) dag[k++] = new int[]{c, p};
        add(all, "stress-2000-courses-5000-edge-dag", n, dag, true, true, false);
        int[][] cycle = new int[n][2];
        for (int i = 0; i < n - 1; i++) cycle[i] = new int[]{i + 1, i};
        cycle[n - 1] = new int[]{0, n - 1};
        add(all, "stress-2000-course-cycle", n, cycle, false, false, false);
        return all.stream();
    }

    private static List<int[]> maskEdges(int n, int mask, boolean self) {
        List<int[]> e = new ArrayList<>();
        int bit = 0;
        for (int p = 0; p < n; p++)
            for (int c = 0; c < n; c++)
                if ((self || c != p) && (mask & (1 << bit++)) != 0) e.add(new int[]{c, p});
        return e;
    }

    private static void add(List<Scenario> a, String name, int n, int[][] e, boolean h, boolean m, boolean mut) {
        a.add(new Scenario(name, n, copy(e), h, m, mut, false));
    }

    private static void addState(List<Scenario> a, String name) {
        a.add(new Scenario(name, 2, new int[][]{{1, 0}}, true, true, false, true));
    }

    private static boolean oracle(int n, List<int[]> edges) {
        boolean[][] seen = new boolean[n][n];
        int[] in = new int[n];
        List<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < n; i++) out.add(new ArrayList<>());
        for (int[] e : edges)
            if (!seen[e[0]][e[1]]) {
                seen[e[0]][e[1]] = true;
                in[e[0]]++;
                out.get(e[1]).add(e[0]);
            }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (in[i] == 0) q.add(i);
        int done = 0;
        while (!q.isEmpty()) {
            int p = q.remove();
            done++;
            for (int c : out.get(p)) if (--in[c] == 0) q.add(c);
        }
        return done == n;
    }

    private static int[][] copy(int[][] e) {
        int[][] c = new int[e.length][];
        for (int i = 0; i < e.length; i++) c[i] = e[i].clone();
        return c;
    }

    private record Scenario(String name, int n, int[][] edges, boolean hash, boolean matrix, boolean mutation,
                            boolean state) {
    }

    private record Outcome(boolean hash, boolean matrix, boolean unchanged, boolean firstHash, boolean firstMatrix) {
    }

    private static final class Worker implements AutoCloseable {
        final Process process;
        final BufferedWriter input;
        final BufferedReader output;

        Worker() {
            try {
                String java = System.getProperty("java.home") + File.separator + "bin" + File.separator +
                        (System.getProperty("os.name").startsWith("Windows") ? "java.exe" : "java");
                String cp = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
                process = new ProcessBuilder(java, "-cp", cp, CanFinishWorker.class.getName()).redirectErrorStream(true).start();
                input = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            } catch (IOException e) {
                throw new IllegalStateException("Could not start isolated worker", e);
            }
        }

        synchronized Outcome run(Scenario s) {
            if (!process.isAlive()) fail("Worker exited before " + s.name);
            try {
                input.write(encode(s));
                input.newLine();
                input.flush();
                long until = System.nanoTime() + TIMEOUT;
                while (!output.ready()) {
                    if (!process.isAlive()) fail("Worker exited while running " + s.name);
                    if (System.nanoTime() >= until) {
                        process.destroyForcibly();
                        fail("Timed out after 2 seconds: " + s.name);
                    }
                    Thread.sleep(1);
                }
                String line = output.readLine();
                if (line == null) fail("Missing worker result: " + s.name);
                if (line.startsWith("ERR")) fail("Worker failed for " + s.name + ": " + line);
                String[] f = line.split(" ");
                if (f.length != 6 || !f[0].equals(s.name)) fail("Malformed worker result: " + line);
                if (f.length != 6) fail("Malformed worker result: " + line);
                return new Outcome(Boolean.parseBoolean(f[1]), Boolean.parseBoolean(f[2]), Boolean.parseBoolean(f[3]),
                        Boolean.parseBoolean(f[4]), Boolean.parseBoolean(f[5]));
            } catch (InterruptedException e) {
                process.destroyForcibly();
                Thread.currentThread().interrupt();
                fail("Interrupted: " + s.name, e);
                return null;
            } catch (IOException e) {
                process.destroyForcibly();
                fail("Worker I/O: " + s.name, e);
                return null;
            }
        }

        private static String encode(Scenario s) {
            StringBuilder b = new StringBuilder(s.name + " " + s.n + " " + s.edges.length);
            for (int[] e : s.edges) b.append(' ').append(e[0]).append(',').append(e[1]);
            return b.toString();
        }

        public void close() {
            try {
                input.close();
            } catch (IOException ignored) {
            }
            if (process.isAlive()) process.destroyForcibly();
        }
    }

    /**
     * Reads one graph per line and emits one result per line; parent forcibly stops hangs.
     */
    public static final class CanFinishWorker {
        public static void main(String[] args) throws Exception {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out))) {
                String line;
                while ((line = r.readLine()) != null) {
                    String[] f = line.split(" ");
                    String name = f[0];
                    try {
                        int n = Integer.parseInt(f[1]), count = Integer.parseInt(f[2]);
                        int[][] e = new int[count][2];
                        for (int i = 0; i < count; i++) {
                            String[] p = f[i + 3].split(",");
                            e[i][0] = Integer.parseInt(p[0]);
                            e[i][1] = Integer.parseInt(p[1]);
                        }
                        int[][] before = copy(e);
                        CanFinish_207 s = new CanFinish_207();
                        boolean h, m, firstH, firstM;
                        if (name.startsWith("state-")) {
                            firstH = s.canFinish(2, new int[][]{{1, 0}, {0, 1}});
                            firstM = s.canFinishWithIntArray(2, new int[][]{{1, 0}, {0, 1}});
                            h = s.canFinish(n, e);
                            m = s.canFinishWithIntArray(n, e);
                        } else {
                            h = s.canFinish(n, e);
                            m = s.canFinishWithIntArray(n, e);
                            firstH = h;
                            firstM = m;
                        }
                        w.write(name + " " + h + " " + m + " " + Arrays.deepEquals(before, e) + " " + firstH + " " + firstM);
                    } catch (Throwable t) {
                        w.write("ERR " + name + " " + t.getClass().getSimpleName() + " " + t.getMessage());
                    }
                    w.newLine();
                    w.flush();
                }
            }
        }
    }
}
