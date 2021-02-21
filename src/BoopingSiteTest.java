import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import org.junit.*;
import static org.junit.Assert.*;
import oop.ex3.spaceship.*;

public class BoopingSiteTest {
    private static BoopingSite testBoopingSite;
    private static Hotel[] testHotelArr;
    private static double posLatitude = 85;
    private static double posLongitude = 175;
    private static double negLatitude = -85;
    private static double negLongitude = -175;

    @BeforeClass
    public static void createTestObjects() {
        testHotelArr = HotelDataset.getHotels("hotels_dataset.txt");
        testBoopingSite = new BoopingSite("hotels_dataset.txt");
    }

    @Test //xxx
    public void testGetHotelsInCityByRating() {
        for (Hotel hotel : testHotelArr) {
            Hotel[] res = testBoopingSite.getHotelsInCityByRating(hotel.getCity());
            for (int i=1; i < res.length; i++) {
                assertTrue("TestGetHotelsInCityByRating failed. Problem: " + res[i-1].getCity() +
                        " ranked higher than " + res[i].getCity(), res[i].getStarRating() >= res[i].getStarRating()); //xxx
                if (res[i-1].getStarRating() == res[i].getStarRating()) {
                    assertTrue("TestGetHotelsInCityByRating failed. Problem: " +res[i-1].getPropertyName()+
                            " is before " +res[i].getPropertyName()+
                            ", but hotels with equal ratings should be ordered by alphabetical order.",
                            res[i-1].getPropertyName().compareTo(res[i].getPropertyName()) <= 0); //xxx
                }
            }
        }
    }

    @Test //xxx
    public void testGetHotelsByProximity() {
        for (Hotel hotel : testHotelArr) {
            Hotel[] res = testBoopingSite.getHotelsByProximity(hotel.getLatitude(), hotel.getLongitude());
            for (int i=1; i < res.length; i++) {
                assertTrue("TestGetHotelsByProximity failed",
                        Calculator.closerHotel(hotel.getLatitude(), hotel.getLongitude(), res[i-1], res[i]) <1);
                if (Calculator.closerHotel(hotel.getLatitude(), hotel.getLongitude(), res[i - 1], res[i]) == 0) {
                    assertTrue("TestGetHotelsByProximity failed. Problem: Hotels that are at the same distance" +
                            " from the given location should be ordered according to the number of points-of-interest" +
                            " for which they are close to (in a decreasing order",
                            res[i-1].getNumPOI() >= res[i].getNumPOI());
                }
            }
        }
    }

    @Test //xxx
    public void testGetHotelsInCityByProximity(){
        for (Hotel hotel : testHotelArr) {
            Hotel[] res = testBoopingSite.getHotelsInCityByProximity(hotel.getCity(),hotel.getLatitude(), hotel.getLongitude());
            for (int i=1; i < res.length; i++) {
                assertTrue("TestGetHotelsInCityByProximity failed.",
                        Calculator.closerHotel(hotel.getLatitude(), hotel.getLongitude(),
                        res[i-1],res[i]) >= 0);
                if (Calculator.closerHotel(hotel.getLatitude(), hotel.getLongitude(),
                        res[i-1],res[i]) == 0) {
                    assertTrue("TestGetHotelsByProximity failed. Problem: Hotels that are at the same distance" +
                                    " from the given location should be ordered according to the number of points-of-interest" +
                                    " for which they are close to (in a decreasing order",
                            res[i - 1].getNumPOI() >= res[i].getNumPOI());
                }
                }
            }
    }

}
