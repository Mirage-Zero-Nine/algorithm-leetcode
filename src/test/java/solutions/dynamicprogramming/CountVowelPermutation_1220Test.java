package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class CountVowelPermutation_1220Test {

    private final CountVowelPermutation_1220 test = new CountVowelPermutation_1220();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.countVowelPermutation(1));
        assertEquals(10, test.countVowelPermutation(2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(5, test.countVowelPermutation(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(68, test.countVowelPermutation(5));
    }

    @Test
    public void testN3() {
        assertEquals(19, test.countVowelPermutation(3));
    }

    @Test
    public void testN4() {
        assertEquals(35, test.countVowelPermutation(4));
    }

    @Test
    public void testN10() {
        assertEquals(1739, test.countVowelPermutation(10));
    }

    @Test
    public void testN20() {
        assertEquals(1151090, test.countVowelPermutation(20));
    }

    @Test
    public void testN50() {
        assertEquals(227130014, test.countVowelPermutation(50));
    }

    @Test
    public void testN100() {
        assertEquals(173981881, test.countVowelPermutation(100));
    }

    @Test
    public void testGiantCase() {
        // n = 20000, just verify it returns a valid modular result
        int result = test.countVowelPermutation(20000);
        assertEquals(true, result >= 0 && result < 1_000_000_007);
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctLengths() {
        return Stream.of(
                DynamicTest.dynamicTest("n6", () -> assertEquals(129, test.countVowelPermutation(6))),
                DynamicTest.dynamicTest("n7", () -> assertEquals(249, test.countVowelPermutation(7))),
                DynamicTest.dynamicTest("n8", () -> assertEquals(474, test.countVowelPermutation(8))),
                DynamicTest.dynamicTest("n9", () -> assertEquals(911, test.countVowelPermutation(9))),
                DynamicTest.dynamicTest("n11", () -> assertEquals(3336, test.countVowelPermutation(11))),
                DynamicTest.dynamicTest("n12", () -> assertEquals(6377, test.countVowelPermutation(12))),
                DynamicTest.dynamicTest("n15", () -> assertEquals(44779, test.countVowelPermutation(15))),
                DynamicTest.dynamicTest("n25", () -> assertEquals(29599477, test.countVowelPermutation(25))),
                DynamicTest.dynamicTest("n30", () -> assertEquals(761083377, test.countVowelPermutation(30))),
                DynamicTest.dynamicTest("n75", () -> assertEquals(467397509, test.countVowelPermutation(75))),
                DynamicTest.dynamicTest("n200", () -> assertEquals(670333618, test.countVowelPermutation(200))));
    }
}
