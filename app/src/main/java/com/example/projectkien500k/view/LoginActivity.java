package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.databinding.ActivityLoginBinding;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.model.viewmodel.clientViewModel;
import com.example.projectkien500k.view.ui.home.HomeViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity {
     ActivityLoginBinding binding;
    clientViewModel mclientViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Đăng Nhập");

        mclientViewModel = new ViewModelProvider(this).get(clientViewModel.class);
        HandleLogin();
    }

    private void HandleLogin() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk=binding.edtus.getText().toString();
                String mk=binding.edtpass.getText().toString();
                mclientViewModel.Loginhandle(tk,mk).observe(LoginActivity.this, new Observer<ClientResponse>() {
                    @Override
                    public void onChanged(ClientResponse clientResponse) {
                        if(clientResponse.getStatus().equals("SUCCESS")){
                            Toast.makeText(LoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                            Client client=clientResponse.getData();
                            EventBus.getDefault().postSticky(client);
                            Common.mclient=client;
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        Log.d("TAG", "onMessageEvent: "+event);
    }
}