package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FindItinerary_332Test {

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

    @Test
    public void testEmptyTickets() {
        assertEquals(List.of(), test.findItinerary(List.of()));
    }

    @Test
    public void testLexicalOrder() {
        // JFK -> ATL and JFK -> BOS, should pick ATL first
        assertEquals(List.of("JFK", "ATL", "JFK", "BOS"),
            test.findItinerary(List.of(List.of("JFK", "ATL"), List.of("JFK", "BOS"), List.of("ATL", "JFK"))));
    }

    @Test
    public void testRoundTrip() {
        assertEquals(List.of("JFK", "A", "JFK"),
            test.findItinerary(List.of(List.of("JFK", "A"), List.of("A", "JFK"))));
    }

    @Test
    public void testMultipleEdgesSameDestination() {
        assertEquals(List.of("JFK", "A", "JFK", "A", "B"),
            test.findItinerary(List.of(List.of("JFK", "A"), List.of("JFK", "A"), List.of("A", "JFK"), List.of("A", "B"))));
    }

    @Test
    public void testThreeWayBranch() {
        // JFK -> A, A -> B, A -> C, B -> A => JFK, A, B, A, C
        assertEquals(List.of("JFK", "A", "B", "A", "C"),
            test.findItinerary(List.of(List.of("JFK", "A"), List.of("A", "B"), List.of("B", "A"), List.of("A", "C"))));
    }

    @Test
    public void testSelfLoop() {
        assertEquals(List.of("JFK", "JFK", "A"),
            test.findItinerary(List.of(List.of("JFK", "JFK"), List.of("JFK", "A"))));
    }

    @Test
    public void testGiantCase() {
        // Chain: JFK -> A0 -> A1 -> ... -> A49
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(List.of("JFK", "A00"));
        for (int i = 0; i < 49; i++) {
            tickets.add(List.of(String.format("A%02d", i), String.format("A%02d", i + 1)));
        }
        List<String> result = test.findItinerary(tickets);
        assertEquals(51, result.size());
        assertEquals("JFK", result.get(0));
        assertEquals("A49", result.get(50));
    }
}
