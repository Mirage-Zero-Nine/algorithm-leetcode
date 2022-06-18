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
            assertEquals(i + 1, test.romanToInt(romanGenerator().get(i)));
        }
        assertEquals(58, test.romanToInt("LVIII"));
        assertEquals(1994, test.romanToInt("MCMXCIV"));
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