package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/30 11:39
 * Created with IntelliJ IDEA
 */

public class TwoEditWords_2452Test {

    private final TwoEditWords_2452 test = new TwoEditWords_2452();

    @Test
    public void test() {
        String[] query = new String[]{"word", "note", "ants", "wood"};
        String[] dictionary = new String[]{"wood", "joke", "moat"};
        List<String> expected = Lists.newArrayList("word", "note", "wood");
        assertIterableEquals(expected, test.twoEditWords(query, dictionary));
    }

    @Test
    public void testEmpty() {
        String[] query = new String[]{"yes"};
        String[] dictionary = new String[]{"not"};
        List<String> expected = Lists.newArrayList();
        assertIterableEquals(expected, test.twoEditWords(query, dictionary));
    }

    @Test
    public void testLong(){
        String[] query = new String[]{"aoxqockwtrbodtkefuwusiqvgyvrkfluiexyvvbuylsbgkdmpsfs","zyylrmkhwkyelyhwpocptsuqalbbfjpcwjmmhlwgdqrpjosytanj","fjrmkghkioiycvvkwserwrnfeghdekhkrlzqkgezblhwgnxkvecw","lsujpedqupihhtrlkhethyjysezioqenxbgpqoccadigbjxjqysl","qvywvdgtyzpnwdakpvwcqrfkocilpjavfszuonfulnllrvfhuugq","afxquneegawtxcrfzstuubsgubohynubrknktkwxbkafiujtenhb","arphizyjcxwtajepltchhasqvvnfadxpasuoztzpexjvyxkzikhn","moxqockwtrbodtkefuwusiqvgyvrkfluiexyvvbuylsbgkdmpsas","kmjqtzrnvtwjlfubhfbduqdnkpeeyamciirdvdyxmbftgpgmebzm","svvzyadmowkrsigfhmxztspppgnflacwqhzulbjiviqyqmlwvxbi","aoxqockwtrbodtkefuwusiqvgyvrkfluiexyvvfuylsbgkdmpsas","hxffjniblzdtybtjtpvivtplrrswrrvojybeuthrzfpmrzeqmpmt","tjhechuiymwlyydrbdeizlxbmcxclrzqczjjdlkuuqzjjvlzulxi","ogxeelmgsaqpgivkocgltpxrdcexraukiwwhoiycqrvzonjehwms","wcmzwqjeibicgutrqzetqilhryfdarxaoolmtbcbqybpbppoayvg","piyxqexaxlrzohtsxdilensztzqrimccsoqvcjohavsyxnknldhi","zbmleyboikxcwmjllgluxnqcnohsytdbkzcvfmtuvitinzicizwe","uajfsyieqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","cmrrlejhsuxropsqfpawomacvkhbpbifnpxzejsvvkbrfqnfefat","ahoauuvvovvfimmkbwnlpvasgqnmomnuvkggbjgstqxlkwldyfgf","ctzorofbvheiwiazzmcfqczhlbuqimqnchlhergqrshrrtpofqmj","mwjgnrodffujrinyqhkhisqlavjotoqcpecwnesfuudfsozzgbig","imyfwoulzwtxlofepiliqaetjytplplzxbldhugnskffkksamhtv","dqqalrrlcghhzgotvcovvypmgqdjgqqfhpnypuifsfxsgbmjztnz","wqwtwdqcdmwfaoranzbrpkanvguiojozwxkwlzasqalumuxbmvwo","bnebeaskzpmstyoghdfcctxuwsudqhbthcnqcptltpscfvxjnodd","rdgxgeovqjulfjdhmsivuinhxihzsdoqfjbhtycgqlsxeqmcwnhf","rofccdzelancofwtbpkghsjworvhxisnzbwnbhbzodggfdogxzjj","gnyycccgurdxiofsmgguuvvaymcdvavnjfulatreifnziswudqtm","fshiyitjauhsbfdxvgzqihdzlnovvrejuvubioqlyvxpdfhpsuvv","anodrupirgjahapxncxubyfebxrcftjzrxiqecnjcgrezapgvyhr","uajffjieqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","dxwzsqaddbnybqrusowfhjalbqizbpticefwpivgyjmsertayxqm","wgpjnwhguzcmlgfpszvlkcoonhnzsijnksxdkednnxseghmuwqbs","piwjbbsctzikmeedrbrlpjsbjlyrkykypjhtvzchsacwdplffdqq","uajfsjirqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","ryygnlvwelqygenflpzyckkvqtqqvtjexsdtjhhahasvuctqjibq"};
        String[] dictionary = new String[]{"ryygnlvweloygenflpzyckkvqtqqvtjexsdtjhhahasvuctqjibq","uajfsjieqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","zbmleyboikxcwqjllgluxnqcnohsytdbkzcvfmtuvitinzicizwe","aoxqockwtrbodtkefuwusiqvgyvrkfluiexyvvbuylsbgkdmpsas","bnebeaskzpmstyoghdfcctxuwsudqhbthcnqcptltpscfvxjnpdd","kmjqtzrnvtwjlfubhfbduqdnkpeeyamciirdvdyxmbftapgmebzm","qvywvdgayzpnwdakpvwcqrfkocilpjavfszuonfulnllrvfhuugq","dqqalrrlcghhzgotvcovvypmgadjgqqfhpnypuifsfxsgbmjztnz","imyfwoulzwtxlofepiliqaetkytplplzxbldhugnskffkksamhtv","fjrmkghkioiycvvjwserwrnfeghdekhkrlzqkgezblhwgnxkvecw","rdgxgeovqjulfjdhmsivuinhxihzsdoqfjbhtycgqlsxeqscwnhf","hcnopppwanukcyqredialgmdmgnlmywgcqhtnbccdcqinqvgtnwp"};
        List<String> expected = Lists.newArrayList("aoxqockwtrbodtkefuwusiqvgyvrkfluiexyvvbuylsbgkdmpsfs","fjrmkghkioiycvvkwserwrnfeghdekhkrlzqkgezblhwgnxkvecw","qvywvdgtyzpnwdakpvwcqrfkocilpjavfszuonfulnllrvfhuugq","moxqockwtrbodtkefuwusiqvgyvrkfluiexyvvbuylsbgkdmpsas","kmjqtzrnvtwjlfubhfbduqdnkpeeyamciirdvdyxmbftgpgmebzm","aoxqockwtrbodtkefuwusiqvgyvrkfluiexyvvfuylsbgkdmpsas","zbmleyboikxcwmjllgluxnqcnohsytdbkzcvfmtuvitinzicizwe","uajfsyieqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","imyfwoulzwtxlofepiliqaetjytplplzxbldhugnskffkksamhtv","dqqalrrlcghhzgotvcovvypmgqdjgqqfhpnypuifsfxsgbmjztnz","bnebeaskzpmstyoghdfcctxuwsudqhbthcnqcptltpscfvxjnodd","rdgxgeovqjulfjdhmsivuinhxihzsdoqfjbhtycgqlsxeqmcwnhf","uajffjieqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","uajfsjirqxnbxugfsypwlnxoebzpyxbdyjhpmivfzyzbpkbcaglx","ryygnlvwelqygenflpzyckkvqtqqvtjexsdtjhhahasvuctqjibq");
        assertIterableEquals(expected, test.twoEditWords(query, dictionary));
    }

    @Test
    public void testExactMatchIncluded() {
        String[] query = new String[]{"abc", "xyz"};
        String[] dictionary = new String[]{"abc"};
        assertIterableEquals(Lists.newArrayList("abc"), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testExactlyTwoDifferencesIncluded() {
        String[] query = new String[]{"abcd"};
        String[] dictionary = new String[]{"abxy"};
        assertIterableEquals(Lists.newArrayList("abcd"), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testThreeDifferencesExcluded() {
        String[] query = new String[]{"abcd"};
        String[] dictionary = new String[]{"wxyz"};
        assertIterableEquals(Lists.newArrayList(), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testMultipleDictionaryCandidatesAnyMatchWorks() {
        String[] query = new String[]{"aaaa", "bbbb"};
        String[] dictionary = new String[]{"aaab", "bbbc"};
        assertIterableEquals(Lists.newArrayList("aaaa", "bbbb"), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testOrderPreserved() {
        String[] query = new String[]{"aaaa", "cccc", "bbbb"};
        String[] dictionary = new String[]{"aaab", "bbba"};
        assertIterableEquals(Lists.newArrayList("aaaa", "bbbb"), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testDuplicateQueriesRetained() {
        String[] query = new String[]{"code", "code", "coda"};
        String[] dictionary = new String[]{"coda"};
        assertIterableEquals(Lists.newArrayList("code", "code", "coda"), test.twoEditWords(query, dictionary));
    }

    @Test
    public void testGiantManyQueriesSmallDictionary() {
        String[] query = new String[200];
        for (int i = 0; i < 200; i++) {
            query[i] = "aaaa";
        }
        String[] dictionary = new String[]{"aaab"};
        List<String> out = test.twoEditWords(query, dictionary);
        assertIterableEquals(Lists.newArrayList(query), out);
    }
}
