package com.example.cropbee;

import android.graphics.Movie;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users?page=2")
    Call<ResponseBody> getMovies();
}
