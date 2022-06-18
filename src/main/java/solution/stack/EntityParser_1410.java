package solution.stack;

import java.util.HashMap;

/**
 * HTML entity parser takes HTML code as input and replace all the special characters by the characters itself.
 * The special characters and their entities for HTML are:
 * Quotation Mark: the entity is <code>&quot</code>; and symbol character is ".
 * Single Quote Mark: the entity is <code>&apos</code>; and symbol character is '.
 * Ampersand: the entity is <code>&amp</code>; and symbol character is &.
 * Greater Than Sign: the entity is <code>&gt</code>; and symbol character is >.
 * Less Than Sign: the entity is <code>&lt</code>; and symbol character is <.
 * Slash: the entity is <code>&frasl</code>; and symbol character is /.
 * Given the input text string to the HTML parser, you have to implement the entity parser.
 * Return the text after replacing the entities by the special characters.
 *
 * @author BorisMirage
 * Time: 2020/05/13 10:59
 * Created with IntelliJ IDEA
 */

public class EntityParser_1410 {
    /**
     * Two pointers with two stacks. In this case, use StringBuilder instead of stack for convenience.
     * One StringBuilder keeps the output of parsed string, the other one keeps the temporary string when meets '&'.
     * When meets '&', append all the chars after '&' into a StringBuilder until reaches the end of string or ';'.
     * Then check if the content exists in the map, which keeps the mapping between special chars and entities.
     *
     * @param text given text body
     * @return parsed string
     */
    public String entityParser(String text) {

        /* Corner case */
        if (text == null || text.length() == 0) {
            return "";
        }

        HashMap<String, String> m = new HashMap<>();
        m.put("&quot;", "\"");
        m.put("&apos;", "'");
        m.put("&amp;", "&");
        m.put("&gt;", ">");
        m.put("&lt;", "<");
        m.put("&frasl;", "/");

        StringBuilder sb = new StringBuilder();         // final output
        StringBuilder parse = new StringBuilder();      // temporary string when meets '&'

        int index = 0;

        while (index < text.length()) {
            if (text.charAt(index) != '&') {
                sb.append(text.charAt(index++));
            } else {
                while (index < text.length() && text.charAt(index) != ';') {
                    parse.append(text.charAt(index++));
                }
                if (index < text.length()) {
                    parse.append(text.charAt(index++));     // append ';' if there is space
                }

                String tmp = parse.toString();

                sb.append(m.getOrDefault(tmp, tmp));
                parse = new StringBuilder();        // empty the StringBuilder for next round
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new EntityParser_1410().entityParser("\"x &gt; y &amp;&amp; x &lt; y is always false\""));       // x > y && x < y is always false
        System.out.println(new EntityParser_1410().entityParser("&amp; is an HTML entity but &ambassador; is not."));       // & is an HTML entity but &ambassador; is not.
        System.out.println(new EntityParser_1410().entityParser("and I quote: &quot;...&quot;"));       // and I quote: "..."
        System.out.println(new EntityParser_1410().entityParser("Stay home! Practice on Leetcode :)"));     // Stay home! Practice on Leetcode :)
        System.out.println(new EntityParser_1410().entityParser("leetcode.com&frasl;problemset&frasl;all"));        // leetcode.com/problemset/all
        System.out.println(new EntityParser_1410().entityParser("&amp;gt;"));       // "&gt;"
    }
}
