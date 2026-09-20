package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:33
 * Created with IntelliJ IDEA
 */

public class Trie_208Test {

    private Trie_208 test;

    @BeforeEach
    public void setUp() {
        test = new Trie_208();
    }

    @Test
    public void test() {
        test.insert("apple");
        assertTrue(test.search("apple"));
        test.insert("application");
        assertTrue(test.search("application"));
        test.insert("app");
        assertTrue(test.search("app"));
    }


    @Test
    public void testSearch() {
        test.insert("apple");
        assertTrue(test.search("apple"));
        assertFalse(test.search("banana"));
        assertFalse(test.search("app"));
        test.insert("application");
        assertTrue(test.search("application"));
    }

    @Test
    public void testStartsWith() {
        test.insert("hello");
        assertTrue(test.startsWith("he"));
        assertFalse(test.startsWith("hi"));
    }

    @Test
    public void testSearchNotFound() {
        test.insert("cat");
        assertFalse(test.search("car"));
        assertFalse(test.search("cats"));
        assertFalse(test.search("ca"));
    }

    @Test
    public void testStartsWithMultipleWords() {
        test.insert("apple");
        test.insert("application");
        test.insert("appetite");
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("appl"));
        assertTrue(test.startsWith("a"));
        assertFalse(test.startsWith("b"));
    }

    @Test
    public void testSingleCharacter() {
        test.insert("a");
        assertTrue(test.search("a"));
        assertTrue(test.startsWith("a"));
        assertFalse(test.search("b"));
    }

    @Test
    public void testPrefixIsNotWord() {
        test.insert("apple");
        assertFalse(test.search("app"));
        assertTrue(test.startsWith("app"));
    }

    @Test
    public void testInsertDuplicate() {
        test.insert("word");
        test.insert("word");
        assertTrue(test.search("word"));
    }

    @Test
    public void testEmptyTrieSearch() {
        assertFalse(test.search("anything"));
        assertFalse(test.startsWith("a"));
    }

    @Test
    public void testOverlappingWords() {
        test.insert("abc");
        test.insert("ab");
        test.insert("a");
        assertTrue(test.search("a"));
        assertTrue(test.search("ab"));
        assertTrue(test.search("abc"));
        assertFalse(test.search("abcd"));
    }

    @Test
    public void testGiantCase() {
        // Insert 1000 words: "a", "aa", "aaa", ..., up to length 1000
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + (i % 26)));
            test.insert(sb.toString());
        }
        assertTrue(test.search("a"));
        assertTrue(test.startsWith("a"));
        assertTrue(test.search(sb.toString()));
        assertFalse(test.search(sb.toString() + "z"));
    }

    @Test
    public void testEmptyStringInsertAndSearch() {
        // Empty string: insert marks root as end-of-word
        test.insert("");
        assertTrue(test.search(""));
        assertTrue(test.startsWith(""));
    }

    @Test
    public void testEmptyTrieStartsWithEmpty() {
        // Empty prefix on empty trie — root node always exists
        assertTrue(test.startsWith(""));
        assertFalse(test.search(""));
    }

    @Test
    public void testWordsSharingPrefix() {
        test.insert("app");
        test.insert("apple");
        assertTrue(test.search("app"));
        assertTrue(test.search("apple"));
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("appl"));
        assertFalse(test.search("ap"));
    }

    @Test
    public void testStartsWithFullWord() {
        test.insert("hello");
        // startsWith the full word should return true
        assertTrue(test.startsWith("hello"));
    }

    @Test
    public void testLongWord1000Chars() {
        String longWord = "a".repeat(1000);
        test.insert(longWord);
        assertTrue(test.search(longWord));
        assertTrue(test.startsWith(longWord));
        assertFalse(test.search(longWord + "a"));
        assertTrue(test.startsWith("a".repeat(500)));
        assertFalse(test.search("a".repeat(999)));
    }

    @Test
    public void testManyRandomWords() {
        Random rng = new Random(42L);
        Set<String> words = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int len = rng.nextInt(20) + 1;
            StringBuilder sb = new StringBuilder(len);
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + rng.nextInt(26)));
            }
            String word = sb.toString();
            words.add(word);
            test.insert(word);
        }
        for (String w : words) {
            assertTrue(test.search(w), "Should find: " + w);
            assertTrue(test.startsWith(w.substring(0, 1)), "Prefix should match: " + w);
        }
        assertFalse(test.search("notfound"));
        assertFalse(test.startsWith("zzzzzz"));
    }

    @Test
    public void testSearchAfterPartialInsertSequence() {
        // Insert words one by one, verify intermediate state
        assertFalse(test.search("ant"));
        test.insert("ant");
        assertTrue(test.search("ant"));
        assertFalse(test.search("ante"));
        test.insert("ante");
        assertTrue(test.search("ante"));
        assertTrue(test.search("ant"));
        assertFalse(test.search("anti"));
    }

    @Test
    public void testPrefixOfAnotherWordNotSearchable() {
        // Insert only the longer word; shorter prefix should not be searchable
        test.insert("application");
        assertFalse(test.search("app"));
        assertFalse(test.search("appli"));
        assertFalse(test.search("applicatio"));
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("applicatio"));
        assertTrue(test.search("application"));
    }

    @Test
    public void testLowercaseAlphabetAndBoundaryCharacters() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String reverse = new StringBuilder(alphabet).reverse().toString();
        test.insert("a");
        test.insert("z");
        test.insert("az");
        test.insert("za");
        test.insert(alphabet);
        test.insert(reverse);

        assertTrue(test.search("a"));
        assertTrue(test.search("z"));
        assertTrue(test.search("az"));
        assertTrue(test.search("za"));
        assertTrue(test.search(alphabet));
        assertTrue(test.search(reverse));
        assertTrue(test.startsWith("a"));
        assertTrue(test.startsWith("z"));
        assertTrue(test.startsWith(alphabet.substring(0, 25)));
        assertFalse(test.search("y"));
        assertFalse(test.search("aza"));
        assertFalse(test.startsWith("zx"));
    }

    @Test
    public void testExactSearchAndPrefixMustUseWholeCharacters() {
        test.insert("car");
        test.insert("careful");
        test.insert("cart");
        test.insert("dog");

        assertTrue(test.search("car"));
        assertTrue(test.search("careful"));
        assertFalse(test.search("ca"));
        assertFalse(test.search("care"));
        assertFalse(test.search("cars"));
        assertFalse(test.search("do"));
        assertTrue(test.startsWith("ca"));
        assertTrue(test.startsWith("care"));
        assertTrue(test.startsWith("cart"));
        assertFalse(test.startsWith("cared"));
        assertFalse(test.startsWith("doa"));
    }

    @Test
    public void testRepeatedInsertionDoesNotChangeState() {
        test.insert("repeat");
        test.insert("repeat");
        test.insert("re");
        test.insert("repeat");

        assertTrue(test.search("repeat"));
        assertTrue(test.search("re"));
        assertTrue(test.startsWith("rep"));
        assertFalse(test.search("rep"));
        assertFalse(test.search("repeats"));
    }

    @Test
    public void testIndependentInstancesDoNotShareWords() {
        Trie_208 first = new Trie_208();
        Trie_208 second = new Trie_208();

        first.insert("shared");
        second.insert("separate");

        assertTrue(first.search("shared"));
        assertFalse(first.search("separate"));
        assertTrue(second.search("separate"));
        assertFalse(second.search("shared"));
        assertFalse(first.startsWith("sep"));
        assertTrue(second.startsWith("sep"));
    }

    @Test
    public void testMaximumDocumentedWordLength() {
        String longA = "a".repeat(2000);
        String longB = "a".repeat(1999) + "b";
        test.insert(longA);
        test.insert(longB);

        assertTrue(test.search(longA));
        assertTrue(test.search(longB));
        assertTrue(test.startsWith("a".repeat(1999)));
        assertTrue(test.startsWith("a".repeat(1000)));
        assertFalse(test.search("a".repeat(1999)));
        assertFalse(test.search(longA + "a"));
        assertFalse(test.startsWith("a".repeat(1999) + "c"));
    }

    @Test
    public void testSeededStatefulOracleSequence() {
        Random rng = new Random(208L);
        Set<String> expectedWords = new HashSet<>();

        for (int operation = 0; operation < 2400; operation++) {
            int length = 1 + rng.nextInt(12);
            StringBuilder candidateBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                // Restrict the generated alphabet to create both collisions and branches.
                candidateBuilder.append((char) ('a' + rng.nextInt(6)));
            }
            String candidate = candidateBuilder.toString();
            int kind = rng.nextInt(3);
            if (kind == 0) {
                test.insert(candidate);
                expectedWords.add(candidate);
            } else if (kind == 1) {
                assertEquals(expectedWords.contains(candidate), test.search(candidate),
                        "search mismatch at operation " + operation);
            } else {
                boolean expected = expectedWords.stream().anyMatch(word -> word.startsWith(candidate));
                assertEquals(expected, test.startsWith(candidate),
                        "startsWith mismatch at operation " + operation);
            }
        }
    }

    @Test
    public void testStatefulQueriesAfterEveryInsertion() {
        Set<String> words = new HashSet<>();
        String[] sequence = {"ant", "ante", "anti", "bat", "bath", "bar", "zoo", "zoom"};
        for (String word : sequence) {
            test.insert(word);
            words.add(word);
            assertTrue(test.search(word));
            assertTrue(test.startsWith(word.substring(0, 1)));
            assertFalse(test.search(word + "x"));
            for (String prior : words) {
                assertTrue(test.search(prior));
            }
        }
        assertFalse(test.search("an"));
        assertTrue(test.startsWith("an"));
        assertFalse(test.startsWith("zooz"));
    }

    @Test
    public void testExactThirtyThousandCallBoundary() {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            String word = fourLetterWord(i);
            words.add(word);
            test.insert(word); // 10,000 insert calls
        }

        for (int i = 0; i < 7000; i++) {
            assertTrue(test.search(words.get(i))); // 7,000 search calls
        }
        for (int i = 0; i < 7000; i++) {
            assertFalse(test.search("zz" + fourLetterWord(i))); // 7,000 search calls
        }
        for (int i = 0; i < 3000; i++) {
            assertTrue(test.startsWith(words.get(i).substring(0, 2))); // 3,000 prefix calls
            assertFalse(test.startsWith("zz")); // 3,000 prefix calls
        }
    }

    private static String fourLetterWord(int value) {
        char[] result = new char[4];
        for (int position = result.length - 1; position >= 0; position--) {
            result[position] = (char) ('a' + value % 26);
            value /= 26;
        }
        return new String(result);
    }
}
