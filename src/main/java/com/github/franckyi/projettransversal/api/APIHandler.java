package com.github.franckyi.projettransversal.api;

import com.github.franckyi.projettransversal.common.model.Position;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class APIHandler {

    private static final String ACCESS_TOKEN = "pk.eyJ1IjoiZnJhbmNreWkiLCJhIjoiY2s1OGJwdGxzMGx4ejNmcGcxMzN4Mmh1eCJ9.Ruuhb5adEJJQedPHUfchsA";

    private static Retrofit retrofit;
    private static MapboxService mapboxService;

    public static Directions getDirections(Position p1, Position p2) {
        if (mapboxService == null) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.mapbox.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            mapboxService = retrofit.create(MapboxService.class);
        }
        Call<Directions> call = mapboxService.getDirections(String.format("%f,%f;%f,%f", p1.getLongitude(),
                p1.getLatitude(), p2.getLongitude(), p2.getLatitude()), ACCESS_TOKEN);
        try {
            Response<Directions> reponse = call.execute();
            return reponse.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
