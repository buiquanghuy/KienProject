package com.example.projectkien500k.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.ActivityLoginBinding;
import com.example.projectkien500k.databinding.ActivityRegisterBinding;
import com.example.projectkien500k.model.response.ClientResponse;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.model.viewmodel.clientViewModel;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    clientViewModel mclientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mclientViewModel = new ViewModelProvider(this).get(clientViewModel.class);
        binding.toolbar.setTitleTextColor(getColor(R.color.white));
        binding.toolbar.setTitle("Đăng ký");
        Register();
    }

    private void Register() {
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                String phone = binding.edtPhone.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String account = binding.edtAccount.getText().toString();
                String pass = binding.edtPass.getText().toString();
                String repass = binding.edtRepass.getText().toString();
                String address = binding.edtAddress.getText().toString();

                if (!Valid(name,phone,email,account,pass,repass,address).equals("")){
                    Toast.makeText(RegisterActivity.this, ""+Valid(name,phone,email,account,pass,repass,address), Toast.LENGTH_SHORT).show();
                }else{
                  mclientViewModel.Register(name,phone,email,account,pass,address).observe(RegisterActivity.this, new Observer<ClientResponse>() {
                      @Override
                      public void onChanged(ClientResponse clientResponse) {
                          if(clientResponse.getStatus().equals("SUCCESS")){
                              Toast.makeText(RegisterActivity.this, ""+clientResponse.getMess(), Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                          }else{
                              Toast.makeText(RegisterActivity.this, ""+clientResponse.getMess(), Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
                }

            }
        });
    }

    private String Valid(String name, String phone, String email, String account, String pass, String repass,String address) {
        if (name.equals("") || phone.equals("") || email.equals("") || account.equals("") || pass.equals("") || repass.equals("") || address.equals("") ) {
            return "Vui lòng nhập đầy đủ thông tin";
        } else {
            if (!pass.equals(repass)) {
                return "Mật khẩu không trùng nhau";
            } else {
                return "";
            }
        }
    }


}