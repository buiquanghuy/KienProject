package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.ActivityListProductBinding;
import com.example.projectkien500k.databinding.ActivityMainBinding;
import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.data.TypeProduct;
import com.example.projectkien500k.model.response.AddressResponse;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.model.response.ProductResponse;
import com.example.projectkien500k.model.viewmodel.ViewProduct;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.model.viewmodel.productViewModel;
import com.example.projectkien500k.view.adapter.ProductAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends BaseActivity implements ProductAdapter.OnClickItemProduct{
        ActivityListProductBinding binding;

        ProductAdapter adapter;
        ArrayList<ViewProduct> list = new ArrayList<>();

        Client client;

    private productViewModel mproductViewModel;
    private billViewModel mbillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Danh sách sản phẩm");
        mproductViewModel = new ViewModelProvider(this).get(productViewModel.class);
        mbillViewModel = new ViewModelProvider(this).get(billViewModel.class);
         initAdapter();

    }

    private void initAdapter() {
        adapter = new ProductAdapter(this,list,this);
        binding.rclistProduct.setLayoutManager(new GridLayoutManager(this,4));
        binding.rclistProduct.setAdapter(adapter);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TypeProduct event) {
       populateData(event.getIdType());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        client = event;
        loadBill(event.getId(), "b1");
    }

    private void populateData(Integer idType) {
        mproductViewModel.getProductWithType(idType).observe(this, new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                if(productResponse != null){
                    if(productResponse.getStatus().equals("SUCCESS")){
                        List<Product> listpro = productResponse.getData();
                        for (int i=0;i<listpro.size();i++){
                            Product pro =listpro.get(i);
                            list.add(new ViewProduct(pro.getName(),pro.getPrice(),pro.getIdProduct(),pro.getDescribe(),pro.getImage()));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void loadBill(int id_client, String stt) {
        mbillViewModel.loadBill(id_client, stt).observe(ListProductActivity.this, new Observer<BillResponse>() {
            @Override
            public void onChanged(BillResponse billResponse) {
                if (billResponse.getStatus().equals("SUCCESS")) {
                    List<Bill> bills = billResponse.getData();
                    EventBus.getDefault().postSticky(bills.get(0));
                    loadDetailbills(bills.get(0).getIdBill());
                }else{
                    List<Bill> bills=new ArrayList<>();
                    EventBus.getDefault().postSticky(bills);
                }
            }
        });
    }

    private void loadDetailbills(Integer idBill) {
        mbillViewModel.loadDetailBill(idBill).observe(this, new Observer<BillDetailResponse>() {
            @Override
            public void onChanged(BillDetailResponse billDetailResponse) {
                if (billDetailResponse.getStatus().equals("SUCCESS")) {
                    List<DetailBill> listDetail = billDetailResponse.getData();
                    EventBus.getDefault().postSticky(listDetail);
                }

            }
        });
    }

    @Override
    public void onCLick(int type, Product product) {
        EventBus.getDefault().postSticky(product);
       startActivity(new Intent(ListProductActivity.this, ProductActivity.class));
    }
}