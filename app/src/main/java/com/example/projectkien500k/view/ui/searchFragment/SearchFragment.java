package com.example.projectkien500k.view.ui.searchFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.FragmentHomeBinding;
import com.example.projectkien500k.databinding.FragmentSearchBinding;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.response.ProductResponse;
import com.example.projectkien500k.model.viewmodel.productViewModel;
import com.example.projectkien500k.utils.RecyclerViewUtil;
import com.example.projectkien500k.view.ProductActivity;
import com.example.projectkien500k.view.adapter.ProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ProductAdapter.OnClickItemProduct {
    FragmentSearchBinding binding;
    productViewModel productViewModel;
    ProductAdapter productAdapter;
    List<Product> listOrigin = new ArrayList<>();
    List<Product> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        productViewModel = new ViewModelProvider(requireActivity()).get(productViewModel.class);
        initProductAdapter();
        search();
        return binding.getRoot();
    }

    private void search() { // hàm bắt dữ liệu khi search
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // khi text thay đổi
                    populateData(newText);
                return false;
            }
        });
    }

    private void initProductAdapter() { // khởi tạo recycler view
        productAdapter = new ProductAdapter(getContext(), list, this);
        binding.rclist.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.rclist.setAdapter(productAdapter);
    }

    private void populateData(String name) { // tải dữ liệu về và gán vào recycler view bước 1 (yêu cầu )
        productViewModel.searchProduct(name).observe(requireActivity(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) { // nhận dữ liệu  (bước 4)
                if (productResponse != null) { // xử lý logic
                    if (productResponse.getStatus().equals("SUCCESS")) {
                        list.addAll(productResponse.getData());
                        productAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public void onCLick(int type, Product product) {
        EventBus.getDefault().postSticky(product);
        getActivity().startActivity(new Intent(getActivity(), ProductActivity.class));
    }
}
