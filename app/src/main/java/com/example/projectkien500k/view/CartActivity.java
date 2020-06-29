package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.ActivityCartBinding;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.utils.FakeDataUtil;
import com.example.projectkien500k.view.adapter.CartAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements CartAdapter.onEventCartAdapter {

    ActivityCartBinding binding;
    List<DetailBill> list;
    Bill bill;
    CartAdapter cartAdapter;
    billViewModel mbillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mbillViewModel = new ViewModelProvider(this).get(billViewModel.class);
        list=new ArrayList<>();
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
//        binding.toolbar.setTitle("Giỏ hàng ("+list.size()+")");
        handleOrderCart();
    }

    private void handleOrderCart() { // xử lý đặt hàng
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbillViewModel.orderCart(list).observe(CartActivity.this, new Observer<BillDetailResponse>() {
                    @Override
                    public void onChanged(BillDetailResponse billDetailResponse) {
                        EventBus.getDefault().removeStickyEvent(bill);
                        list.clear();
                        cartAdapter.notifyDataSetChanged();
                        totalMoney(list);

                        Toast.makeText(CartActivity.this, "đặt hàng thành công !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void populateDataCart(int id_bill) {
        mbillViewModel.loadDetailBill(id_bill).observe(CartActivity.this, new Observer<BillDetailResponse>() {
            @Override
            public void onChanged(BillDetailResponse billDetailResponse) {
                if(billDetailResponse.getStatus().equals("SUCCESS")){
                list = billDetailResponse.getData();
                totalMoney(list);
                initCartAdapter();
                }else{
                    Toast.makeText(CartActivity.this, "chưa có sản phẩm trong giỏ hàng ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initCartAdapter()
    {
        /// test commit github
        cartAdapter=new CartAdapter(list,this,this);
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerViewCart.setAdapter(cartAdapter);
    }

    public void totalMoney(List<DetailBill> list){
        int total=0;
        binding.toolbar.setTitle("Giỏ hàng ("+list.size()+")");
        for (DetailBill detail:list){
            total+=detail.getPrice()*detail.getQuantity();
        }
        binding.textView36.setText(format(total)+" VND");
    }
    public String format(double number) {
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Bill event) {
        populateDataCart(event.getIdBill());
        bill=event;
    }


    @Override
    public void onChangeQualityProduct() {

    }

    @Override
    public void onRemoveProduct() {

    }
}