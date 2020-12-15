package com.example.dialabarretrofit.Api_interfaces;
import com.example.dialabarretrofit.Models.register;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("register")
    Call<List<register>> getPosts();

    @POST("register")
    Call<register> createPost(@Body register reg);
}
