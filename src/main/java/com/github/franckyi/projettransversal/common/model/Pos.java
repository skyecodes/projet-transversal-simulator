package com.github.franckyi.projettransversal.common.model;

public interface Pos {

    double getLongitude();

    void setLongitude(double longitude);

    double getLatitude();

    void setLatitude(double latitude);

    default boolean posEquals(Pos p) {
        return this.getLongitude() == p.getLongitude() && this.getLatitude() == p.getLatitude();
    }

    default double distance(Pos p) {
        return Math.sqrt(Math.pow(this.getLongitude() - p.getLongitude(), 2)
                + Math.pow(this.getLatitude() - p.getLatitude(), 2));
    }

}
