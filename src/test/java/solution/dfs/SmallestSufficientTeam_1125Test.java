package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class SmallestSufficientTeam_1125Test {

    private final SmallestSufficientTeam_1125 test = new SmallestSufficientTeam_1125();

    @Test
    public void testHappyCases() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"java", "nodejs", "reactjs"},
            List.of(List.of("java"), List.of("nodejs"), List.of("nodejs", "reactjs"))
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testEdgeCases() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"algorithms", "math"},
            List.of(List.of("algorithms", "math"))
        );
        assertEquals(1, result.length);
    }

    @Test
    public void testLargeCase() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b", "c"},
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("a", "c"))
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testSingleSkillSinglePerson() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"x"},
            List.of(List.of("x"))
        );
        assertEquals(1, result.length);
        assertEquals(0, result[0]);
    }

    @Test
    public void testAllPeopleNeeded() {
        // Each person has exactly one unique skill
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b", "c"},
            List.of(List.of("a"), List.of("b"), List.of("c"))
        );
        assertEquals(3, result.length);
    }

    @Test
    public void testOnePersonCoversAll() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b", "c"},
            List.of(List.of("a"), List.of("a", "b", "c"), List.of("b"))
        );
        assertEquals(1, result.length);
        assertEquals(1, result[0]);
    }

    @Test
    public void testDuplicateSkillsPeople() {
        // Multiple people with same skills
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b"},
            List.of(List.of("a"), List.of("a"), List.of("b"), List.of("b"))
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testOverlappingSkills() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"a", "b", "c", "d"},
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("c", "d"), List.of("a", "d"))
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testLeetcodeExample2() {
        int[] result = test.smallestSufficientTeam(
            new String[]{"algorithms", "math", "java", "reactjs", "csharp", "aws"},
            List.of(
                List.of("algorithms", "math", "java"),
                List.of("algorithms", "math", "reactjs"),
                List.of("java", "csharp", "aws"),
                List.of("reactjs", "csharp"),
                List.of("csharp", "math"),
                List.of("aws", "java")
            )
        );
        assertEquals(2, result.length);
    }

    @Test
    public void testResultCoversAllSkills() {
        String[] skills = {"a", "b", "c", "d", "e"};
        List<List<String>> people = List.of(
            List.of("a", "b"), List.of("c", "d"), List.of("d", "e"), List.of("a", "e")
        );
        int[] result = test.smallestSufficientTeam(skills, people);
        // Verify all skills are covered
        Set<String> covered = new HashSet<>();
        for (int idx : result) {
            covered.addAll(people.get(idx));
        }
        for (String s : skills) {
            assertTrue(covered.contains(s));
        }
    }

    @Test
    public void testGiantCase() {
        // 10 skills, many people with overlapping skills
        String[] skills = {"s0","s1","s2","s3","s4","s5","s6","s7","s8","s9"};
        List<List<String>> people = List.of(
            List.of("s0","s1","s2","s3","s4","s5","s6","s7","s8","s9"),
            List.of("s0"), List.of("s1"), List.of("s2"), List.of("s3"),
            List.of("s4"), List.of("s5"), List.of("s6"), List.of("s7"),
            List.of("s8"), List.of("s9")
        );
        int[] result = test.smallestSufficientTeam(skills, people);
        assertEquals(1, result.length);
    }
}
