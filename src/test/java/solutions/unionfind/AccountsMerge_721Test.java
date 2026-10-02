package solutions.unionfind;

import org.junit.jupiter.api.Test;

import module java.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountsMerge_721Test {

    @Test
    @SuppressWarnings("unchecked")
    void testLateBridgeMergesExistingComponentsAndPreservesSortedEmails() {
        List<List<String>> input = accounts(List.of("Sam", "d@x.com", "c@x.com"),
                List.of("Sam", "b@x.com", "a@x.com"), List.of("Sam", "c@x.com", "b@x.com"),
                List.of("Sam", "separate@x.com"));
        List<List<String>> result = invoke(input);
        assertEquals(2, result.size());
        assertEquals(Set.of(List.of("Sam", "a@x.com", "b@x.com", "c@x.com", "d@x.com"),
                List.of("Sam", "separate@x.com")), new HashSet<>(result));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testIdenticalNameOnlyAccountsRemainSeparate() {
        List<List<String>> result = invoke(accounts(List.of("Sam"), List.of("Sam")));
        assertEquals(List.of(List.of("Sam"), List.of("Sam")), result);
    }

    @Test
    void testManyIndependentMergeGroupsKeepEveryAccountAndEmail() {
        List<List<String>> input = new ArrayList<>();
        Set<List<String>> expected = new HashSet<>();
        for (int group = 0; group < 100; group++) {
            String name = "User" + group;
            String prefix = "g" + group;
            input.add(new ArrayList<>(List.of(name, prefix + "z@x.com", prefix + "m@x.com")));
            input.add(new ArrayList<>(List.of(name, prefix + "m@x.com", prefix + "a@x.com")));
            expected.add(List.of(name, prefix + "a@x.com", prefix + "m@x.com", prefix + "z@x.com"));
        }
        Collections.shuffle(input, new Random(7212026L));
        List<List<String>> result = invoke(input);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, new HashSet<>(result));
    }


    private final AccountsMerge_721 solution = new AccountsMerge_721();

    // Helper to build mutable list of lists (needed since solution may modify input)
    @SafeVarargs
    private List<List<String>> accounts(List<String>... lists) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> l : lists) {
            result.add(new ArrayList<>(l));
        }
        return result;
    }

    // Normalize results for order-independent comparison
    private Set<List<String>> normalize(List<List<String>> result) {
        Set<List<String>> set = new HashSet<>();
        for (List<String> account : result) {
            List<String> sorted = new ArrayList<>();
            sorted.add(account.getFirst());
            List<String> emails = new ArrayList<>(account.subList(1, account.size()));
            Collections.sort(emails);
            sorted.addAll(emails);
            set.add(sorted);
        }
        return set;
    }

    // --- Existing tests (preserved) ---

    @Test
    void testAccountsMerge_multipleAccountsWithSharedEmail() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testAccountsMerge_noSharedEmail() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("Paul", "paul@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("Paul", "paul@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testAccountsMerge_multipleEmailsShared() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "john00@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testAccountsMerge_duplicateEmails() {
        List<List<String>> input = accounts(
                List.of("John", "johnsmith@mail.com", "johnsmith@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("John", "john00@mail.com", "johnsmith@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testAccountsMerge_singleAccount() {
        List<List<String>> input = accounts(List.of("John", "johnsmith@mail.com"));
        Set<List<String>> expected = Set.of(List.of("John", "johnsmith@mail.com"));
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testAccountsMerge_emptyInput() {
        List<List<String>> result = invoke(new ArrayList<>());
        assertTrue(result.isEmpty());
    }

    @Test
    void testAccountsMerge_nullInput() {
        List<List<String>> result = invoke(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAccountsMerge_chainMerge() {
        List<List<String>> input = accounts(
                List.of("John", "a@mail.com", "b@mail.com"),
                List.of("John", "b@mail.com", "c@mail.com"),
                List.of("John", "c@mail.com", "d@mail.com")
        );
        List<List<String>> result = invoke(input);
        assertEquals(1, result.size());
        assertEquals(5, result.getFirst().size()); // name + 4 emails
    }

    @Test
    void testAccountsMerge_sameNameDifferentPeople() {
        List<List<String>> input = accounts(
                List.of("John", "john1@mail.com"),
                List.of("John", "john2@mail.com")
        );
        assertEquals(2, invoke(input).size());
    }

    @Test
    void testAccountsMerge_giantCase() {
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add(new ArrayList<>(List.of("User", "common@mail.com", "unique" + i + "@mail.com")));
        }
        List<List<String>> result = invoke(input);
        assertEquals(1, result.size());
        assertEquals(102, result.getFirst().size()); // name + common + 100 unique
    }

    // --- NEW tests ---

    @Test
    void testSingleAccountNoEmails() {
        List<List<String>> input = accounts(List.of("Alice"));
        List<List<String>> result = invoke(input);
        assertEquals(1, result.size());
        assertEquals("Alice", result.getFirst().getFirst());
        assertEquals(1, result.getFirst().size());
    }

    @Test
    void testSingleAccountOneEmail() {
        List<List<String>> input = accounts(List.of("Bob", "bob@mail.com"));
        Set<List<String>> expected = Set.of(List.of("Bob", "bob@mail.com"));
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testTwoAccountsNoSharedEmails() {
        List<List<String>> input = accounts(
                List.of("Alice", "alice@mail.com"),
                List.of("Bob", "bob@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Alice", "alice@mail.com"),
                List.of("Bob", "bob@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testTwoAccountsShareOneEmail() {
        List<List<String>> input = accounts(
                List.of("Alice", "shared@mail.com", "a1@mail.com"),
                List.of("Alice", "shared@mail.com", "a2@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Alice", "a1@mail.com", "a2@mail.com", "shared@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testThreeAccountsChainMerge() {
        // A-B share email, B-C share email -> all merge into one
        List<List<String>> input = accounts(
                List.of("X", "a@x.com", "ab@x.com"),
                List.of("X", "ab@x.com", "bc@x.com"),
                List.of("X", "bc@x.com", "c@x.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("X", "a@x.com", "ab@x.com", "bc@x.com", "c@x.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testSameNameNoSharedEmailsNotMerged() {
        // LeetCode quirk: same name does NOT mean same person
        List<List<String>> input = accounts(
                List.of("John", "john1@mail.com"),
                List.of("John", "john2@mail.com"),
                List.of("John", "john3@mail.com")
        );
        assertEquals(3, invoke(input).size());
    }

    @Test
    void testDuplicateEmailsInternally() {
        // Account has the same email listed multiple times
        List<List<String>> input = accounts(
                List.of("Eve", "eve@mail.com", "eve@mail.com", "extra@mail.com")
        );
        Set<List<String>> expected = Set.of(
                List.of("Eve", "eve@mail.com", "extra@mail.com")
        );
        assertEquals(expected, normalize(invoke(input)));
    }

    @Test
    void testOutputEmailsSortedAndNameFirst() {
        List<List<String>> input = accounts(
                List.of("Zara", "z@mail.com", "a@mail.com", "m@mail.com")
        );
        List<List<String>> result = invoke(input);
        assertEquals(1, result.size());
        assertEquals("Zara", result.getFirst().getFirst());
        List<String> emails = result.getFirst().subList(1, result.getFirst().size());
        List<String> sorted = new ArrayList<>(emails);
        Collections.sort(sorted);
        assertEquals(sorted, emails, "Emails must be in sorted order");
    }

    @Test
    void testLargeInput50AccountsRandomShared() {
        Random rng = new Random(42L);
        int n = 50;
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> account = new ArrayList<>();
            account.add("User" + i);
            account.add("unique" + i + "@test.com");
            account.add("shared" + rng.nextInt(20) + "@test.com");
            account.add("shared" + rng.nextInt(20) + "@test.com");
            input.add(account);
        }

        List<List<String>> result = invoke(input);

        // Reference union-find to compute expected group count
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        java.util.Map<String, Integer> emailOwner = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < input.get(i).size(); j++) {
                String email = input.get(i).get(j);
                if (emailOwner.containsKey(email)) {
                    refUnion(parent, emailOwner.get(email), i);
                } else {
                    emailOwner.put(email, i);
                }
            }
        }
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) roots.add(refFind(parent, i));

        assertEquals(roots.size(), result.size());

        // Verify all emails present and sorted
        Set<String> allResultEmails = new HashSet<>();
        for (List<String> account : result) {
            assertTrue(account.size() >= 1);
            List<String> emails = account.subList(1, account.size());
            List<String> sorted = new ArrayList<>(emails);
            Collections.sort(sorted);
            assertEquals(sorted, emails, "Emails must be sorted");
            allResultEmails.addAll(emails);
        }
        assertEquals(emailOwner.keySet(), allResultEmails);
    }

    /**
     * Exhaustively checks every assignment of three labeled emails to three accounts, requiring
     * each account to have at least one email. Each account has one of seven nonempty email
     * subsets, so all 7^3 = 343 valid assignments are covered. The expected result comes from
     * reachability over an independently built account graph rather than from union-find.
     */
    @Test
    void testExhaustiveThreeAccountTwoEmailMemberships() {
        runInIsolatedJvm("exhaustive", 5);
    }

    private void runExhaustiveThreeAccountTwoEmailMemberships() {
        for (int firstAccountEmails = 1; firstAccountEmails < 8; firstAccountEmails++) {
            for (int secondAccountEmails = 1; secondAccountEmails < 8; secondAccountEmails++) {
                for (int thirdAccountEmails = 1; thirdAccountEmails < 8; thirdAccountEmails++) {
                    List<List<String>> input = new ArrayList<>();
                    int[] accountEmails = {firstAccountEmails, secondAccountEmails, thirdAccountEmails};
                    for (int account = 0; account < accountEmails.length; account++) {
                        List<String> row = new ArrayList<>(List.of("User"));
                        for (int email = 0; email < 3; email++) {
                            if ((accountEmails[account] & (1 << email)) != 0) {
                                row.add("e" + email + "@example.com");
                            }
                        }
                        input.add(row);
                    }
                    List<List<String>> expected = normalizeRows(expectedByReachability(input));
                    assertEquals(expected, normalizeRows(new AccountsMerge_721().accountsMerge(input)),
                            "account masks " + firstAccountEmails + "," + secondAccountEmails + ","
                                    + thirdAccountEmails);
                }
            }
        }
    }

    @Test
    void testMaximumPublishedInputAndIndependentEmailCoverage() {
        runInIsolatedJvm("maximum", 10);
    }

    private void runMaximumPublishedInputAndIndependentEmailCoverage() {
        List<List<String>> input = new ArrayList<>();
        for (int account = 0; account < 1000; account++) {
            List<String> row = new ArrayList<>();
            row.add("User");
            row.add("bridge@example.com");
            for (int email = 0; email < 8; email++) {
                row.add("user" + account + "-" + email + "@example.com");
            }
            input.add(row);
        }
        List<List<String>> before = deepCopy(input);
        SolverResponse response = invokeResponse(input);
        Set<List<String>> result = normalize(response.result());
        Set<List<String>> expected = Set.of(expectedMaximumAccount());
        assertEquals(expected, result);
        assertEquals(before, response.postCallInput(), "accountsMerge must not mutate child input rows");
    }

    @Test
    void testRepeatedCallsUseFreshStateAndDoNotLeakEmails() {
        List<List<String>> first = accounts(
                List.of("A", "a@example.com"), List.of("A", "shared@example.com"));
        List<List<String>> second = accounts(
                List.of("B", "b@example.com"), List.of("B", "shared@example.com"));
        List<SolverResponse> responses = invokeSequence(first, second);
        assertEquals(normalizeRows(expectedByReachability(first)), normalizeRows(responses.getFirst().result()));
        assertEquals(normalizeRows(expectedByReachability(second)), normalizeRows(responses.get(1).result()));
    }

    private List<String> expectedMaximumAccount() {
        List<String> expected = new ArrayList<>();
        expected.add("User");
        expected.add("bridge@example.com");
        for (int account = 0; account < 1000; account++) {
            for (int email = 0; email < 8; email++) {
                expected.add("user" + account + "-" + email + "@example.com");
            }
        }
        Collections.sort(expected.subList(1, expected.size()));
        return expected;
    }

    /**
     * Runs stress/exhaustive checks in a child JVM so a nonterminating solution cannot leave a
     * worker thread alive after a JUnit timeout.  The parent owns the deadline and forcibly kills
     * the process when it expires; the child runs only the selected body and reports assertion
     * failures through its exit code.
     */
    private void runInIsolatedJvm(String caseName, int timeoutSeconds) {
        String javaExecutable = java.nio.file.Path.of(System.getProperty("java.home"), "bin", "java")
                .toString();
        Process process;
        try {
            process = new ProcessBuilder(javaExecutable, "-cp", System.getProperty("java.class.path"),
                    AccountsMerge_721Test.class.getName(), "child", caseName)
                    .redirectErrorStream(true)
                    .start();
        } catch (java.io.IOException exception) {
            throw new AssertionError("Could not start isolated test JVM", exception);
        }
        try {
            boolean completed = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                assertTrue(process.waitFor(2, TimeUnit.SECONDS),
                        "Timed-out child JVM did not terminate: " + caseName);
                throw new AssertionError("Isolated test JVM timed out: " + caseName);
            }
            String output = new String(process.getInputStream().readAllBytes(),
                    java.nio.charset.StandardCharsets.UTF_8);
            assertEquals(0, process.exitValue(), "Isolated test failed: " + output);
        } catch (InterruptedException exception) {
            process.destroyForcibly();
            Thread.currentThread().interrupt();
            throw new AssertionError("Interrupted while waiting for isolated test JVM", exception);
        } catch (java.io.IOException exception) {
            process.destroyForcibly();
            throw new AssertionError("Could not read isolated test output", exception);
        }
    }

    /**
     * Every solver call runs in a child JVM owned by this parent test process.
     */
    private List<List<String>> invoke(List<List<String>> input) {
        return invokeResponse(input).result();
    }

    private SolverResponse invokeResponse(List<List<String>> input) {
        String javaExecutable = java.nio.file.Path.of(System.getProperty("java.home"), "bin", "java")
                .toString();
        java.nio.file.Path outputFile = null;
        try {
            outputFile = java.nio.file.Files.createTempFile("accounts-merge-solver-", ".bin");
            Process process = new ProcessBuilder(javaExecutable, "-cp", System.getProperty("java.class.path"),
                    AccountsMerge_721Test.class.getName(), "solve")
                    .redirectOutput(outputFile.toFile())
                    .start();
            try (java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(process.getOutputStream())) {
                output.writeObject(input);
            }
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                throw new AssertionError("Isolated solver JVM timed out");
            }
            if (process.exitValue() != 0) {
                throw new AssertionError("Isolated solver JVM failed: "
                        + new String(process.getErrorStream().readAllBytes(), java.nio.charset.StandardCharsets.UTF_8));
            }
            // Output is redirected to a file so a large merged account cannot fill a pipe while
            // the parent waits. Read the complete serialized response from it.
            try (java.io.ObjectInputStream result = new java.io.ObjectInputStream(
                    java.nio.file.Files.newInputStream(outputFile))) {
                return (SolverResponse) result.readObject();
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new AssertionError("Interrupted while waiting for isolated solver JVM", exception);
        } catch (java.io.IOException | ClassNotFoundException exception) {
            throw new AssertionError("Could not communicate with isolated solver JVM", exception);
        } finally {
            if (outputFile != null) {
                try {
                    java.nio.file.Files.deleteIfExists(outputFile);
                } catch (java.io.IOException ignored) {
                    // The temporary file is only a bounded test transport artifact.
                }
            }
        }
    }

    private List<SolverResponse> invokeSequence(List<List<String>> first, List<List<String>> second) {
        String javaExecutable = java.nio.file.Path.of(System.getProperty("java.home"), "bin", "java")
                .toString();
        java.nio.file.Path outputFile = null;
        try {
            outputFile = java.nio.file.Files.createTempFile("accounts-merge-sequence-", ".bin");
            Process process = new ProcessBuilder(javaExecutable, "-cp", System.getProperty("java.class.path"),
                    AccountsMerge_721Test.class.getName(), "sequence")
                    .redirectOutput(outputFile.toFile())
                    .start();
            try (java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(process.getOutputStream())) {
                output.writeObject(List.of(first, second));
            }
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                throw new AssertionError("Isolated sequence solver JVM timed out");
            }
            if (process.exitValue() != 0) {
                throw new AssertionError("Isolated sequence solver JVM failed: "
                        + new String(process.getErrorStream().readAllBytes(), java.nio.charset.StandardCharsets.UTF_8));
            }
            try (java.io.ObjectInputStream result = new java.io.ObjectInputStream(
                    java.nio.file.Files.newInputStream(outputFile))) {
                return (List<SolverResponse>) result.readObject();
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new AssertionError("Interrupted while waiting for isolated sequence solver JVM", exception);
        } catch (java.io.IOException | ClassNotFoundException exception) {
            throw new AssertionError("Could not communicate with isolated sequence solver JVM", exception);
        } finally {
            if (outputFile != null) {
                try {
                    java.nio.file.Files.deleteIfExists(outputFile);
                } catch (java.io.IOException ignored) {
                    // The temporary file is only a bounded test transport artifact.
                }
            }
        }
    }

    /**
     * Immutable response carrier for the child-JVM protocol. A record keeps the result and the
     * child's post-call input together, so mutation checks observe exactly what the solver saw
     * after it returned. It is serializable because the parent and child exchange this value over
     * an object stream.
     */
    private record SolverResponse(List<List<String>> result, List<List<String>> postCallInput)
            implements java.io.Serializable {
    }

    public static void main(String[] args) {
        if (args.length == 1 && "solve".equals(args[0])) {
            try (java.io.ObjectInputStream input = new java.io.ObjectInputStream(System.in);
                 java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(System.out)) {
                List<List<String>> accounts = (List<List<String>>) input.readObject();
                output.writeObject(new SolverResponse(new AccountsMerge_721().accountsMerge(accounts), accounts));
            } catch (Throwable failure) {
                failure.printStackTrace();
                System.exit(1);
            }
            return;
        }
        if (args.length == 1 && "sequence".equals(args[0])) {
            try (java.io.ObjectInputStream input = new java.io.ObjectInputStream(System.in);
                 java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(System.out)) {
                List<List<List<String>>> inputs = (List<List<List<String>>>) input.readObject();
                AccountsMerge_721 solution = new AccountsMerge_721();
                List<SolverResponse> responses = new ArrayList<>();
                for (List<List<String>> accounts : inputs) {
                    responses.add(new SolverResponse(solution.accountsMerge(accounts), accounts));
                }
                output.writeObject(responses);
            } catch (Throwable failure) {
                failure.printStackTrace();
                System.exit(1);
            }
            return;
        }
        if (args.length == 2 && "child".equals(args[0])) {
            AccountsMerge_721Test test = new AccountsMerge_721Test();
            try {
                if ("exhaustive".equals(args[1])) {
                    test.runExhaustiveThreeAccountTwoEmailMemberships();
                } else if ("maximum".equals(args[1])) {
                    test.runMaximumPublishedInputAndIndependentEmailCoverage();
                } else {
                    throw new IllegalArgumentException("Unknown child test: " + args[1]);
                }
            } catch (Throwable failure) {
                failure.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * Builds the required components by graph reachability, independently of UnionFind.
     */
    private List<List<String>> expectedByReachability(List<List<String>> input) {
        Map<String, List<Integer>> owners = new java.util.HashMap<>();
        for (int account = 0; account < input.size(); account++) {
            for (String email : input.get(account).subList(1, input.get(account).size())) {
                owners.computeIfAbsent(email, ignored -> new ArrayList<>()).add(account);
            }
        }
        boolean[] visited = new boolean[input.size()];
        List<List<String>> expected = new ArrayList<>();
        for (int start = 0; start < input.size(); start++) {
            if (visited[start]) {
                continue;
            }
            List<Integer> queue = new ArrayList<>(List.of(start));
            visited[start] = true;
            TreeSet<String> emails = new TreeSet<>();
            for (int head = 0; head < queue.size(); head++) {
                int account = queue.get(head);
                emails.addAll(input.get(account).subList(1, input.get(account).size()));
                for (String email : input.get(account).subList(1, input.get(account).size())) {
                    for (int neighbor : owners.get(email)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
            }
            List<String> merged = new ArrayList<>();
            merged.add(input.get(start).getFirst());
            merged.addAll(emails);
            expected.add(merged);
        }
        return expected;
    }

    /**
     * Sorts rows for order-independent comparison while preserving duplicate rows.
     */
    private List<List<String>> normalizeRows(List<List<String>> result) {
        List<List<String>> normalized = new ArrayList<>();
        for (List<String> account : result) {
            List<String> row = new ArrayList<>();
            row.add(account.getFirst());
            List<String> emails = new ArrayList<>(account.subList(1, account.size()));
            Collections.sort(emails);
            row.addAll(emails);
            normalized.add(row);
        }
        normalized.sort((left, right) -> {
            int size = Math.min(left.size(), right.size());
            for (int i = 0; i < size; i++) {
                int comparison = left.get(i).compareTo(right.get(i));
                if (comparison != 0) {
                    return comparison;
                }
            }
            return Integer.compare(left.size(), right.size());
        });
        return normalized;
    }

    private List<List<String>> deepCopy(List<List<String>> input) {
        List<List<String>> copy = new ArrayList<>();
        for (List<String> row : input) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    private int refFind(int[] parent, int x) {
        if (parent[x] != x) parent[x] = refFind(parent, parent[x]);
        return parent[x];
    }

    private void refUnion(int[] parent, int a, int b) {
        parent[refFind(parent, a)] = refFind(parent, b);
    }
}
