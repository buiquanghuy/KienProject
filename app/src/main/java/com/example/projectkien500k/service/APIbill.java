package com.example.projectkien500k.service;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.response.BillObjectResponse;
import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.model.response.ClientResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIbill {

    @FormUrlEncoded
    @POST("bill/loadbill")
    Call<BillResponse> loadBill(@Field("id_client") int id, @Field("status_bill") String stt);

    @FormUrlEncoded
    @POST("bill/getListBillDetail")
    Call<BillDetailResponse> loadDetailBill(@Field("id_bill") int id);

    @FormUrlEncoded
    @POST("bill/CreateBill")
    Call<BillObjectResponse> CreateBill(@Field("id_address") int id_address,
                                        @Field("id_client") int id_client,
                                        @Field("id_product") int id_product,
                                        @Field("price") int price);

    @FormUrlEncoded
    @POST("bill/addExCart")
    Call<BillDetailResponse> addExCart(@Field("id_bill") int id_address,
                                        @Field("id_product") int id_client,
                                        @Field("price") int id_product);

    @FormUrlEncoded
    @POST("bill/changeCart")
    Call<BillDetailResponse> changeCart(@Field("type") String type,
                                       @Field("id_bill") int id_bill,
                                       @Field("id_product") int id_product);


    @POST("bill/orderCart")
    Call<BillDetailResponse> orderCart(@Body List<DetailBill> detail);


}
