package com.example.projectkien500k.view.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.databinding.FragmentHomeBinding;
import com.example.projectkien500k.databinding.FragmentProfileBinding;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.example.projectkien500k.view.AddressActivity;
import com.example.projectkien500k.view.ListBillActivity;
import com.example.projectkien500k.view.ListProductActivity;
import com.example.projectkien500k.view.LoginActivity;
import com.example.projectkien500k.view.UpdateUserActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initProfileClient();
        return root;
    }

    private void initProfileClient() {
        binding.textView16.setText("" + Common.mclient.getName());
        binding.textView17.setText("" + Common.mclient.getEmail());
        binding.textView18.setText("" + Common.mclient.getPhone());

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), UpdateUserActivity.class));
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().removeAllStickyEvents();
                Common.mclient = null;
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        binding.textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Common.stt="b2";
               startActivity(new Intent(getActivity(),ListBillActivity.class));
            }
        });
        binding.textView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.stt="b3";
                startActivity(new Intent(getActivity(),ListBillActivity.class));
            }
        });
        binding.textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.stt="b4";
                startActivity(new Intent(getActivity(),ListBillActivity.class));
            }
        });
        binding.textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.stt="b5";
                startActivity(new Intent(getActivity(),ListBillActivity.class));
            }
        });

        binding.textView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.stt="huy";
                startActivity(new Intent(getActivity(),ListBillActivity.class));
            }
        });
        binding.textView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddressActivity.class));
            }
        });


    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//    @Override
//    public void onStart() {
//        if (!EventBus.getDefault().isRegistered(requireActivity())) {
//            EventBus.getDefault().register(requireActivity());
//        }
//        super.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        EventBus.getDefault().unregister(requireActivity());
//        super.onStop();
//    }
}
