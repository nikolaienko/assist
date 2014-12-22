package models;

/**
 * Created by vlad on 12/22/14.
 */
public class Coord {
    private Double lat;
    private Double lng;

    public Coord(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
