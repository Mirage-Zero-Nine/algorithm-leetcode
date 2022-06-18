package solution.bfs;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list.
 * Find all shortest transformation sequence(s) from beginWord to endWord.
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 1. Return an empty list if there is no such transformation sequence.
 * 2. All words have the same length.
 * 3. All words contain only lowercase alphabetic characters.
 * 4. No duplicates in the word list. beginWord and endWord are non-empty and are different.
 *
 * @author BorisMirage
 * Time: 2019/05/29 10:59
 * Created with IntelliJ IDEA
 */

public class FindLadders_126 {
    /**
     * Use BFS to find if shortest path exists, and build graph mapping current word to previous word.
     * Then use DFS to search from end to beginning to construct each path.
     *
     * @param beginWord begin word
     * @param endWord   target word
     * @param wordList  middle words
     * @return all shortest transformation sequence(s) from beginWord to endWord
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        /* Corner case */
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return new LinkedList<>();
        }

        Set<String> set = new HashSet<>(wordList);
        HashMap<String, List<String>> m = new HashMap<>();
        List<List<String>> out = new LinkedList<>();

        if (bfs(beginWord, endWord, new HashSet<>(set), m)) {       // if shortest path exists
            List<String> tmp = new LinkedList<>();
            tmp.add(endWord);
            dfs(out, tmp, endWord, beginWord, m);       // search from end to begin
        }

        return out;
    }

    /**
     * BFS to build the graph. The graph is reversed, key is current word, and value is all words from previous level.
     *
     * @param begin      begin word
     * @param end        end word
     * @param dictionary all middle words
     * @param m          graph mapping current word to previous word
     * @return if the shortest path exists
     */
    private boolean bfs(String begin, String end, Set<String> dictionary, HashMap<String, List<String>> m) {
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        boolean success = false;
        while (!q.isEmpty()) {
            HashSet<String> level = new HashSet<>();
            int size = q.size();
            while (size-- > 0) {
                String current = q.poll();

                char[] tmp = current.toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    char original = tmp[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        tmp[j] = c;
                        String word = new String(tmp);      // next word
                        if (word.equals(end)) {
                            success = true;
                        }
                        if (c != original && dictionary.contains(word)) {
                            if (!level.contains(word)) {
                                List<String> next = new LinkedList<>();
                                next.add(current);
                                m.put(word, next);
                                q.offer(word);
                                level.add(word);
                            } else {
                                List<String> next = m.get(word);
                                next.add(current);
                                m.put(word, next);
                            }
                        }
                    }
                    tmp[j] = original;
                }
            }
            dictionary.removeAll(level);
        }

        return success;
    }

    /**
     * DFS to find all shortest paths based on pre-build graph.
     * Search starts from end word, and ends at begin word.
     *
     * @param out     output list
     * @param tmp     temporary list
     * @param current current word
     * @param end     end word
     * @param m       graph mapping current word to previous word
     */
    private void dfs(List<List<String>> out, List<String> tmp, String current, String end, HashMap<String, List<String>> m) {

        if (current.equals(end)) {
            out.add(new LinkedList<>(tmp));
            return;
        }

        List<String> next = m.get(current);
        for (String w : next) {
            tmp.add(0, w);
            dfs(out, tmp, w, end, m);
            tmp.remove(0);
        }
    }

    /**
     * Save each path while completing BFS using hash map and two hash sets.
     * Hash map stores the path from begin word to current word.
     * Hash set stores all words in given word list and all words during each layer of BFS (to avoid duplication).
     * Each time, if word poll out from queue can form up a new word in list, add new word to the end of each list.
     * Based on character of BFS, if end word is reached, the path will be the shortest.
     *
     * @param beginWord begin word
     * @param endWord   target word
     * @param wordList  middle words
     * @return all shortest transformation sequence(s) from beginWord to endWord
     */
    public List<List<String>> findLaddersBFS(String beginWord, String endWord, List<String> wordList) {

        /* Corner case */
        if (wordList == null || wordList.size() == 0) {
            return new LinkedList<>();
        }

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return new ArrayList<>();
        }

        Map<String, List<List<String>>> map = new HashMap<>(); // key: word; value: all the paths to this word
        List<String> begin = new ArrayList<String>(){{
            add(beginWord);
        }};
        map.put(beginWord, new ArrayList<>());
        map.get(beginWord).add(begin); // add initial path to the map: beginWord -> {{beginWord}}

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        boolean isFound = false; // break BFS when the target word is found

        while (!q.isEmpty() && !wordSet.isEmpty() && !isFound) {
            int size = q.size();
            Set<String> currentSet = new HashSet<>();

            for (int i = 0; i < size; i++) { // iterate all the words in the queue
                String currentWord = q.poll();
                List<List<String>> previousPaths = map.get(currentWord); // get all the paths to current word

                for (int j = 0; j < currentWord.length(); j++) {
                    char[] array = currentWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        array[j] = c;
                        String nextWord = new String(array);
                        isFound |= nextWord.equals(endWord);

                        if (wordSet.contains(nextWord) && !nextWord.equals(currentWord) && previousPaths != null) {
                            q.add(nextWord);
                            map.putIfAbsent(nextWord, new ArrayList<>());
                            currentSet.add(nextWord);
                            for (List<String> list : previousPaths) { // append next available word to the end of each path
                                List<String> newPath = new ArrayList<>(list);
                                newPath.add(nextWord);
                                map.get(nextWord).add(newPath);
                            }
                        }
                    }
                }
                map.remove(currentWord);
            }
            wordSet.removeAll(currentSet);
        }

        return map.get(endWord) == null ? new ArrayList<>() : map.get(endWord);
    }

    public static void main(String[] args) {
        String b = "hit";
        String e = "cog";
        FindLadders_126 test = new FindLadders_126();
        String[] tmp = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> l = Arrays.asList(tmp);
        System.out.println(test.findLadders(b, e, l));

        b = "a";
        e = "c";
        tmp = new String[]{"a", "b", "c"};
        l = Arrays.asList(tmp);
        System.out.println(test.findLadders(b, e, l));

        b = "red";
        e = "tax";
        tmp = new String[]{"ted", "tex", "red", "tax", "tad", "den", "rex", "pee"};
        l = Arrays.asList(tmp);
        System.out.println(test.findLadders(b, e, l));

        b = "hit";
        e = "cog";
        tmp = new String[]{"hot", "dot", "dog", "lot", "cog"};
        l = Arrays.asList(tmp);
        System.out.println(test.findLadders(b, e, l));

        b = "cet";
        e = "ism";
        tmp = new String[]{"kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"};
        l = Arrays.asList(tmp);
        System.out.println(test.findLadders(b, e, l));
    }
}
