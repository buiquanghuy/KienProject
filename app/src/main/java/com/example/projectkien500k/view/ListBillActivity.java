package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.application.onEventBillAdapter;
import com.example.projectkien500k.databinding.ActivityCartBinding;
import com.example.projectkien500k.databinding.ActivityListBillBinding;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.view.adapter.BillAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ListBillActivity extends BaseActivity implements onEventBillAdapter {
    ActivityListBillBinding binding;
    billViewModel mbillViewModel;
    BillAdapter adapter;
    ArrayList<Bill> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Danh sách hóa đơn");

        mbillViewModel = new ViewModelProvider(this).get(billViewModel.class);
        InitAdapter();
        populateData();
    }

    private void populateData() {
        mbillViewModel.getlistBills(Common.mclient.getId(), Common.stt).observe(this, new Observer<BillResponse>() {
            @Override
            public void onChanged(BillResponse billResponse) {
                if (billResponse.getStatus().equals("SUCCESS")) {
                    list.addAll(billResponse.getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListBillActivity.this, "không có bill", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InitAdapter() {
        binding.lvBill.setHasFixedSize(true);
        binding.lvBill.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new BillAdapter(list, this, this);
        binding.lvBill.setAdapter(adapter);
    }

    @Override
    public void onClickItem(Bill bill, int position) {
        Common.bill= bill;
        startActivity(new Intent(ListBillActivity.this,ListBillDetailActivity.class));
    }


}