package com.example.home.think_natalia.Retrofit;

import com.example.home.think_natalia.Retrofit.Model.Item;
import com.example.home.think_natalia.Retrofit.Model.Natalia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Home on 2017-04-26.
 */

public interface NataliaInterface {

    @GET("media")
    Call<Natalia> getposts();

    @GET("media/")
    Call<Natalia> loadmore(@Query("max_id") String page);
}
