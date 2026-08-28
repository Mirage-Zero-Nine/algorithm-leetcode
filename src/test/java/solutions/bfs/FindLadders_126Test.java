package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2022/10/11 21:14
 * Created with IntelliJ IDEA
 */

public class FindLadders_126Test {

    private final FindLadders_126 test = new FindLadders_126();

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("hit", "hot", "dot", "dog", "cog"),
                Lists.newArrayList("hit", "hot", "lot", "log", "cog"));
        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void test1() {
        List<String> list = Lists.newArrayList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("red", "ted", "tad", "tax"),
                Lists.newArrayList("red", "ted", "tex", "tax"),
                Lists.newArrayList("red", "rex", "tex", "tax"));
        assertAllApproaches(expected, "red", "tax", list);
    }

    @Test
    public void test2() {
        List<String> list = Lists.newArrayList("hot", "dot", "dog", "lot", "cog");
        List<String> temp = Lists.newArrayList("hit", "hot", "dot", "dog", "cog");
        List<List<String>> expected = Lists.newArrayList();
        expected.add(temp);
        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testMini() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<String> temp = Lists.newArrayList("a", "c");
        List<List<String>> expected = Lists.newArrayList();
        expected.add(temp);
        assertAllApproaches(expected, "a", "c", list);
    }

    @Test
    public void testLargeWordListBeyondConstraintIsStillHandled() {
        List<String> list = Lists.newArrayList(
                "kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val",
                "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry",
                "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad",
                "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out",
                "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh",
                "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san",
                "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb",
                "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip",
                "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex",
                "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask",
                "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy",
                "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web",
                "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era",
                "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm",
                "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan",
                "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila",
                "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid",
                "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox",
                "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see",
                "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod",
                "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg",
                "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due",
                "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye",
                "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado",
                "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep",
                "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog",
                "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put",
                "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel",
                "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din",
                "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei",
                "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con",
                "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted",
                "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad",
                "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina",
                "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han",
                "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig",
                "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho",
                "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe",
                "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air",
                "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob");
        // Defensive stress case: this fixture intentionally contains 599 words,
        // exceeding LeetCode's current 500-word constraint.
        assertTrue(list.size() > 500);
        List<List<String>> expected = Lists.newArrayList(
                Lists.newArrayList("cet", "get", "gee", "gte", "ate", "ats", "its", "ito", "ibo", "ibm", "ism"),
                Lists.newArrayList("cet", "cat", "can", "ian", "inn", "ins", "its", "ito", "ibo", "ibm", "ism"),
                Lists.newArrayList("cet", "cot", "con", "ion", "inn", "ins", "its", "ito", "ibo", "ibm", "ism"));
        assertAllApproaches(expected, "cet", "ism", list);
    }

    @Test
    public void testLarge1() {
        List<String> list = Lists.newArrayList(
                "aaaaa", "caaaa", "cbaaa", "daaaa", "dbaaa", "eaaaa", "ebaaa", "faaaa", "fbaaa", "gaaaa", "gbaaa", "haaaa",
                "hbaaa", "iaaaa", "ibaaa", "jaaaa", "jbaaa", "kaaaa", "kbaaa", "laaaa", "lbaaa", "maaaa", "mbaaa", "naaaa", "nbaaa",
                "oaaaa", "obaaa", "paaaa", "pbaaa", "bbaaa", "bbcaa", "bbcba", "bbdaa", "bbdba", "bbeaa", "bbeba", "bbfaa", "bbfba",
                "bbgaa", "bbgba", "bbhaa", "bbhba", "bbiaa", "bbiba", "bbjaa", "bbjba", "bbkaa", "bbkba", "bblaa", "bblba", "bbmaa",
                "bbmba", "bbnaa", "bbnba", "bboaa", "bboba", "bbpaa", "bbpba", "bbbba", "abbba", "acbba", "dbbba", "dcbba", "ebbba",
                "ecbba", "fbbba", "fcbba", "gbbba", "gcbba", "hbbba", "hcbba", "ibbba", "icbba", "jbbba", "jcbba", "kbbba", "kcbba",
                "lbbba", "lcbba", "mbbba", "mcbba", "nbbba", "ncbba", "obbba", "ocbba", "pbbba", "pcbba", "ccbba", "ccaba", "ccaca",
                "ccdba", "ccdca", "cceba", "cceca", "ccfba", "ccfca", "ccgba", "ccgca", "cchba", "cchca", "cciba", "ccica", "ccjba",
                "ccjca", "cckba", "cckca", "cclba", "cclca", "ccmba", "ccmca", "ccnba", "ccnca", "ccoba", "ccoca", "ccpba", "ccpca",
                "cccca", "accca", "adcca", "bccca", "bdcca", "eccca", "edcca", "fccca", "fdcca", "gccca", "gdcca", "hccca", "hdcca",
                "iccca", "idcca", "jccca", "jdcca", "kccca", "kdcca", "lccca", "ldcca", "mccca", "mdcca", "nccca", "ndcca", "occca",
                "odcca", "pccca", "pdcca", "ddcca", "ddaca", "ddada", "ddbca", "ddbda", "ddeca", "ddeda", "ddfca", "ddfda", "ddgca",
                "ddgda", "ddhca", "ddhda", "ddica", "ddida", "ddjca", "ddjda", "ddkca", "ddkda", "ddlca", "ddlda", "ddmca", "ddmda",
                "ddnca", "ddnda", "ddoca", "ddoda", "ddpca", "ddpda", "dddda", "addda", "aedda", "bddda", "bedda", "cddda", "cedda",
                "fddda", "fedda", "gddda", "gedda", "hddda", "hedda", "iddda", "iedda", "jddda", "jedda", "kddda", "kedda", "lddda",
                "ledda", "mddda", "medda", "nddda", "nedda", "oddda", "oedda", "pddda", "pedda", "eedda", "eeada", "eeaea", "eebda",
                "eebea", "eecda", "eecea", "eefda", "eefea", "eegda", "eegea", "eehda", "eehea", "eeida", "eeiea", "eejda", "eejea",
                "eekda", "eekea", "eelda", "eelea", "eemda", "eemea", "eenda", "eenea", "eeoda", "eeoea", "eepda", "eepea", "eeeea",
                "ggggg", "agggg", "ahggg", "bgggg", "bhggg", "cgggg", "chggg", "dgggg", "dhggg", "egggg", "ehggg", "fgggg", "fhggg",
                "igggg", "ihggg", "jgggg", "jhggg", "kgggg", "khggg", "lgggg", "lhggg", "mgggg", "mhggg", "ngggg", "nhggg", "ogggg",
                "ohggg", "pgggg", "phggg", "hhggg", "hhagg", "hhahg", "hhbgg", "hhbhg", "hhcgg", "hhchg", "hhdgg", "hhdhg", "hhegg",
                "hhehg", "hhfgg", "hhfhg", "hhigg", "hhihg", "hhjgg", "hhjhg", "hhkgg", "hhkhg", "hhlgg", "hhlhg", "hhmgg", "hhmhg",
                "hhngg", "hhnhg", "hhogg", "hhohg", "hhpgg", "hhphg", "hhhhg", "ahhhg", "aihhg", "bhhhg", "bihhg", "chhhg", "cihhg",
                "dhhhg", "dihhg", "ehhhg", "eihhg", "fhhhg", "fihhg", "ghhhg", "gihhg", "jhhhg", "jihhg", "khhhg", "kihhg", "lhhhg",
                "lihhg", "mhhhg", "mihhg", "nhhhg", "nihhg", "ohhhg", "oihhg", "phhhg", "pihhg", "iihhg", "iiahg", "iiaig", "iibhg",
                "iibig", "iichg", "iicig", "iidhg", "iidig", "iiehg", "iieig", "iifhg", "iifig", "iighg", "iigig", "iijhg", "iijig",
                "iikhg", "iikig", "iilhg", "iilig", "iimhg", "iimig", "iinhg", "iinig", "iiohg", "iioig", "iiphg", "iipig", "iiiig",
                "aiiig", "ajiig", "biiig", "bjiig", "ciiig", "cjiig", "diiig", "djiig", "eiiig", "ejiig", "fiiig", "fjiig", "giiig",
                "gjiig", "hiiig", "hjiig", "kiiig", "kjiig", "liiig", "ljiig", "miiig", "mjiig", "niiig", "njiig", "oiiig", "ojiig",
                "piiig", "pjiig", "jjiig", "jjaig", "jjajg", "jjbig", "jjbjg", "jjcig", "jjcjg", "jjdig", "jjdjg", "jjeig", "jjejg",
                "jjfig", "jjfjg", "jjgig", "jjgjg", "jjhig", "jjhjg", "jjkig", "jjkjg", "jjlig", "jjljg", "jjmig", "jjmjg", "jjnig",
                "jjnjg", "jjoig", "jjojg", "jjpig", "jjpjg", "jjjjg", "ajjjg", "akjjg", "bjjjg", "bkjjg", "cjjjg", "ckjjg", "djjjg",
                "dkjjg", "ejjjg", "ekjjg", "fjjjg", "fkjjg", "gjjjg", "gkjjg", "hjjjg", "hkjjg", "ijjjg", "ikjjg", "ljjjg", "lkjjg",
                "mjjjg", "mkjjg", "njjjg", "nkjjg", "ojjjg", "okjjg", "pjjjg", "pkjjg", "kkjjg", "kkajg", "kkakg", "kkbjg", "kkbkg",
                "kkcjg", "kkckg", "kkdjg", "kkdkg", "kkejg", "kkekg", "kkfjg", "kkfkg", "kkgjg", "kkgkg", "kkhjg", "kkhkg", "kkijg",
                "kkikg", "kkljg", "kklkg", "kkmjg", "kkmkg", "kknjg", "kknkg", "kkojg", "kkokg", "kkpjg", "kkpkg", "kkkkg", "ggggx",
                "gggxx", "ggxxx", "gxxxx", "xxxxx", "xxxxy", "xxxyy", "xxyyy", "xyyyy", "yyyyy", "yyyyw", "yyyww", "yywww", "ywwww",
                "wwwww", "wwvww", "wvvww", "vvvww", "vvvwz", "avvwz", "aavwz", "aaawz", "aaaaz");

        List<String> tmp = Lists.newArrayList("aaaaa", "aaaaz", "aaawz", "aavwz", "avvwz", "vvvwz", "vvvww", "wvvww", "wwvww", "wwwww", "ywwww", "yywww", "yyyww", "yyyyw", "yyyyy", "xyyyy", "xxyyy", "xxxyy", "xxxxy", "xxxxx", "gxxxx", "ggxxx", "gggxx", "ggggx", "ggggg");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(tmp);
        }};
        assertAllApproaches(expected, "aaaaa", "ggggg", list);
    }

    @Test
    public void testNoPath() {
        List<String> list = Lists.newArrayList("hot", "dot", "dog", "lot", "log");
        List<List<String>> expected = Lists.newArrayList();
        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testBeginEqualsEndIsHandledDefensively() {
        // beginWord != endWord is a LeetCode constraint; this checks local input handling.
        List<String> list = Lists.newArrayList("hot", "dot");
        List<List<String>> expected = Lists.newArrayList();
        assertAllApproaches(expected, "hot", "hot", list);
    }

    @Test
    public void testDirectTransform() {
        List<String> list = Lists.newArrayList("hot");
        List<String> path = Lists.newArrayList("hit", "hot");
        List<List<String>> expected = new ArrayList<>();
        expected.add(path);
        assertAllApproaches(expected, "hit", "hot", list);
    }

    @Test
    public void testEndNotInList() {
        List<String> list = Lists.newArrayList("hot", "dot", "dog");
        List<List<String>> expected = Lists.newArrayList();
        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testEmptyWordList() {
        // LeetCode requires at least one dictionary word; this is a defensive case.
        List<String> list = Lists.newArrayList();
        List<List<String>> expected = Lists.newArrayList();
        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testConvergingShortestPaths() {
        List<String> list = List.of("baa", "aba", "bba", "bbb");
        List<List<String>> expected = List.of(
                List.of("aaa", "baa", "bba", "bbb"),
                List.of("aaa", "aba", "bba", "bbb"));

        assertAllApproaches(expected, "aaa", "bbb", list);
    }

    @Test
    public void testAllThreePositionPermutationsAreReturned() {
        List<String> list = List.of("aab", "aba", "baa", "abb", "bab", "bba", "bbb");
        List<List<String>> expected = List.of(
                List.of("aaa", "aab", "abb", "bbb"),
                List.of("aaa", "aab", "bab", "bbb"),
                List.of("aaa", "aba", "abb", "bbb"),
                List.of("aaa", "aba", "bba", "bbb"),
                List.of("aaa", "baa", "bab", "bbb"),
                List.of("aaa", "baa", "bba", "bbb"));

        assertAllApproaches(expected, "aaa", "bbb", list);
    }

    @Test
    public void testLongerCompletePathsAreExcluded() {
        List<String> list = List.of("aab", "abb", "aac", "acc", "bcc", "bbc", "bbb");
        List<List<String>> expected = List.of(
                List.of("aaa", "aab", "abb", "bbb"));

        // aaa -> aac -> acc -> bcc -> bbc -> bbb is valid, but is not shortest.
        assertAllApproaches(expected, "aaa", "bbb", list);
    }

    @Test
    public void testAllFourPositionPermutationsAreReturnedExactlyOnce() {
        List<String> list = List.of(
                "baaa", "abaa", "aaba", "aaab",
                "bbaa", "baba", "baab", "abba", "abab", "aabb",
                "bbba", "bbab", "babb", "abbb", "bbbb");

        // Each shortest path changes the four positions in one of 4! orders.
        assertValidPathsForAllApproaches("aaaa", "bbbb", list, 24, 5);
    }

    @Test
    public void testShortestPathCanUseEveryCharacterPosition() {
        List<String> list = List.of("baaa", "bbaa", "bbba", "bbbb");
        List<List<String>> expected = List.of(
                List.of("aaaa", "baaa", "bbaa", "bbba", "bbbb"));

        assertAllApproaches(expected, "aaaa", "bbbb", list);
    }

    @Test
    public void testDirectTransformationWinsOverIrrelevantWords() {
        List<String> list = List.of("hot", "hut", "zzz", "aaa");
        List<List<String>> expected = List.of(
                List.of("hit", "hot"));

        assertAllApproaches(expected, "hit", "hot", list);
    }

    @Test
    public void testEndWordAloneCannotBridgeTheGapWithoutIntermediate() {
        assertAllApproaches(List.of(), "hit", "cog", List.of("cog"));
    }

    @Test
    public void testMissingIntermediateWordMakesTheTargetUnreachable() {
        List<String> list = List.of("cog", "dog");

        // Neither word provides a valid first hop from hit.
        assertAllApproaches(List.of(), "hit", "cog", list);
    }

    @Test
    public void testDisconnectedTargetComponentDoesNotProduceAPath() {
        List<String> list = List.of("hot", "dot", "cog", "cod", "cad");

        assertAllApproaches(List.of(), "hit", "cog", list);
    }

    @Test
    public void testBeginWordMayAppearInTheWordList() {
        List<String> list = List.of("hit", "hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> expected = List.of(
                List.of("hit", "hot", "dot", "dog", "cog"),
                List.of("hit", "hot", "lot", "log", "cog"));

        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testDuplicateWordListEntriesAreIgnored() {
        // Word-list entries are required to be unique; this is a defensive case.
        List<String> list = new ArrayList<>(List.of("hot", "hot", "dot", "dog", "cog", "cog"));
        List<List<String>> expected = List.of(
                List.of("hit", "hot", "dot", "dog", "cog"));

        assertAllApproaches(expected, "hit", "cog", list);
    }

    @Test
    public void testWordListOrderDoesNotAffectTheSetOfPaths() {
        List<String> forward = List.of("hot", "dot", "dog", "lot", "log", "cog");
        List<String> reverse = List.of("cog", "log", "lot", "dog", "dot", "hot");
        List<List<String>> expected = List.of(
                List.of("hit", "hot", "dot", "dog", "cog"),
                List.of("hit", "hot", "lot", "log", "cog"));

        assertAllApproaches(expected, "hit", "cog", forward);
        assertAllApproaches(expected, "hit", "cog", reverse);
    }

    @Test
    public void testNullInputsReturnNoPaths() {
        // Null values are outside the lowercase-word constraints; this is defensive coverage.
        List<String> list = List.of("hit", "hot", "cog");

        assertAllApproaches(List.of(), null, "cog", list);
        assertAllApproaches(List.of(), "hit", null, list);
        assertAllApproaches(List.of(), "hit", "cog", null);
    }

    @Test
    public void testEmptyWordsReturnNoPaths() {
        // Word lengths must be at least one; this is defensive coverage.
        List<String> list = List.of("a", "b");

        assertAllApproaches(List.of(), "", "b", list);
        assertAllApproaches(List.of(), "a", "", list);
    }

    @Test
    public void testDifferentBeginAndEndWordLengthsReturnNoPaths() {
        // Equal begin/end lengths are required; this is defensive coverage.
        assertAllApproaches(List.of(), "a", "bb", List.of("bb"));
    }

    @Test
    public void testDifferentLengthDictionaryWordsCannotBeUsedAsHops() {
        // Dictionary words must match beginWord's length; this is defensive coverage.
        List<String> list = List.of("h", "hot", "cog");

        assertAllApproaches(List.of(), "hit", "cog", list);
    }

    @Test
    public void testNullDictionaryEntryDoesNotCreateAPath() {
        // Dictionary entries must be lowercase words; null is an invalid entry.
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add(null);
        list.add("cog");

        assertAllApproaches(List.of(), "hit", "cog", list);
    }

    @Test
    public void testBeginEqualsEndIsHandledAsAnInvalidRequest() {
        // beginWord != endWord is a LeetCode constraint; this is defensive coverage.
        assertAllApproaches(List.of(), "same", "same", List.of("same"));
    }

    @Test
    public void testInputWordListIsNotModified() {
        List<String> list = new ArrayList<>(List.of("hot", "dot", "dog", "cog"));
        List<String> original = new ArrayList<>(list);

        test.findLadders("hit", "cog", list);
        test.findLaddersWithPatternIndexing("hit", "cog", list);
        test.findLaddersBidirectional("hit", "cog", list);

        assertEquals(original, list);
    }

    @Test
    public void testRepeatedCallsDoNotShareSearchState() {
        List<List<String>> firstExpected = List.of(
                List.of("hit", "hot", "dot", "dog", "cog"));
        List<List<String>> secondExpected = List.of(
                List.of("a", "c"));

        assertAllApproaches(firstExpected, "hit", "cog", List.of("hot", "dot", "dog", "cog"));
        assertAllApproaches(secondExpected, "a", "c", List.of("b", "c"));
    }

    private void assertAllApproaches(
            List<List<String>> expected,
            String begin,
            String end,
            List<String> wordList) {
        assertPaths(expected, test.findLadders(begin, end, wordList));
        assertPaths(expected, test.findLaddersWithPatternIndexing(begin, end, wordList));
        assertPaths(expected, test.findLaddersBidirectional(begin, end, wordList));
    }

    private void assertValidPathsForAllApproaches(
            String begin,
            String end,
            List<String> wordList,
            int expectedCount,
            int expectedPathLength) {
        assertValidPaths(
                test.findLadders(begin, end, wordList),
                begin,
                end,
                wordList,
                expectedCount,
                expectedPathLength);
        assertValidPaths(
                test.findLaddersWithPatternIndexing(begin, end, wordList),
                begin,
                end,
                wordList,
                expectedCount,
                expectedPathLength);
        assertValidPaths(
                test.findLaddersBidirectional(begin, end, wordList),
                begin,
                end,
                wordList,
                expectedCount,
                expectedPathLength);
    }

    private void assertPaths(List<List<String>> expected, List<List<String>> actual) {
        // The problem does not specify an order for the returned sequences.
        assertEquals(expected.size(), actual.size());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    private void assertValidPaths(
            List<List<String>> actual,
            String begin,
            String end,
            List<String> wordList,
            int expectedCount,
            int expectedPathLength) {
        Set<String> dictionary = new HashSet<>(wordList);
        assertEquals(expectedCount, actual.size());
        assertEquals(expectedCount, new HashSet<>(actual).size());

        for (List<String> path : actual) {
            assertEquals(expectedPathLength, path.size());
            assertEquals(begin, path.get(0));
            assertEquals(end, path.get(path.size() - 1));
            for (int i = 1; i < path.size(); i++) {
                assertTrue(dictionary.contains(path.get(i)));
                assertTrue(differsByExactlyOneCharacter(path.get(i - 1), path.get(i)));
            }
        }
    }

    private boolean differsByExactlyOneCharacter(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int differences = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                differences++;
            }
        }
        return differences == 1;
    }
}
