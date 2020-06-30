package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.data.Banner;
import com.example.projectkien500k.model.response.ProductResponse;
import com.example.projectkien500k.service.APIbanner;
import com.example.projectkien500k.service.APIproduct;
import com.example.projectkien500k.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bannerViewModel extends ViewModel {
    private APIbanner apIbanner = RetrofitService.cteateService(APIbanner.class);

    public MutableLiveData<List<Banner>> getBanner(){
        final MutableLiveData<List<Banner>>  newsData = new MutableLiveData<>();
        apIbanner.getBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });
        return newsData;
    }


}
