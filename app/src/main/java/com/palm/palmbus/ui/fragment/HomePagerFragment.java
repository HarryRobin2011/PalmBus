package com.palm.palmbus.ui.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.palm.palmbus.MyApplication;
import com.palm.palmbus.R;
import com.palm.palmbus.adapter.BannerViewPagerAdapter;
import com.palm.palmbus.adapter.HomePagerAdapter;
import com.palm.palmbus.config.ControlUrl;
import com.palm.palmbus.config.LogUtil;
import com.palm.palmbus.model.BusListModel;
import com.palm.palmbus.service.LocationService;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.utils.JSONHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerFragment extends BaseFragment implements OnGetPoiSearchResultListener {
    @BindView(R.id.list_view)
    ListView listView;
    private HomePagerAdapter homePagerAdapter;
    private View bannerView;
    private RollPagerView mRollViewPager;
    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private LocationService locationService;
    private BdListener mBdLocationListener;
    private PoiSearch poiSearch;
    private HomePagerAdapter busStationListAdapter;

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
        locationService = ((MyApplication)getActivity().getApplication()).getmLocationService();
        mBdLocationListener = new BdListener();
        // 实例化搜索对象
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);

        bannerView = mInflater.inflate(R.layout.home_head_view_layout, null);
        mRollViewPager = (RollPagerView) bannerView.findViewById(R.id.view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.WHITE));
    }

    @Override
    protected void initOperation() {
        if (bannerViewPagerAdapter == null) {
            bannerViewPagerAdapter = new BannerViewPagerAdapter(mContext, new int[]{R.mipmap.banner_02});
        }
        mRollViewPager.setAdapter(bannerViewPagerAdapter);
        listView.addHeaderView(bannerView);
        listView.setAdapter(null);
        locationService.registerListener(mBdLocationListener);
        locationService.start();


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

    /**
     * 检索公交站牌
     */
    private void searchPoi(double latitude,double longitude){
        LatLng latLng = new LatLng(latitude,longitude);
        PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
        searchOption.keyword("公交站");
        searchOption.location(latLng);
        searchOption.radius(1000);
        poiSearch.searchNearby(searchOption);
    }

    /**
     * 定位回掉
     */
    private class BdListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            searchPoi(bdLocation.getLatitude(),bdLocation.getLongitude());
            Log.i("HarryRobin","=========="+JSONHelper.toJSONString(bdLocation));
        }
    }

    /**
     * 搜索回掉
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LogUtil.LogOutPut(JSONHelper.toJSONString(poiResult));
        setAdapter(poiResult.getAllPoi());
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        LogUtil.LogOutPut(JSONHelper.toJSONString(poiDetailResult));
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        LogUtil.LogOutPut(JSONHelper.toJSONString(poiIndoorResult));
    }

    private void setAdapter(List dataList){
        if(busStationListAdapter == null){
            busStationListAdapter = new HomePagerAdapter(getContext(),dataList);
        }
        listView.setAdapter(busStationListAdapter);
    }



}
