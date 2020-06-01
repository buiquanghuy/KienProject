package com.example.projectkien500k.view.adapter;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.projectkien500k.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private  String[] iamgeUrls;
    public ViewPagerAdapter(Context context, String[] iamgeUrls)
    {
        this.context=context;
        this.iamgeUrls=iamgeUrls;
    }
    @Override
    public int getCount() {
        return iamgeUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        Picasso.get()
                .load(iamgeUrls[position])
                .fit()
                .transform(new RoundedTransformation(16, 0))
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+iamgeUrls[position], Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
