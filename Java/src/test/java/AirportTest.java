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
    private static final MilitaryType FIGHTER_MILITARY_TYPE = MilitaryType.FIGHTER;
    private static final ExperimentalType VTOL_EXPERIMENTAL_TYPE = ExperimentalType.VTOL;
    private static final ExperimentalType HIGH_ALTITUDE_EXPERIMENTAL_TYPE = ExperimentalType.HIGH_ALTITUDE;
    private static final ExperimentalType LIFTING_BODY_EXPERIMENTAL_TYPE = ExperimentalType.LIFTING_BODY;
    private static final ClassificationLevelType CONFIDENTIAL_EXPERIMENTAL_CLASSIFICATION_LEVEL_TYPE = ClassificationLevelType.CONFIDENTIAL;
    private static final ClassificationLevelType UNCLASSIFIED_EXPERIMENTAL_CLASSIFICATION_LEVEL_TYPE = ClassificationLevelType.UNCLASSIFIED;
    private static final MilitaryPlane BOMBER_MILITARY_PLANE =
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER);

    @Test
    public void testHasNotExperimentalPlanesWithClassificationLevelTypeWithUnclassifiedType(){
        Assert.assertFalse(new Airport(planes).getClassificationLevelsInExperimentalPlanes().contains(UNCLASSIFIED_EXPERIMENTAL_CLASSIFICATION_LEVEL_TYPE));
    }

    @Test
    public void testHasNotExperimentalPlanesWithClassificationLevelTypeWithConfidentialType(){
        Assert.assertFalse(new Airport(planes).getClassificationLevelsInExperimentalPlanes().contains(CONFIDENTIAL_EXPERIMENTAL_CLASSIFICATION_LEVEL_TYPE));
    }

    @Test
    public void testHasVtolExperimentalPlanes() {
        Assert.assertFalse(new Airport(planes).getExperimentalPlanesByCertainType(VTOL_EXPERIMENTAL_TYPE).isEmpty());
    }

    @Test
    public void testHasHighAltitudeExperimentalPlanes() {
        Assert.assertFalse(new Airport(planes).getExperimentalPlanesByCertainType(HIGH_ALTITUDE_EXPERIMENTAL_TYPE).isEmpty());
    }

    @Test
    public void testHasZeroHighAltitudeExperimentalPlanes() {
        Assert.assertTrue(new Airport(planes).getExperimentalPlanesByCertainType(LIFTING_BODY_EXPERIMENTAL_TYPE).isEmpty());
    }

    @Test
    public void testHasExperimentalPlanes() {
        Assert.assertFalse(new Airport(planes).getExperimentalPlanes().isEmpty());
    }

    @Test
    public void testHasFighterMilitaryPlanes() {
        Assert.assertFalse(new Airport(planes).getMilitaryPlanesByCertainType(MilitaryType.FIGHTER).isEmpty());
    }

    @Test
    public void testHasMilitaryPlanes() {
        Assert.assertFalse(new Airport(planes).getMilitaryPlanes().isEmpty());
    }

    @Test
    public void testHasTransportMilitaryPlanes() {
        Assert.assertFalse(new Airport(planes).getMilitaryPlanesByCertainType(TRANSPORT_MILITARY_TYPE).isEmpty());
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Assert.assertTrue(new Airport(planes).getMilitaryPlanesByCertainType(BOMBER_MILITARY_TYPE).contains(BOMBER_MILITARY_PLANE));
    }

    @Test
    public void testHasBomberMilitaryPlanes() {
        Assert.assertFalse(new Airport(planes).getMilitaryPlanesByCertainType(FIGHTER_MILITARY_TYPE).isEmpty());
    }

    @Test
    public void testHasPassengerPlanes() {
        Assert.assertFalse(new Airport(planes).getPassengerPlanes().isEmpty());
    }

    @Test
    public void testComparePassengerPlaneWithMaxCapacity() {
        Assert.assertEquals(new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity(), PASSENGER_PLANE_WITH_MAX_PASSENGER_CAPACITY);
    }

    @Test
    public void testHasPlanes() {
        Assert.assertFalse(new Airport(planes).getPlanes().isEmpty());
    }

    @Test
    public void testSortExperimentalPlanesByMaxDistance() {
        Airport airport = new Airport(planes);
        airport.sortPlanesByMaxFlightDistance();
        List<? extends Plane> planesSortedByMaxFlightDistance = airport.getExperimentalPlanes();
        Assert.assertTrue(planesSortedByMaxFlightDistance.get(0).getMaxFlightDistance() <= planesSortedByMaxFlightDistance.get(1).getMaxFlightDistance());
    }

    @Test
    public void testSortExperimentalPlanesByMaxSpeed() {
        Airport airport = new Airport(planes);
        airport.sortPlanesByMaxSpeed();
        List<? extends Plane> planesSortedByByMaxSpeed = airport.getExperimentalPlanes();
        Assert.assertTrue(planesSortedByByMaxSpeed.get(0).getMaxSpeed() <= planesSortedByByMaxSpeed.get(1).getMaxSpeed());
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        Assert.assertTrue(planesSortedByMaxLoadCapacity.get(0).getMaxLoadCapacity() >= planesSortedByMaxLoadCapacity.get(1).getMaxLoadCapacity());
    }
}