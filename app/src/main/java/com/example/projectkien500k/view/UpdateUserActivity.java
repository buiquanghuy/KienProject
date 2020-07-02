package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.databinding.ActivityProductBinding;
import com.example.projectkien500k.databinding.ActivityUpdateUserBinding;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.model.viewmodel.clientViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UpdateUserActivity extends BaseActivity {
    ActivityUpdateUserBinding binding;
    clientViewModel mclientViewModel;
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mclientViewModel = new ViewModelProvider(this).get(clientViewModel.class);
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Thông tin cá nhân");

        updateUser();
    }

    private void updateUser() {
        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mclientViewModel.updateUser(
                        binding.getClient().getId().toString(),
                        binding.edtName.getText().toString(),
                        binding.edtPhone.getText().toString(),
                        binding.edtEmail.getText().toString()
                        ).observe(UpdateUserActivity.this, new Observer<ClientResponse>() {
                    @Override
                    public void onChanged(ClientResponse clientResponse) {
                        Toast.makeText(UpdateUserActivity.this, ""+clientResponse.getMess(), Toast.LENGTH_SHORT).show();
                        client.setPhone(binding.edtPhone.getText().toString());
                        client.setEmail(binding.edtEmail.getText().toString());
                        client.setName(binding.edtName.getText().toString());
                        EventBus.getDefault().postSticky(client);
                        Common.mclient=client;
                    }
                });
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        binding.setClient(event);
        client = event;
    }
}