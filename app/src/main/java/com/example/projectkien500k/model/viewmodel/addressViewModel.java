package com.example.projectkien500k.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.data.Banner;
import com.example.projectkien500k.model.response.AddressResponse;
import com.example.projectkien500k.service.APIaddress;
import com.example.projectkien500k.service.APIbanner;
import com.example.projectkien500k.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addressViewModel extends ViewModel {
    private APIaddress apIaddress = RetrofitService.cteateService(APIaddress.class);

    public MutableLiveData<AddressResponse> getAddress(int idclient){
        final MutableLiveData<AddressResponse>  newsData = new MutableLiveData<>();
        apIaddress.LoadAddress(idclient).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<Address> createAddress(int idclient, String phone, String address){
        final MutableLiveData<Address>  newsData = new MutableLiveData<>();
        apIaddress.CreateAddress(idclient,phone,address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<AddressResponse> updateAddress(int idAddress,String phone,String address){
        final MutableLiveData<AddressResponse>  newsData = new MutableLiveData<>();
        apIaddress.updateAddress(idAddress,phone,address).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<AddressResponse> deleteAddress(int idAddress){
        final MutableLiveData<AddressResponse>  newsData = new MutableLiveData<>();
        apIaddress.deleteAddress(idAddress).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


}
