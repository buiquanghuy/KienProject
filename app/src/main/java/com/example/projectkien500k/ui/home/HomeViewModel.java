package com.example.projectkien500k.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectkien500k.model.viewmodel.ViewProduct;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<ViewProduct>> list;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        list=new MutableLiveData<>();
        mText.setValue("This is home fragment");
        setDataListProduct(new ArrayList<ViewProduct>());
    }

    public LiveData<List<ViewProduct>> getListProduct()
    {
       return list;
    }
    public LiveData<String> getText() {
        return mText;
    }
    public void setDataListProduct(ArrayList<ViewProduct> arrayList)
    {
        arrayList=new ArrayList<>();
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        arrayList.add(new ViewProduct("Sách nè",10000000,1,"một thứ gì đó","https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg"));
        list.setValue(arrayList);
    }

}