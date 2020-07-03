package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.response.BillObjectResponse;
import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.service.APIbill;
import com.example.projectkien500k.service.APIclient;
import com.example.projectkien500k.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class billViewModel extends ViewModel {
    private APIbill apIbill = RetrofitService.cteateService(APIbill.class);

    public MutableLiveData<BillResponse> loadBill(int id, String stt){
        final MutableLiveData<BillResponse>  newsData = new MutableLiveData<>();
        apIbill.loadBill(id,stt).enqueue(new Callback<BillResponse>() {
            @Override
            public void onResponse(Call<BillResponse> call, Response<BillResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillResponse> getlistBills(int id, String stt){
        final MutableLiveData<BillResponse>  newsData = new MutableLiveData<>();
        apIbill.getlistBills(id,stt).enqueue(new Callback<BillResponse>() {
            @Override
            public void onResponse(Call<BillResponse> call, Response<BillResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


    public MutableLiveData<BillDetailResponse> loadDetailBill(int id){
        final MutableLiveData<BillDetailResponse>  newsData = new MutableLiveData<>();
        apIbill.loadDetailBill(id).enqueue(new Callback<BillDetailResponse>() {
            @Override
            public void onResponse(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillDetailResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillObjectResponse> CreateBill(int id_address,int id_client,int id_product,int price){
        final MutableLiveData<BillObjectResponse>  newsData = new MutableLiveData<>();
        apIbill.CreateBill(id_address,id_client,id_product,price).enqueue(new Callback<BillObjectResponse>() {
            @Override
            public void onResponse(Call<BillObjectResponse> call, Response<BillObjectResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillObjectResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillDetailResponse> addExCart(int id_bill,int id_product,int price){
        final MutableLiveData<BillDetailResponse>  newsData = new MutableLiveData<>();
        apIbill.addExCart(id_bill,id_product,price).enqueue(new Callback<BillDetailResponse>() {
            @Override
            public void onResponse(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillDetailResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillDetailResponse> changeCart(String type,int id_detail,int id_product){
        final MutableLiveData<BillDetailResponse>  newsData = new MutableLiveData<>();
        apIbill.changeCart(type,id_detail,id_product).enqueue(new Callback<BillDetailResponse>() {
            @Override
            public void onResponse(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillDetailResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillDetailResponse> removeCart(int id_detail){
        final MutableLiveData<BillDetailResponse>  newsData = new MutableLiveData<>();
        apIbill.removeCart(id_detail).enqueue(new Callback<BillDetailResponse>() {
            @Override
            public void onResponse(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillDetailResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BillDetailResponse> orderCart(List<DetailBill> list){
        final MutableLiveData<BillDetailResponse>  newsData = new MutableLiveData<>();
        apIbill.orderCart(list).enqueue(new Callback<BillDetailResponse>() {
            @Override
            public void onResponse(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BillDetailResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

}
