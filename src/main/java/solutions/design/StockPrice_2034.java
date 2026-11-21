package solutions.design;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * You are given a stream of records about a particular stock.
 * Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order.
 * Even worse, some records may be incorrect.
 * Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 * Design an algorithm that:
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 * @author BorisMirage
 * Time: 2026/11/21 00:10
 * Created with IntelliJ IDEA
 */

public class StockPrice_2034 {
    private final NavigableMap<Integer, Integer> priceAtTime;
    private final NavigableMap<Integer, Integer> priceCount;

    /**
     * Initializes empty indexes for timestamp prices and live price counts.
     * <p>
     * The timestamp index lets an update replace the record already stored at that timestamp and
     * find the price at the greatest timestamp. The price-count index keeps prices ordered while
     * counting how many current records have each price, so its first and last keys are the minimum
     * and maximum. Updating both indexes removes corrected prices from the extrema when their last
     * timestamp stops using them.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public StockPrice_2034() {
        priceAtTime = new TreeMap<>();
        priceCount = new TreeMap<>();
    }

    /**
     * Updates one timestamp in both indexes, first removing its previous price count when the
     * timestamp was already present. Counts ensure that a price remains in the ordered extrema
     * index as long as at least one current timestamp still uses it.
     * <p>
     * Time complexity: O(log n + log d), where n is the number of timestamps and d is the number
     * of distinct current prices.
     * Auxiliary space: O(1) additional space, or O(1) new entries when the timestamp or price is new.
     *
     * @param timestamp timestamp whose current price is being set
     * @param price     new price at that timestamp
     */
    public void update(int timestamp, int price) {
        if (priceAtTime.containsKey(timestamp)) {
            int previousPrice = priceAtTime.get(timestamp);
            int previousPriceCount = priceCount.get(previousPrice);
            if (previousPriceCount == 1) {
                // Drop the key entirely so an obsolete price cannot become an extremum.
                priceCount.remove(previousPrice);
            } else {
                priceCount.put(previousPrice, previousPriceCount - 1);
            }
        }
        priceCount.put(price, priceCount.getOrDefault(price, 0) + 1);
        priceAtTime.put(timestamp, price);
    }

    /**
     * Returns the price recorded at the greatest timestamp in the current index.
     * <p>
     * Time complexity: O(log n).
     * Auxiliary space: O(1).
     *
     * @return price at the latest timestamp
     */
    public int current() {
        return priceAtTime.lastEntry().getValue();
    }

    /**
     * Returns the greatest key in the ordered counts of current prices.
     * <p>
     * Time complexity: O(log d), where d is the number of distinct current prices.
     * Auxiliary space: O(1).
     *
     * @return maximum current price
     */
    public int maximum() {
        return priceCount.lastKey();
    }

    /**
     * Returns the smallest key in the ordered counts of current prices.
     * <p>
     * Time complexity: O(log d), where d is the number of distinct current prices.
     * Auxiliary space: O(1).
     *
     * @return minimum current price
     */
    public int minimum() {
        return priceCount.firstKey();
    }
}
