package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.data.Banner;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.service.APIbanner;
import com.example.projectkien500k.service.APIclient;
import com.example.projectkien500k.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class clientViewModel extends ViewModel {
    private APIclient apIclient = RetrofitService.cteateService(APIclient.class);

    public MutableLiveData<ClientResponse> Loginhandle(String tk,String mk){
        final MutableLiveData<ClientResponse>  newsData = new MutableLiveData<>();
        apIclient.loginhandle(tk,mk).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


}
