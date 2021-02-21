import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import oop.ex3.spaceship.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BoopingSite {

    /** nameFile of dataSet. */
    private String dataSetNameFile;

    /** Upper bound for valid latitude input. */
    private final double latitudeUpperBound = 90;

    /** Lower bound for valid latitude input. */
    private final double latitudeLowerBound = -90;

    /** Upper bound for valid longitude input. */
    private final double longitudeUpperBound = 180;

    /** Lower bound for valid longitude input. */
    private final double longitudeLowerBound = -180;

    /** Constructor. */
    public BoopingSite(String name) {
        this.dataSetNameFile = name;
    }

    /** Returns an array of hotels located in the given city, sorted from the highest star-rating to the lowest.
     * Hotels that have the same rating will be organized according to the alphabet order of the hotel's (property) name.
     * In case there are no hotels in the given city, returns an empty array.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        ArrayList<Hotel> rankedHotelsArray = new ArrayList<>(Arrays.asList(HotelDataset.getHotels(dataSetNameFile)));
        RatingComprator ratingComprator = new RatingComprator();
        rankedHotelsArray.sort(ratingComprator);
        return rankedHotelsArray.toArray(new Hotel[0]);
    }

    /** Returns an array of hotels, sorted according to their Euclidean distance
     * from the given geographic location, in ascending order. Hotels that are at the same distance
     * from the given location are organized according to the number of points-of-interest for
     * which they are close to (in a decreasing order). In case of illegal input returns an empty array.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        if (!checkBoundaries(latitude, longitude)) {
            return new Hotel[0];
        }
        ArrayList<Hotel> sortedHotelsArray = new ArrayList<>(Arrays.asList(HotelDataset.getHotels(dataSetNameFile)));
        ProximityComprator proximityComprator = new ProximityComprator(latitude, longitude);
        sortedHotelsArray.sort(proximityComprator);
        return sortedHotelsArray.toArray(new Hotel[0]);
    }


    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        if (!checkBoundaries(latitude, longitude)) {
            return new Hotel[0];
        }
        ArrayList<Hotel> sortedHotelsInCityArray = new ArrayList<>();
        for (Hotel hotel : Arrays.asList(getHotelsByProximity(latitude, longitude))) {
            if (hotel.getCity().equals(city)) { //xxx
                sortedHotelsInCityArray.add(hotel);
            }
        }
        return sortedHotelsInCityArray.toArray(new Hotel[0]);
    }

    /** Helper for getHotelsByProximity and getHotelsInCityByProximity.
     * if the given latitude or longitude gets out of bounds, returns false. else return true.
     */
    private Boolean checkBoundaries(double latitude, double longitude) {
        if (latitude < latitudeLowerBound || latitude > latitudeUpperBound ||
                longitude < longitudeLowerBound || longitude > longitudeUpperBound) {
            return false;
        }
        return true;
    }
}
