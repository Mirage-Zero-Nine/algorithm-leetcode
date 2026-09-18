package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinMutation_433Test {
    private final MinMutation_433 solution = new MinMutation_433();

    @Test
    void testBasic() {
        String[] bank = {"AACCGGTA"};
        assertEquals(1, solution.minMutation("AACCGGTT", "AACCGGTA", bank));
    }

    @Test
    void testMultipleSteps() {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        assertEquals(2, solution.minMutation("AACCGGTT", "AAACGGTA", bank));
    }

    @Test
    void testNoSolution() {
        String[] bank = {"AACCGGTA", "AACCGCTA"};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AAACGGTA", bank));
    }

    @Test
    void testSameStartEnd() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation("AACCGGTA", "AACCGGTA", bank));
    }

    @Test
    void testEmptyBank() {
        String[] bank = {};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AACCGGTA", bank));
    }

    @Test
    void testThreeSteps() {
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        assertEquals(3, solution.minMutation("AAAAACCC", "AACCCCCC", bank));
    }

    @Test
    void testNullStart() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation(null, "AACCGGTA", bank));
    }

    @Test
    void testEmptyStart() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation("", "AACCGGTA", bank));
    }

    @Test
    void testDifferentLengths() {
        String[] bank = {"AACCGGTA"};
        assertEquals(-1, solution.minMutation("AACCGG", "AACCGGTA", bank));
    }

    @Test
    void testEndNotInBank() {
        String[] bank = {"AACCGGTA", "AACCGGTC"};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AACCGGTG", bank));
    }

    @Test
    void testMultiplePaths() {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA", "AAACGCTA"};
        int result = solution.minMutation("AACCGGTT", "AAACGCTA", bank);
        assertEquals(3, result);
    }

    @Test
    void testMaximumSizeBank() {
        String[] bank = {
                "AACCGGTA", "AACCGGTC", "AACCGGCA", "AACCGGCT", "AACCGCTA",
                "AACCGCTC", "AAACGGTA", "AAACGGTC", "AAACGGCA", "AAACGGCT"
        };
        assertEquals(1, solution.minMutation("AACCGGTT", "AACCGGTA", bank));
    }

    @Test
    void testShortestPathBeatsLongerPath() {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA", "AAACGCTA"};
        assertEquals(2, solution.minMutation("AACCGGTT", "AAACGGTA", bank));
    }

    @Test
    void testStartNeedNotBeInBank() {
        assertEquals(2, solution.minMutation("AAAAACCC", "AAACCCCC",
                new String[]{"AAAACCCC", "AAACCCCC"}));
    }

    @Test
    void testEndMustBeInBank() {
        assertEquals(-1, solution.minMutation("AAAAACCC", "AACCCCCC",
                new String[]{"AAAACCCC", "AAACCCCC"}));
    }

    @Test
    void testDisconnectedBank() {
        assertEquals(-1, solution.minMutation("AACCGGTT", "AAACGGTA",
                new String[]{"AAACGGTA", "CCCCCCCC", "GGGGGGGG", "TTTTTTTT"}));
    }

    @Test
    void testDeadEndBranchDoesNotChangeAnswer() {
        assertEquals(2, solution.minMutation("AACCGGTT", "AAACGGTA",
                new String[]{"AACCGGTA", "AAACGGTA", "CCCCCCCC", "CCCCCCCA"}));
    }

    @Test
    void testCycleInBankTerminates() {
        assertEquals(2, solution.minMutation("AAAAAAAA", "AAAAAATG",
                new String[]{"AAAAAAAG", "AAAAAATG", "AAAAAAAC", "AAAAAATA"}));
    }

    @Test
    void testDuplicateBankEntries() {
        assertEquals(1, solution.minMutation("AACCGGTT", "AACCGGTA",
                new String[]{"AACCGGTA", "AACCGGTA", "AACCGGTA"}));
    }

    @Test
    void testBankOrderDoesNotMatter() {
        assertEquals(2, solution.minMutation("AACCGGTT", "AAACGGTA",
                new String[]{"AAACGGTA", "AACCGGTA", "AACCGCTA"}));
    }

    @Test
    void testOneMutationAtLastPosition() {
        assertEquals(1, solution.minMutation("AAAAAAAA", "AAAAAAAT",
                new String[]{"AAAAAAAT"}));
    }

    @Test
    void testAllCharactersCanBeUsedForOnePosition() {
        assertEquals(1, solution.minMutation("AAAAAAAA", "AAAAAAAG",
                new String[]{"AAAAAAAC", "AAAAAAAG", "AAAAAAAT", "AAAAAAAG"}));
    }

    @Test
    void testLongEightMutationChain() {
        assertEquals(8, solution.minMutation("AAAAAAAA", "CCCCCCCC", new String[]{
                "CAAAAAAA", "CCAAAAAA", "CCCAAAAA", "CCCCAAAA", "CCCCCAAA",
                "CCCCCCAA", "CCCCCCCA", "CCCCCCCC"}));
    }

    @Test
    void testSameGeneAbsentFromBank() {
        assertEquals(0, solution.minMutation("AACCGGTT", "AACCGGTT", new String[0]));
    }

    @Test
    void testSameGeneWithUnrelatedBank() {
        assertEquals(0, solution.minMutation("AACCGGTT", "AACCGGTT",
                new String[]{"CCCCCCCC", "GGGGGGGG"}));
    }

    @Test
    void testNullEndUsesDocumentedBoundary() {
        assertEquals(0, solution.minMutation("AACCGGTT", null, new String[0]));
    }

    @Test
    void testEmptyEndUsesDocumentedBoundary() {
        assertEquals(0, solution.minMutation("AACCGGTT", "", new String[0]));
    }

    @Test
    void testNullStartUsesDocumentedBoundary() {
        assertEquals(0, solution.minMutation(null, "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    @Test
    void testEmptyStartUsesDocumentedBoundary() {
        assertEquals(0, solution.minMutation("", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    @Test
    void testDifferentLengthsReturnMinusOne() {
        assertEquals(-1, solution.minMutation("AACCGG", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    @Test
    void testOneCharacterDifferenceNotInBank() {
        assertEquals(-1, solution.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTC"}));
    }

    @Test
    void testRandomSmallBanksAgainstIndependentOracle() {
        Random random = new Random(433L);
        String alphabet = "ACGT";
        boolean sawReachable = false;
        boolean sawUnreachable = false;
        for (int caseNumber = 0; caseNumber < 10; caseNumber++) {
            String start = randomGene(random, alphabet);
            Set<String> bank = new HashSet<>();
            String current = start;
            int pathLength = 1 + random.nextInt(8);
            for (int position = 0; position < pathLength; position++) {
                char[] next = current.toCharArray();
                char original = next[position];
                for (char candidate : alphabet.toCharArray()) {
                    if (candidate != original) {
                        next[position] = candidate;
                        break;
                    }
                }
                current = new String(next);
                bank.add(current);
            }
            while (bank.size() < 10) {
                bank.add(randomGene(random, alphabet));
            }
            String end = current;
            String[] bankArray = bank.toArray(String[]::new);
            int expected = referenceBfs(start, end, bank);
            assertTrue(expected > 0);
            assertEquals(expected, solution.minMutation(start, end, bankArray),
                    "reachable random case " + caseNumber);
            sawReachable = true;
        }
        for (int caseNumber = 0; caseNumber < 10; caseNumber++) {
            String start = randomGene(random, alphabet);
            Set<String> bank = new HashSet<>();
            while (bank.size() < 10) {
                bank.add(randomGene(random, alphabet));
            }
            String end = randomGene(random, alphabet);
            while (bank.contains(end)) {
                end = randomGene(random, alphabet);
            }
            int expected = referenceBfs(start, end, bank);
            assertEquals(-1, expected);
            assertEquals(expected, solution.minMutation(start, end, bank.toArray(String[]::new)),
                    "missing-end random case " + caseNumber);
            sawUnreachable = true;
        }
        for (int caseNumber = 0; caseNumber < 10; caseNumber++) {
            String start = randomGene(random, alphabet);
            char[] endChars = start.toCharArray();
            endChars[0] = differentGeneCharacter(endChars[0], alphabet);
            endChars[1] = differentGeneCharacter(endChars[1], alphabet);
            String end = new String(endChars);
            Set<String> bank = new HashSet<>();
            bank.add(end);
            while (bank.size() < 10) {
                bank.add(randomGene(random, alphabet));
            }
            int expected = referenceBfs(start, end, bank);
            if (expected != -1) {
                // Regenerate this deliberately disconnected bank if a random decoy happened
                // to form a valid bridge to the two-position-away target.
                bank.clear();
                bank.add(end);
                bank.add("GGGGGGGG");
                bank.add("TTTTTTTT");
                bank.add("CCCCCCCC");
                expected = referenceBfs(start, end, bank);
            }
            assertEquals(-1, expected);
            assertEquals(expected, solution.minMutation(start, end, bank.toArray(String[]::new)),
                    "end-present unreachable case " + caseNumber);
            sawUnreachable = true;
        }
        assertTrue(sawReachable);
        assertTrue(sawUnreachable);
    }

    private static char differentGeneCharacter(char original, String alphabet) {
        for (char candidate : alphabet.toCharArray()) {
            if (candidate != original) {
                return candidate;
            }
        }
        throw new AssertionError("gene alphabet must contain an alternate character");
    }

    private static String randomGene(Random random, String alphabet) {
        char[] gene = new char[8];
        for (int i = 0; i < gene.length; i++) {
            gene[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return new String(gene);
    }

    /** Independent Hamming-graph BFS used only to derive expected values for randomized cases. */
    private static int referenceBfs(String start, String end, Set<String> bank) {
        if (start.equals(end)) {
            return 0;
        }
        if (!bank.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<>();
        Queue<Integer> distances = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        distances.add(0);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.remove();
            int distance = distances.remove();
            for (String candidate : bank) {
                if (!visited.contains(candidate) && differsByOne(current, candidate)) {
                    if (candidate.equals(end)) {
                        return distance + 1;
                    }
                    visited.add(candidate);
                    queue.add(candidate);
                    distances.add(distance + 1);
                }
            }
        }
        return -1;
    }

    private static boolean differsByOne(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        int differences = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i) && ++differences > 1) {
                return false;
            }
        }
        return differences == 1;
    }
}
