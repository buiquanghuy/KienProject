package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.application.onEventAdapter;
import com.example.projectkien500k.databinding.ActivityListBillBinding;
import com.example.projectkien500k.databinding.ActivityListBillDetailBinding;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.view.adapter.BillAdapter;
import com.example.projectkien500k.view.adapter.BillDetailAdapter;

import java.util.ArrayList;

public class ListBillDetailActivity extends BaseActivity {
    ActivityListBillDetailBinding binding;
    billViewModel mbillViewModel;
    BillDetailAdapter adapter;
    ArrayList<DetailBill> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBillDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mbillViewModel = new ViewModelProvider(this).get(billViewModel.class);
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Chi Tiết Hóa Đơn");
        Log.d("TAG", "onCreate: "+Common.bill.getIdBill());
        initAdapter();
        populateDataCart(Common.bill.getIdBill());
    }

    private void initAdapter() {
        binding.lvBillDetail.setHasFixedSize(true);
        binding.lvBillDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter=new BillDetailAdapter(list , this);
        binding.lvBillDetail.setAdapter(adapter);
    }

    private void populateDataCart(int id_bill) {
        mbillViewModel.loadDetailBill(id_bill).observe(ListBillDetailActivity.this, new Observer<BillDetailResponse>() {
            @Override
            public void onChanged(BillDetailResponse billDetailResponse) {
                if (billDetailResponse.getStatus().equals("SUCCESS")) {
                    list.addAll(billDetailResponse.getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListBillDetailActivity.this, "trống ???", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}