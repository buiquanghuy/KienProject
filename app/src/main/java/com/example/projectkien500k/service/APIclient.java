package com.example.projectkien500k.service;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.model.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIclient {

    @FormUrlEncoded
    @POST("user/Login")
    Call<ClientResponse> loginhandle(@Field("account_name") String tk , @Field("password")  String mk);

}