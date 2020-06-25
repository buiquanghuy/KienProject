package com.example.projectkien500k.service;
import com.example.projectkien500k.model.data.Banner;
import com.example.projectkien500k.model.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIbanner {

    @GET("banner/getBanner")
    Call<List<Banner>> getBanner();

}
