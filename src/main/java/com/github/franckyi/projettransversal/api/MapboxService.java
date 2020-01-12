package com.github.franckyi.projettransversal.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapboxService {

    @GET("/directions/v5/mapbox/driving-traffic/{coordinates}.json")
    Call<Directions> getDirections(@Path("coordinates") String coordinates,
                                   @Query("access_token") String accessToken,
                                   @Query("geometries") String geometries);

}
