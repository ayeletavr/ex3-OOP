import oop.ex3.searchengine.Hotel;

import java.awt.*;
import java.util.*;

class RatingComprator implements Comparator<Hotel> {
    @Override
    public int compare(Hotel o1, Hotel o2) {
        if (o1.getStarRating() != o2.getStarRating()) {
            return Integer.compare(o1.getStarRating(), o2.getStarRating());
        }
        return o1.getPropertyName().compareToIgnoreCase(o2.getPropertyName());
    }
}

class ProximityComprator implements Comparator<Hotel> {
    private double compareLatitude;
    private double compareLongitude;
    public ProximityComprator(double latitude, double longitude) {
        this.compareLatitude = latitude;
        this.compareLongitude = longitude;
    }

    @Override
    public int compare(Hotel o1, Hotel o2) {
        if (Calculator.closerHotel(compareLatitude, compareLongitude, o1, o2) != 0) {
            return Calculator.closerHotel(compareLatitude, compareLongitude, o1, o2);
        }
        return Integer.compare(o2.getNumPOI(), o1.getNumPOI());
    }
}

class Calculator {

    public static double getDistanceBetweenHotels(double latitude1, double longitude1, double latitude2, double longitude2) {
        return Math.sqrt((Math.pow(Math.abs(latitude1-latitude2),2) + (Math.pow(Math.abs(longitude1-longitude2),2))));
    }

    public static int closerHotel(double latitude, double longitude, Hotel o1, Hotel o2) {
        return Double.compare(getDistanceBetweenHotels(latitude, longitude, o2.getLatitude(), o2.getLongitude()),
                getDistanceBetweenHotels(latitude, longitude, o1.getLatitude(), o1.getLongitude()));
    }
}