import model.airport.Airport;
import model.plane.ExperimentalPlane;
import type.ClassificationLevelType;
import type.ExperimentalType;
import type.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import model.plane.MilitaryPlane;
import model.plane.PassengerPlane;
import model.plane.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static final List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevelType.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevelType.TOP_SECRET)
    );

    private static final PassengerPlane PASSENGER_PLANE_WITH_MAX_PASSENGER_CAPACITY =
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
    private static final MilitaryType TRANSPORT_MILITARY_TYPE = MilitaryType.TRANSPORT;
    private static final MilitaryType BOMBER_MILITARY_TYPE = MilitaryType.BOMBER;
    private static final ClassificationLevelType UNCLASSIFIED_EXPERIMENTAL_CLASSIFICATION_LEVEL = ClassificationLevelType.UNCLASSIFIED;
    private static final MilitaryPlane BOMBER_MILITARY_PLANE =
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER);

    @Test
    public void testHasTransportMilitaryPlanes() {
        Assert.assertFalse(new Airport(planes).getMilitaryPlanesByCertainType(TRANSPORT_MILITARY_TYPE).isEmpty());
    }

    @Test
    public void testComparePassengerPlaneWithMaxCapacity() {
        Assert.assertEquals(new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity(), PASSENGER_PLANE_WITH_MAX_PASSENGER_CAPACITY);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(0).getMaxLoadCapacity() >= planesSortedByMaxLoadCapacity.get(1).getMaxLoadCapacity());
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Assert.assertTrue(new Airport(planes).getMilitaryPlanesByCertainType(BOMBER_MILITARY_TYPE).contains(BOMBER_MILITARY_PLANE));
    }

    @Test
    public void experimentalPlanesHasClassificationLevelHigherThanUnclassifiedTest(){
        Assert.assertFalse(new Airport(planes).getClassificationLevelsInExperimentalPlanes().contains(UNCLASSIFIED_EXPERIMENTAL_CLASSIFICATION_LEVEL));
    }
}