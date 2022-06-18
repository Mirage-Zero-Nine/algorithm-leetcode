package solution.datastructure;

import java.util.HashMap;

/**
 * TinyURL is a URL shortening service.
 * You enter a URL such as leetcode.com/problems/design-tinyurl and returns a URL such as tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * @author BorisMirage
 * Time: 2020/02/03 17:02
 * Created with IntelliJ IDEA
 */

public class Codec_535 {
    private final HashMap<String, String> tinyUrlMap = new HashMap<>();
    private final HashMap<String, String> longUrlMap = new HashMap<>();

    /**
     * Use hash code and system nano time as the key of tiny URL.
     *
     * @param longUrl long URL
     * @return encoded tiny url
     */
    public String encode(String longUrl) {

        if (longUrlMap.containsKey(longUrl)) {
            return longUrlMap.get(longUrl);
        }

        String prefix = "https://tinyurl.com/";
        String tinyUrl = prefix + (longUrl.hashCode() + System.nanoTime());     // generate code and avoid collision
        tinyUrlMap.put(tinyUrl, longUrl);
        longUrlMap.put(longUrl, tinyUrl);

        return tinyUrl;
    }

    /**
     * Get the original URL based on encoded tiny URL.
     *
     * @param shortUrl tiny URL
     * @return original URL
     */
    public String decode(String shortUrl) {
        return tinyUrlMap.get(shortUrl);
    }
}
