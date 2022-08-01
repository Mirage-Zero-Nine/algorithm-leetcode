package solution.anagram;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BorisMirage
 * Time: 2022/06/18 14:35
 * Created with IntelliJ IDEA
 */

public class GroupAnagrams49Test {
    private final GroupAnagrams_49 test = new GroupAnagrams_49();

    @Test
    public void test() {
        String[] testArray = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> actual = test.groupAnagrams(testArray).stream().sorted(Comparator.comparingInt(List::size)).collect(Collectors.toList());
        Iterator<Set<String>> expected = expectedResult().iterator();
        actual.forEach(
                list -> Assertions.assertEquals(expected.next(), new HashSet<>(list))
        );
    }

    @Test
    public void testEmpty() {
        List<List<String>> actual = test.groupAnagrams(new String[]{});
        List<List<String>> expected = new ArrayList<>();
        Assertions.assertEquals(expected, actual);
    }

    private List<Set<String>> expectedResult() {
        return Lists.newArrayList(
                Sets.newHashSet("bat"),
                Sets.newHashSet("tan", "nat"),
                Sets.newHashSet("ate", "eat", "tea")
        );
    }
}