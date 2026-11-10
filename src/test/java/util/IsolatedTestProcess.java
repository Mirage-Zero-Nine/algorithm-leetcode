package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public final class IsolatedTestProcess {
    private static final long TERMINATION_WAIT_SECONDS = 5;

    private IsolatedTestProcess() {
    }

    public static void run(Class<?> testClass, String methodName, long timeoutSeconds) throws IOException, InterruptedException {
        Path output = Files.createTempFile("isolated-test-", ".log");
        Process process = null;
        try {
            String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();
            String classPath = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
            process = new ProcessBuilder(java, "-cp", classPath, IsolatedTestProcess.class.getName(),
                    testClass.getName(), methodName)
                    .redirectErrorStream(true)
                    .redirectOutput(output.toFile())
                    .start();

            if (!process.waitFor(timeoutSeconds, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                if (!process.waitFor(TERMINATION_WAIT_SECONDS, TimeUnit.SECONDS)) {
                    throw new AssertionError("Timed-out test child could not be reaped: " + methodName);
                }
                throw new AssertionError("Test child exceeded " + timeoutSeconds + " seconds: " + methodName
                        + System.lineSeparator() + Files.readString(output));
            }
            if (process.exitValue() != 0) {
                throw new AssertionError("Test child failed: " + methodName
                        + System.lineSeparator() + Files.readString(output));
            }
        } catch (InterruptedException interrupted) {
            if (process != null) {
                terminateAndWait(process);
            }
            Thread.currentThread().interrupt();
            throw interrupted;
        } finally {
            if (process != null && process.isAlive()) {
                terminateAndWait(process);
            }
            Files.deleteIfExists(output);
        }
    }

    private static void terminateAndWait(Process process) {
        process.destroyForcibly();
        boolean interrupted = false;
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(TERMINATION_WAIT_SECONDS);
        while (process.isAlive()) {
            long remaining = deadline - System.nanoTime();
            if (remaining <= 0) {
                break;
            }
            try {
                process.waitFor(remaining, TimeUnit.NANOSECONDS);
            } catch (InterruptedException ignored) {
                interrupted = true;
                process.destroyForcibly();
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    static void main(String[] args) throws ReflectiveOperationException {
        Class<?> testClass = Class.forName(args[0]);
        Method method = testClass.getDeclaredMethod(args[1]);
        method.setAccessible(true);
        try {
            var constructor = testClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            method.invoke(constructor.newInstance());
        } catch (InvocationTargetException failure) {
            failure.getCause().printStackTrace();
            System.exit(1);
        }
    }
}
