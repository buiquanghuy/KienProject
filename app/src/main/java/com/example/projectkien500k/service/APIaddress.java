package com.example.projectkien500k.service;
import com.example.projectkien500k.model.response.AddressResponse;
import com.example.projectkien500k.model.response.ClientResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIaddress {

    @FormUrlEncoded
    @POST("address/loadaddress")
    Call<AddressResponse> LoadAddress(@Field("idClient") int idclient);

}
