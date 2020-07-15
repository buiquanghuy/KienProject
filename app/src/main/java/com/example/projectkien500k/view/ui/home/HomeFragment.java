package com.example.projectkien500k.view.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.model.data.Banner;
import com.example.projectkien500k.model.response.ProductResponse;
import com.example.projectkien500k.model.viewmodel.bannerViewModel;
import com.example.projectkien500k.model.viewmodel.productViewModel;
import com.example.projectkien500k.view.MainActivity;
import com.example.projectkien500k.view.ProductActivity;
import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.FragmentHomeBinding;
import com.example.projectkien500k.model.data.Product;
import com.example.projectkien500k.model.viewmodel.ViewProduct;
import com.example.projectkien500k.utils.RecyclerViewUtil;
import com.example.projectkien500k.view.adapter.ProductAdapter;
import com.example.projectkien500k.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements ProductAdapter.OnClickItemProduct {


    private productViewModel productViewModel;
    private bannerViewModel mbannerViewModel;
    private FragmentHomeBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    ProductAdapter productAdapter;
    ProductAdapter topProductAdapter;
    ProductAdapter forUProductAdapter;
    Handler handler;
    Runnable Update;
    List<Product> list=new ArrayList<>();
    List<Product> listTopProduct=new ArrayList<>();
    List<Product> listforUProduct=new ArrayList<>();
    List<String> listBanner=new ArrayList<>();
    // để viewpapger chạy nhé bạn
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    String[] iamgeUrls ;   // banner quảng cáo đm
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        productViewModel = new ViewModelProvider(requireActivity()).get(productViewModel.class);
        mbannerViewModel=new ViewModelProvider(requireActivity()).get(bannerViewModel.class);

      //  View root = inflater.inflate(R.layout.fragment_home, container, false);
        View root=binding.getRoot();
       // final TextView textView = root.findViewById(R.id.text_home);
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.nav_view);
        RecyclerViewUtil.hideBottomNavigationOnScroll(getContext(),bottomNavigationView,binding.nestedScrollViewHome);

        initProductAdapter();
        populateData();

        ((MainActivity) getActivity()).setTitle(R.string.title_home);
        return root;
    }

    private void populateData() { // load tâst cả sản phẩm
        productViewModel.getAllProduct().observe(requireActivity(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                if(productResponse != null){
                    if(productResponse.getStatus().equals("SUCCESS")){
                        list.addAll(productResponse.getData());
                        productAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
                // load sản phẩm bán chạy
        productViewModel.getTopProduct().observe(requireActivity(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                if(productResponse != null){
                    if(productResponse.getStatus().equals("SUCCESS")){
                        listTopProduct.addAll(productResponse.getData());
                        topProductAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
            // load danh sách sản phẩm dành cho bạn
        productViewModel.getProductForU().observe(requireActivity(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                if(productResponse != null){
                    if(productResponse.getStatus().equals("SUCCESS")){
                        listforUProduct.addAll(productResponse.getData());
                        forUProductAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
            // tải banner
        mbannerViewModel.getBanner().observe(requireActivity(), new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banner) {
                if(banner.size()>0){
                    iamgeUrls=new String[banner.size()];
                    for(int i=0;i<banner.size();i++)
                    {
                        iamgeUrls[i] = banner.get(i).getImage();
                    }
                    initViewPager();
                }
            }
        });
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
        topProductAdapter=new ProductAdapter(getContext(),listTopProduct,this);
        forUProductAdapter=new ProductAdapter(getContext(),listforUProduct,this);

        binding.recycleviewBestSell.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recycleviewBestSell.setAdapter(topProductAdapter);

        binding.recyclerViewForU.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recyclerViewForU.setAdapter(forUProductAdapter);

        binding.recyclerViewAll.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.recyclerViewAll.setAdapter(productAdapter);


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
    public void onCLick(int type, Product product) {
        EventBus.getDefault().postSticky(product);
        getActivity().startActivity(new Intent(getActivity(), ProductActivity.class));
    }
}
