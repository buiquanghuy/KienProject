package com.example.projectkien500k.view.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectkien500k.R;
import com.example.projectkien500k.model.data.Client;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    Client mclient;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
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
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        mclient = event;
    }




}
