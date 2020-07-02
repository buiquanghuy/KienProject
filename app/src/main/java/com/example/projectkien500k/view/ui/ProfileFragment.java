package com.example.projectkien500k.view.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectkien500k.R;
import com.example.projectkien500k.application.Common;
import com.example.projectkien500k.databinding.FragmentHomeBinding;
import com.example.projectkien500k.databinding.FragmentProfileBinding;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.utils.RoundedTransformation;
import com.example.projectkien500k.view.UpdateUserActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    Client mclient;

    FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        initProfileClient();
        return root;
    }

    private void initProfileClient() {
        binding.textView16.setText(""+Common.mclient.getName());
        binding.textView17.setText(""+Common.mclient.getEmail());
        binding.textView18.setText(""+Common.mclient.getPhone());

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), UpdateUserActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        if (!EventBus.getDefault().isRegistered(requireActivity())) {
            EventBus.getDefault().register(requireActivity());
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(requireActivity());
        super.onStop();
    }

}
