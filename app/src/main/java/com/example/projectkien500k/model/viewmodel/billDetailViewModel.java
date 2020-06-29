package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.service.APIbill;
import com.example.projectkien500k.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class billDetailViewModel extends ViewModel {
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


}
