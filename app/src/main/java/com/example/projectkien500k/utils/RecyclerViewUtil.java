package com.example.projectkien500k.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkien500k.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecyclerViewUtil {
    private boolean isLoading=false;
    LoadMoreListener loadMoreListener;

//    public RecyclerViewUtil(LoadMoreListener loadMoreListener) {
//        this.isLoading = false;
//        this.loadMoreListener = loadMoreListener;
//    }

    public interface LoadMoreListener
    {
        public void onLoadMore();
    }
    public  void loadingSucces()
    {
        isLoading=false;
    }
    private void initScrollListener(RecyclerView recyclerView,final int size) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == size - 1) {
                            //bottom of list!
                            isLoading = true;
                            loadMoreListener.onLoadMore();
                        }
                }
            }
        });
    }
    public static void hideBottomNavigationOnScroll(Context context, final BottomNavigationView bottomNav, final NestedScrollView nestedScrollView)
    {
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ((scrollY-oldScrollY)>10){
                    bottomNav.setVisibility(View.GONE);
                }else if ((oldScrollY-scrollY)>10){
                    bottomNav.setVisibility(View.VISIBLE);
                }
            }
        });
        // hide bottom nav when scrolling
    }

}
