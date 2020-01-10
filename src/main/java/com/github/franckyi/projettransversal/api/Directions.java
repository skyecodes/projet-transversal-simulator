package com.github.franckyi.projettransversal.api;

import java.util.List;

public class Directions {
    private List<Waypoint> waypoints;

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public static class Waypoint {
        private List<Double> location;

        public List<Double> getLocation() {
            return location;
        }
    }
}
