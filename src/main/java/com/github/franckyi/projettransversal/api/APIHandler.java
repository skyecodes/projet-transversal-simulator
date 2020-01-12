package com.github.franckyi.projettransversal.api;

import com.github.franckyi.projettransversal.common.model.Pos;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.stream.Collectors;

public class APIHandler {

    private static final String ACCESS_TOKEN = "pk.eyJ1IjoiZnJhbmNreWkiLCJhIjoiY2s1OGJwdGxzMGx4ejNmcGcxMzN4Mmh1eCJ9.Ruuhb5adEJJQedPHUfchsA";
    private static final String GEOMETRIES = "geojson";

    private static Retrofit retrofit;
    private static MapboxService mapboxService;

    public static List<Pos> getDirections(Pos p1, Pos p2) {
        if (mapboxService == null) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.mapbox.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            mapboxService = retrofit.create(MapboxService.class);
        }
        Call<Directions> call = mapboxService.getDirections(String.format("%f,%f;%f,%f", p1.getLatitude(),
                p1.getLongitude(), p2.getLatitude(), p2.getLongitude()), ACCESS_TOKEN, GEOMETRIES);
        try {
            Directions directions = call.execute().body();
            List<List<Double>> res = directions.getRoutes().get(0).getGeometry().getCoordinates();
            return res.stream().map(doubles -> new PosImpl(doubles.get(1), doubles.get(0))).collect(Collectors.toList());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static class PosImpl implements Pos {

        private double longitude, latitude;

        public PosImpl(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public double getLongitude() {
            return longitude;
        }

        @Override
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public double getLatitude() {
            return latitude;
        }

        @Override
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
}
