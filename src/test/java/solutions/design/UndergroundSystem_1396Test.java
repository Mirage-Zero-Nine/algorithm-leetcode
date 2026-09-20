package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link UndergroundSystem_1396}.
 *
 * <p>The expected route totals in the generated tests use a separate route object and aggregate
 * map, rather than reproducing the implementation's string-key representation. This catches
 * collisions between distinct station-name pairs as well as arithmetic and state-transition
 * errors.</p>
 */
public class UndergroundSystem_1396Test {

    private static final double EPSILON = 1e-9;

    private record Route(String start, String end) {
    }

    private static final class Aggregate {
        private long total;
        private int count;

        private void add(long duration) {
            total += duration;
            count++;
        }

        private double average() {
            return (double) total / count;
        }
    }

    private static void assertAverage(UndergroundSystem_1396 system, String start, String end,
                                      double expected) {
        assertEquals(expected, system.getAverageTime(start, end), EPSILON,
                start + " -> " + end);
    }

    private static void addTrip(UndergroundSystem_1396 system, Map<Route, Aggregate> expected,
                                int id, String start, int checkInTime, String end,
                                int checkOutTime) {
        system.checkIn(id, start, checkInTime);
        system.checkOut(id, end, checkOutTime);
        expected.computeIfAbsent(new Route(start, end), ignored -> new Aggregate())
                .add((long) checkOutTime - checkInTime);
    }

    @Test
    public void officialExampleWithAverageUpdatedAfterReuse() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        system.checkIn(45, "Leyton", 3);
        system.checkIn(32, "Paradise", 8);
        system.checkIn(27, "Leyton", 10);
        system.checkOut(45, "Waterloo", 15);
        system.checkOut(27, "Waterloo", 20);
        system.checkOut(32, "Cambridge", 22);
        assertAverage(system, "Paradise", "Cambridge", 14.0);
        assertAverage(system, "Leyton", "Waterloo", 11.0);

        system.checkIn(10, "Leyton", 24);
        assertAverage(system, "Leyton", "Waterloo", 11.0);
        system.checkOut(10, "Waterloo", 38);
        assertAverage(system, "Leyton", "Waterloo", 12.0);
    }

    @Test
    public void oneTripAndZeroTimeOriginAreHandled() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 0, "B", 10);
        assertAverage(system, "A", "B", 10.0);
    }

    @Test
    public void missingRouteUsesDocumentedZeroResult() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        assertAverage(system, "Unknown", "Route", 0.0);
    }

    @Test
    public void concurrentPassengersAggregateInCheckoutOrder() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        system.checkIn(1, "A", 1);
        system.checkIn(2, "A", 2);
        system.checkIn(3, "A", 3);
        system.checkOut(2, "B", 12);
        system.checkOut(1, "B", 21);
        system.checkOut(3, "B", 33);
        assertAverage(system, "A", "B", (10.0 + 20.0 + 30.0) / 3.0);
    }

    @Test
    public void samePassengerCanMakeSequentialTrips() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        Map<Route, Aggregate> expected = new HashMap<>();
        addTrip(system, expected, 7, "A", 10, "B", 20);
        addTrip(system, expected, 7, "B", 25, "C", 40);
        addTrip(system, expected, 7, "C", 45, "A", 51);
        for (Map.Entry<Route, Aggregate> entry : expected.entrySet()) {
            assertAverage(system, entry.getKey().start(), entry.getKey().end(),
                    entry.getValue().average());
        }
    }

    @Test
    public void routesAreDirectional() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 11);
        addTrip(system, new HashMap<>(), 2, "B", 12, "A", 30);
        assertAverage(system, "A", "B", 10.0);
        assertAverage(system, "B", "A", 18.0);
    }

    @Test
    public void sameStationRouteIsDistinctAndValid() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "Station", 4, "Station", 9);
        assertAverage(system, "Station", "Station", 5.0);
        assertAverage(system, "Station", "Other", 0.0);
    }

    @Test
    public void routeKeyMustNotMergeConcatenationCollisions() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "AB", 1, "C", 6);
        addTrip(system, new HashMap<>(), 2, "A", 2, "BC", 12);
        assertAverage(system, "AB", "C", 5.0);
        assertAverage(system, "A", "BC", 10.0);
    }

    @Test
    public void stationNamesRemainCaseAndDigitSensitive() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A1", 1, "b2", 8);
        addTrip(system, new HashMap<>(), 2, "a1", 2, "B2", 13);
        assertAverage(system, "A1", "b2", 7.0);
        assertAverage(system, "a1", "B2", 11.0);
        assertAverage(system, "A1", "B2", 0.0);
    }

    @Test
    public void duplicateTripsCountSeparately() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 4);
        addTrip(system, new HashMap<>(), 2, "A", 5, "B", 15);
        addTrip(system, new HashMap<>(), 3, "A", 16, "B", 17);
        assertAverage(system, "A", "B", (3.0 + 10.0 + 1.0) / 3.0);
    }

    @Test
    public void averagesPreserveFractionalPrecision() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 2);
        addTrip(system, new HashMap<>(), 2, "A", 3, "B", 5);
        addTrip(system, new HashMap<>(), 3, "A", 6, "B", 9);
        assertAverage(system, "A", "B", 2.0);

        addTrip(system, new HashMap<>(), 4, "A", 10, "B", 14);
        assertAverage(system, "A", "B", 2.5);
    }

    @Test
    public void multipleIndependentRoutesDoNotContaminateEachOther() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        Map<Route, Aggregate> expected = new HashMap<>();
        addTrip(system, expected, 1, "North", 1, "South", 11);
        addTrip(system, expected, 2, "North", 2, "East", 22);
        addTrip(system, expected, 3, "West", 3, "South", 33);
        addTrip(system, expected, 4, "North", 4, "South", 24);
        for (Map.Entry<Route, Aggregate> entry : expected.entrySet()) {
            assertAverage(system, entry.getKey().start(), entry.getKey().end(),
                    entry.getValue().average());
        }
        assertAverage(system, "South", "North", 0.0);
    }

    @Test
    public void queryingDoesNotChangeFutureAggregates() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 11);
        assertAverage(system, "A", "B", 10.0);
        assertAverage(system, "A", "B", 10.0);
        addTrip(system, new HashMap<>(), 2, "A", 12, "B", 30);
        assertAverage(system, "A", "B", 14.0);
    }

    @Test
    public void checkedOutPassengerNoLongerAffectsLaterRoutes() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        system.checkIn(1, "A", 1);
        system.checkOut(1, "B", 5);
        system.checkIn(2, "A", 6);
        system.checkOut(2, "B", 16);
        assertAverage(system, "A", "B", 7.0);
    }

    @Test
    public void checkInsMayRemainActiveWhileOtherPassengersFinish() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        system.checkIn(1, "A", 1);
        system.checkIn(2, "C", 2);
        system.checkOut(1, "B", 10);
        assertAverage(system, "A", "B", 9.0);
        system.checkOut(2, "D", 20);
        assertAverage(system, "C", "D", 18.0);
    }

    @Test
    public void minimumPositiveDurationIsIncluded() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 2);
        assertAverage(system, "A", "B", 1.0);
    }

    @Test
    public void maximumDocumentedTimestampDifferenceIsIncluded() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "A", 1, "B", 1_000_000);
        assertAverage(system, "A", "B", 999_999.0);
    }

    @Test
    public void maximumTotalCallWorkloadDoesNotOverflowRouteTotal() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        int trips = 10_000;
        for (int id = 1; id <= trips; id++) {
            system.checkIn(id, "Start", 1);
            system.checkOut(id, "End", 1_000_000);
        }
        assertAverage(system, "Start", "End", 999_999.0);
    }

    @Test
    public void repeatedCallsWithDifferentInstancesRemainIndependent() {
        UndergroundSystem_1396 first = new UndergroundSystem_1396();
        UndergroundSystem_1396 second = new UndergroundSystem_1396();
        addTrip(first, new HashMap<>(), 1, "A", 1, "B", 6);
        addTrip(second, new HashMap<>(), 1, "A", 1, "B", 16);
        assertAverage(first, "A", "B", 5.0);
        assertAverage(second, "A", "B", 15.0);
    }

    @Test
    public void routeAggregatesCanBeCheckedAtSeveralIntermediatePoints() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        Map<Route, Aggregate> expected = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            addTrip(system, expected, i, "A", i * 10, "B", i * 10 + i);
            assertAverage(system, "A", "B", expected.get(new Route("A", "B")).average());
        }
    }

    @Test
    public void seededIndependentOracleCoversMixedRoutesAndOrdering() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        Map<Route, Aggregate> expected = new HashMap<>();
        String[] stations = {"A", "B", "C", "AB", "BC", "Z9"};
        Random random = new Random(1396L);
        int currentTime = 1;
        List<Route> observedRoutes = new ArrayList<>();
        for (int id = 1; id <= 1_200; id++) {
            String start = stations[random.nextInt(stations.length)];
            String end = stations[random.nextInt(stations.length)];
            int checkInTime = currentTime++;
            // Keep the monotonic event clock below the documented 1,000,000 timestamp limit.
            int duration = 1 + random.nextInt(400);
            int checkOutTime = checkInTime + duration;
            currentTime = checkOutTime + 1;
            addTrip(system, expected, id, start, checkInTime, end, checkOutTime);
            Route route = new Route(start, end);
            if (!observedRoutes.contains(route)) {
                observedRoutes.add(route);
            }
        }
        for (Route route : observedRoutes) {
            assertAverage(system, route.start(), route.end(), expected.get(route).average());
        }
    }

    @Test
    public void seededConcurrentTripsUseTheirOwnCheckInState() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        Map<Route, Aggregate> expected = new HashMap<>();
        Random random = new Random(42L);
        String[] starts = {"A", "B", "C"};
        String[] ends = {"X", "Y", "Z"};
        for (int id = 1; id <= 90; id++) {
            int checkInTime = id;
            system.checkIn(id, starts[id % starts.length], checkInTime);
        }
        for (int id = 1; id <= 90; id++) {
            String start = starts[id % starts.length];
            String end = ends[random.nextInt(ends.length)];
            int checkOutTime = 100 + id + random.nextInt(20);
            system.checkOut(id, end, checkOutTime);
            expected.computeIfAbsent(new Route(start, end), ignored -> new Aggregate())
                    .add((long) checkOutTime - id);
        }
        for (Map.Entry<Route, Aggregate> entry : expected.entrySet()) {
            assertAverage(system, entry.getKey().start(), entry.getKey().end(),
                    entry.getValue().average());
        }
    }

    @Test
    public void stationNamesAtLengthBoundaryRemainDistinct() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        String tenA = "AAAAAAAAAA";
        String tenB = "BBBBBBBBBB";
        addTrip(system, new HashMap<>(), 1, tenA, 1, tenB, 101);
        addTrip(system, new HashMap<>(), 2, tenB, 2, tenA, 202);
        assertAverage(system, tenA, tenB, 100.0);
        assertAverage(system, tenB, tenA, 200.0);
    }

    @Test
    public void averagesRemainStableAfterManyUnrelatedRoutes() {
        UndergroundSystem_1396 system = new UndergroundSystem_1396();
        addTrip(system, new HashMap<>(), 1, "Target", 1, "Destination", 8);
        for (int id = 2; id <= 101; id++) {
            addTrip(system, new HashMap<>(), id, "S" + id, id, "E" + id, id + 50);
        }
        assertAverage(system, "Target", "Destination", 7.0);
    }
}
