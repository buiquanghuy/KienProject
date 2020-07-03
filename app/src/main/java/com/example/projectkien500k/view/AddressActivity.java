package com.example.projectkien500k.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.databinding.ActivityAddressBinding;
import com.example.projectkien500k.databinding.ActivityListBillBinding;
import com.example.projectkien500k.databinding.DialogAddressBinding;
import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.response.AddressResponse;
import com.example.projectkien500k.model.viewmodel.addressViewModel;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.view.adapter.AddressAdapter;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.OnClickItemAddress {
    ActivityAddressBinding binding;
    DialogAddressBinding dialogBinding;
    ArrayList<Address> list = new ArrayList<>();
    AddressAdapter addressAdapter;
    addressViewModel maddressViewModel;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Quản lý địa chỉ của bạn");
        maddressViewModel = new ViewModelProvider(this).get(addressViewModel.class);
        initAđapter();
        Event();
    }

    private void Event() {
        binding.FloatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddressActivity.this, "mở dialog ", Toast.LENGTH_SHORT).show();
                Address address = new Address();
                displayAlertDialog(address, 1, "ADD");
            }
        });
    }

    private void populateData() {
        maddressViewModel.getAddress(Common.mclient.getId()).observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {
                if (addressResponse.getStatus().equals(Common.SUCCESS)) {
                    list.addAll(addressResponse.getData());
                    addressAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AddressActivity.this, "empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initAđapter() {
        binding.lvAddress.setHasFixedSize(true);
        binding.lvAddress.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        addressAdapter = new AddressAdapter(this, list, this);
        binding.lvAddress.setAdapter(addressAdapter);
        populateData();
    }

    @Override
    public void onCLick(Address address, int position) {
        // hiển thị dialog sửa nè
        displayAlertDialog(address, position, "");
    }

    public void displayAlertDialog(final Address address, final int position, final String type) {
        dialogBinding = DialogAddressBinding.inflate(getLayoutInflater());
        View root = dialogBinding.getRoot();

        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.myFullscreenAlertDialogStyle);
        alert.setView(root);
        dialog = alert.create();

        if (type.equals("ADD")) {
            dialogBinding.buttonUpdate.setVisibility(View.INVISIBLE);
            dialogBinding.buttonDelete.setVisibility(View.INVISIBLE);
        }

        dialogBinding.edtAddress.setText("" + address != null ? address.getAddress() : "");
        dialogBinding.edtPhone.setText("" + address != null ? address.getPhoneNumber() : "");

        dialogBinding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogBinding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();

            }
        });
        dialogBinding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAddress(address, position);
            }
        });
        dialogBinding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAddress(address, position);

            }
        });

        dialog.show();
    }

    private void addAddress() {
        String phone = dialogBinding.edtPhone.getText().toString();
        String address = dialogBinding.edtAddress.getText().toString();
        maddressViewModel.createAddress(Common.mclient.getId(), phone, address).observe(this, new Observer<Address>() {
            @Override
            public void onChanged(Address address) {
                list.add(address);
                addressAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void updateAddress(final Address address, final int position) {
        address.setPhoneNumber(dialogBinding.edtPhone.getText().toString());
        address.setAddress(dialogBinding.edtAddress.getText().toString());
        maddressViewModel.updateAddress(address.getId(), address.getPhoneNumber(), address.getAddress()).observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {
                list.set(position, address);
                addressAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void deleteAddress(final Address address, final Integer pos) {
        maddressViewModel.deleteAddress(address.getId()).observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {
                Toast.makeText(AddressActivity.this, "" + addressResponse.getMess(), Toast.LENGTH_SHORT).show();
                list.remove(address);
                addressAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
}