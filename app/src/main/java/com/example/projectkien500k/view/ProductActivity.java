package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.ActivityProductBinding;
import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.response.BillObjectResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends BaseActivity {

    ActivityProductBinding binding;
    billViewModel mbillViewModel;

    Bill bill;
    Client client;
    Product product;
    ArrayList<Address> listaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mbillViewModel = new ViewModelProvider(this).get(billViewModel.class);
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Chi tiết sản phẩm");

        handleOrder();
    }

    private void handleOrder() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bill!=null){
                    mbillViewModel.addExCart(bill.getIdBill(),product.getIdProduct(),product.getPrice()).observe(ProductActivity.this, new Observer<BillDetailResponse>() {
                        @Override
                        public void onChanged(BillDetailResponse billDetailResponse) {
                            Toast.makeText(ProductActivity.this, ""+billDetailResponse.getMess(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    mbillViewModel.CreateBill(listaddress.get(0).getId(),client.getId(),product.getIdProduct(),product.getPrice()).observe(ProductActivity.this, new Observer<BillObjectResponse>() {
                        @Override
                        public void onChanged(BillObjectResponse billObjectResponse) {
                            bill=billObjectResponse.getData();
                            EventBus.getDefault().postSticky(bill);
                            Toast.makeText(ProductActivity.this, ""+billObjectResponse.getMess(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Product event) {
        product=event;
       binding.textView.setText(""+event.getName());
       binding.ratingBar.setRating(4);
       binding.textView3.setText(""+event.getPrice());
       binding.textView14.setText(""+event.getDescribe());
        Picasso.get()
                .load(event.getImage())
                .into(binding.imageView);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Bill event) {
        bill=event;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        client=event;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Address> event) {
        listaddress=new ArrayList<>(event);
    }
}