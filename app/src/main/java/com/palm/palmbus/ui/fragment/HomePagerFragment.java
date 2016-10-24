package com.palm.palmbus.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.palm.palmbus.R;
import com.palm.palmbus.adapter.BannerViewPagerAdapter;
import com.palm.palmbus.adapter.HomePagerAdapter;
import com.palm.palmbus.config.ControlUrl;
import com.palm.palmbus.model.BusListModel;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.utils.JSONHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerFragment extends BaseFragment {
    @BindView(R.id.list_view)
    ListView listView;
    private HomePagerAdapter homePagerAdapter;
    private View bannerView;
    private RollPagerView mRollViewPager;
    private BannerViewPagerAdapter bannerViewPagerAdapter;

    public static HomePagerFragment newInstance() {
        HomePagerFragment fragment = new HomePagerFragment();
        return fragment;
    }

    @Override
    protected int initResource() {
        return R.layout.home_pager_layout;
    }

    @Override
    protected void initData() {
        bannerView = mInflater.inflate(R.layout.home_head_view_layout, null);
        mRollViewPager = (RollPagerView) bannerView.findViewById(R.id.view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器


        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

    }

    @Override
    protected void initOperation() {
        if (bannerViewPagerAdapter == null) {
            bannerViewPagerAdapter = new BannerViewPagerAdapter(mContext, new int[]{R.mipmap.banner_02});
        }
        mRollViewPager.setAdapter(bannerViewPagerAdapter);
        listView.addHeaderView(bannerView);
        listView.setAdapter(null);
        //  request();
    }

    private void request() {
        OkHttpUtils.get().url(ControlUrl.PALM_GET_BUS_LIST).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                BusListModel listModel = JSONHelper.fromJSONObject(response, BusListModel.class);
                if (homePagerAdapter == null) {
                    homePagerAdapter = new HomePagerAdapter(mContext, listModel.getLineList());
                }
                listView.setAdapter(homePagerAdapter);
            }
        });
    }

}
