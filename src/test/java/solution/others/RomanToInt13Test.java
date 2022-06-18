package solution.others;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:08
 * Created with IntelliJ IDEA
 */

public class RomanToInt13Test {
    private final RomanToInt_13 test = new RomanToInt_13();

    @Test
    public void test() {
        for (int i = 0; i < romanGenerator().size(); i++) {
            assertEquals(test.romanToInt(romanGenerator().get(i)), i + 1);
        }
        assertEquals(test.romanToInt("LVIII"), 58);
        assertEquals(test.romanToInt("MCMXCIV"), 1994);
    }

    private List<String> romanGenerator() {
        return Lists.newArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        );
    }
}