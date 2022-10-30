package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/30 11:39
 * Created with IntelliJ IDEA
 */

public class TwoEditWords2452Test {

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
}