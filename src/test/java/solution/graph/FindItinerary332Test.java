package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindItinerary332Test {

    private final FindItinerary_332 test = new FindItinerary_332();

    @Test
    public void testHappyCases() {
        assertEquals(List.of("JFK", "MUC", "LHR", "SFO", "SJC"),
            test.findItinerary(List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"))));
        assertEquals(List.of("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"),
            test.findItinerary(List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK"), List.of("ATL", "SFO"))));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of("JFK", "A"), test.findItinerary(List.of(List.of("JFK", "A"))));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of("JFK", "A", "B", "C", "D"),
            test.findItinerary(List.of(List.of("JFK", "A"), List.of("A", "B"), List.of("B", "C"), List.of("C", "D"))));
    }
}
