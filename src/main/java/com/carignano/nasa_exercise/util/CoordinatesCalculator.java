package com.carignano.nasa_exercise.util;

public class CoordinatesCalculator {
    public static BoxCoordinates getBoxCoordinatesByRange(Double startLat, Double startLon, Double range){
        return new BoxCoordinates(startLat - range, startLon - range, startLat + range, startLon + range);
    }
}
