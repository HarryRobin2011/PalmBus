package com.palm.palmbus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by Robin on 2016/10/24.
 */

public class BannerViewPagerAdapter extends StaticPagerAdapter{
     private Context mContext;
    private int[] images;

    public BannerViewPagerAdapter(Context mContext, int[] images) {
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(images[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return images== null?0:images.length;
    }
}
