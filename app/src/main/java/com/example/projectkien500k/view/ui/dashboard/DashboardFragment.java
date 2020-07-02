package com.example.projectkien500k.view.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.FragmentDashboardBinding;
import com.example.projectkien500k.model.data.TypeProduct;
import com.example.projectkien500k.view.ListProductActivity;
import com.example.projectkien500k.view.ProductActivity;
import com.example.projectkien500k.view.adapter.ProductTypeAdapter;
import com.example.projectkien500k.model.viewmodel.productViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ProductTypeAdapter.OnClickItemProductType {

    private FragmentDashboardBinding binding;
    private productViewModel productViewModel;
    private ProductTypeAdapter adapter;
    ArrayList<TypeProduct> list=new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        productViewModel = new ViewModelProvider(getActivity()).get(productViewModel.class);
        View root = binding.getRoot();
        initAdapter();
        populateData();
        return root;
    }

    private void initAdapter() {
        binding.rcProductType.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        adapter = new ProductTypeAdapter(getContext(),list,this);
        binding.rcProductType.setAdapter(adapter);
    }

    private void populateData() {
    productViewModel.getProductType().observe(requireActivity(), new Observer<List<TypeProduct>>() {
        @Override
        public void onChanged(List<TypeProduct> typeProducts) {
            list.addAll(typeProducts);
            adapter.notifyDataSetChanged();
        }
    });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // binding = null;
    }

    @Override
    public void onCLick(TypeProduct typeProduct) {
        EventBus.getDefault().postSticky(typeProduct);
        getActivity().startActivity(new Intent(getActivity(), ListProductActivity.class));
    }
}
