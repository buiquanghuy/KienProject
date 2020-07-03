package com.example.projectkien500k.service;
import com.example.projectkien500k.model.data.Address;
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

    @FormUrlEncoded
    @POST("address/updateAddress")
    Call<AddressResponse> updateAddress(@Field("id_address") int idclient,
                                        @Field("phone") String phone,
                                        @Field("address") String address);

    @FormUrlEncoded
    @POST("address/deleteAddress")
    Call<AddressResponse> deleteAddress(@Field("id_address") int id_address);

    @FormUrlEncoded
    @POST("address/CreateAddress")
    Call<Address> CreateAddress(
            @Field("idClient") int idclient,
            @Field("phone") String phone,
            @Field("address") String address);



}
