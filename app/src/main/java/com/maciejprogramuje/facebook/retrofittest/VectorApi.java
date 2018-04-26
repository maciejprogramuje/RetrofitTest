package com.maciejprogramuje.facebook.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface VectorApi {
        @Headers({
                "X-Parse-Application-Id: U2jFQxxfQtj0kLOvtt4u1iQKPg318MhkflXY39oG",
                "X-Parse-REST-API-Key: undefined",
                "X-Parse-Revocable-Session: 1"
        })
        @GET("login")
        Call<VectorResponse> getLogin(@Query("username") String username, @Query("password") String password);

}
