package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.data.TypeProduct;
import com.example.projectkien500k.model.response.ProductResponse;
import com.example.projectkien500k.service.APIproduct;
import com.example.projectkien500k.service.RetrofitService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productViewModel extends ViewModel {
    private APIproduct apIproduct = RetrofitService.cteateService(APIproduct.class);

    public MutableLiveData<ProductResponse> getAllProduct(){
        final MutableLiveData<ProductResponse>  newsData = new MutableLiveData<>();
        apIproduct.getAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getTopProduct(){
        final MutableLiveData<ProductResponse>  newsData = new MutableLiveData<>();
        apIproduct.getTopProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getProductForU(){
        final MutableLiveData<ProductResponse>  newsData = new MutableLiveData<>();
        apIproduct.getTopProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<List<TypeProduct>> getProductType(){
        final MutableLiveData<List<TypeProduct>>  newsData = new MutableLiveData<>();
        apIproduct.getProductType().enqueue(new Callback<List<TypeProduct>>() {
            @Override
            public void onResponse(Call<List<TypeProduct>> call, Response<List<TypeProduct>> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TypeProduct>> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> getProductWithType(int idType){
        final MutableLiveData<ProductResponse>  newsData = new MutableLiveData<>();
        apIproduct.getProductWithType(idType).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductResponse> searchProduct(String name){
        final MutableLiveData<ProductResponse>  newsData = new MutableLiveData<>();
        apIproduct.searchProduct(name).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

}
