package com.example.projectkien500k.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.projectkien500k.MainActivity;
import com.example.projectkien500k.ProductActivity;
import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.FragmentHomeBinding;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.viewmodel.ViewProduct;
import com.example.projectkien500k.utils.RecyclerViewUtil;
import com.example.projectkien500k.view.adapter.ProductAdapter;
import com.example.projectkien500k.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements ProductAdapter.OnClickItemProduct {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    ProductAdapter productAdapter;

    Handler handler;
    Runnable Update;
    List<ViewProduct> list=new ArrayList<>();

    // để viewpapger chạy nhé bạn
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    String[] iamgeUrls=new String[]
            {
                    "https://salt.tikicdn.com/cache/w885/ts/banner/3a/21/17/c91cfbb87be7f222cde8873fbf1fa5f3.jpg",
                    "https://salt.tikicdn.com/cache/w885/ts/banner/da/32/51/f8477d7c6f55bf7f539e86cd6cd9efa0.png",
                    "https://salt.tikicdn.com/cache/w885/ts/banner/87/92/6a/9255ab6c720ebb39c61aa509af9f2a87.jpg",
                    "https://salt.tikicdn.com/cache/w885/ts/banner/63/7d/14/f16b099192d246e5ac1561c4ee273c34.jpg",
                    "https://salt.tikicdn.com/cache/w885/ts/banner/c5/d9/59/8241baa29e4967a5c29c6072feefceac.jpg",
                    "https://salt.tikicdn.com/cache/w885/ts/banner/36/91/93/022cfb904ad517ad34101b542e4e6837.jpg",
            };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        homeViewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
      //  View root = inflater.inflate(R.layout.fragment_home, container, false);
        View root=binding.getRoot();
       // final TextView textView = root.findViewById(R.id.text_home);
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.nav_view);
        RecyclerViewUtil.hideBottomNavigationOnScroll(getContext(),bottomNavigationView,binding.nestedScrollViewHome);
        initViewPager();
        initProductAdapter();
        homeViewModel.getListProduct().observe(getViewLifecycleOwner(), new Observer<List<ViewProduct>>() {
            @Override
            public void onChanged(List<ViewProduct> viewProducts) {
                updateData(viewProducts);
            }
        });
        homeViewModel.setDataListProduct(new ArrayList<ViewProduct>());
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
             //   textView.setText(s);
            }
        });
        ((MainActivity) getActivity()).setTitle(R.string.title_home);
        return root;
    }
    private void initViewPager()
    {
        viewPagerAdapter=new ViewPagerAdapter(getContext(),iamgeUrls);
        binding.viewPagerHome.setAdapter(viewPagerAdapter);
        runViewPaper();
    }
    private void initProductAdapter()
    {
        productAdapter=new ProductAdapter(getContext(),list,this);
        binding.recycleviewBestSell.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recycleviewBestSell.setAdapter(productAdapter);

        binding.recyclerViewForU.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recyclerViewForU.setAdapter(productAdapter);

        binding.recyclerViewAll.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.recyclerViewAll.setAdapter(productAdapter);


    }
    public void updateData(List<ViewProduct> list)
    {
        if (list!=null) this.list.clear();
        this.list.addAll(list);
        productAdapter.notifyDataSetChanged();
    }
    private void runViewPaper()
    {
        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if (currentPage == 6-1) {
                    currentPage = 0;
                }
                binding.viewPagerHome.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    //    binding=null; // cẩn thận dễ bị lỗi ở đoạn này đấy nhé
    }

    @Override
    public void onCLick(int type, Product product) {
        getActivity().startActivity(new Intent(getActivity(), ProductActivity.class));

    }
}
