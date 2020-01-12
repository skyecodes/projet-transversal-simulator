package com.github.franckyi.projettransversal.api;

import java.util.List;

public class Directions {
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public static class Route {
        private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        public static class Geometry {
            private List<List<Double>> coordinates;

            public List<List<Double>> getCoordinates() {
                return coordinates;
            }
        }
    }
}
