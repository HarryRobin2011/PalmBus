package com.palm.palmbus.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
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
import com.palm.palmbus.adapter.base.BaseMyAdapter;
import com.palm.palmbus.bean.HomeListBean;
import com.palm.palmbus.bean.LineInfoBean;
import com.palm.palmbus.config.ControlUrl;
import com.palm.palmbus.config.IntentFlag;
import com.palm.palmbus.config.LogUtil;
import com.palm.palmbus.config.ParamsKey;
import com.palm.palmbus.model.BusListModel;
import com.palm.palmbus.service.LocationService;
import com.palm.palmbus.ui.StationInfoActivity;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.ui.base.BasePalmActivity;
import com.palm.palmbus.utils.JSONHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerFragment extends BaseFragment implements OnGetPoiSearchResultListener,BaseMyAdapter.OnChildClickListener{
    @BindView(R.id.list_view)
    ListView listView;
    private View bannerView;
    private RollPagerView mRollViewPager;
    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private LocationService locationService;
    private BdListener mBdLocationListener;
    private PoiSearch poiSearch;
    private HomePagerAdapter busStationListAdapter;
    private List stationList;

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
     * 检索公交线路
     */
    private void searchInCityPoi(String busLine){
        PoiCitySearchOption operation = new PoiCitySearchOption();
        operation.city(((BasePalmActivity)getActivity()).bdLocation.getCity());
        operation.keyword(busLine);
        poiSearch.searchInCity(operation);
    }

    /**
     * 详情检索
     * @param uid
     */
    private void searchDetailPoi(String uid){
        PoiDetailSearchOption option = new PoiDetailSearchOption();
        option.poiUid(uid);
        poiSearch.searchPoiDetail(option);
    }


    @Override
    public void OnChildClick(View view) {
        switch (view.getId()){
            case R.id.home_page_item_layout:
                Intent intent = new Intent(mContext, StationInfoActivity.class);
                PoiInfo poiInfo = (PoiInfo) busStationListAdapter.dataList.get((Integer) view.getTag());
                intent.putExtra(IntentFlag.KEY,poiInfo.uid);
                startActivity(intent);
                break;
        }
    }

    /**
     * 定位回掉
     */
    private class BdListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            searchPoi(bdLocation.getLatitude(),bdLocation.getLongitude());
            ((BasePalmActivity)getActivity()).saveBdLocation(bdLocation);
            Log.i("HarryRobin","=========="+JSONHelper.toJSONString(bdLocation));
        }
    }

    /**
     * 组装列表数据
     */
    private List<HomeListBean> packageData(List<PoiInfo> poiInfoList){
        List<HomeListBean> homeListBeanList = new LinkedList<>();
       for (int position = 0; position < poiInfoList.size();position++){
           HomeListBean listBean = new HomeListBean();
           listBean.setStationInfo(poiInfoList.get(position));
           if(position == 0){
               int lineNum;
               String[] lineName = poiInfoList.get(position).address.split(";");
               if(stationList == null){
                   stationList = new LinkedList();
               }else{
                   stationList.clear();
               }

               if(lineName.length > 3){
                   lineNum = 3;
               }else{
                   lineNum = lineName.length;
               }
               for (int i = 0;i<lineNum;i++){
                   requestBusLine(lineName[i].replace("路",""));
               }
               listBean.setHomeLineInfoBeanList(stationList);

           }
           homeListBeanList.add(listBean);
       }
        return homeListBeanList;
    }


    /**
     * 请求线路详情
     * @param line
     */
    private void requestBusLine(String line){
       new MyTask(line).execute();
    }

    private class MyTask extends AsyncTask<Void,Void,Response>{
        private String line;

        public MyTask(String line) {
            this.line = line;
        }

        @Override
        protected Response doInBackground(Void... params) {
            OkHttpUtils.post().url(ControlUrl.PALM_GET_LINE_STATION).addParams(ParamsKey.LINE_CODE,line).build().execute(new Callback() {
                @Override
                public Object parseNetworkResponse(Response response, int id) throws Exception {
                    return null;
                }

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(Object response, int id) {
                    Log.i("HarryRobin",response.toString());
                }
            });
            Response response = null;

            try {
                 response = OkHttpUtils.post().url(ControlUrl.PALM_GET_LINE_STATION).addParams(ParamsKey.LINE_CODE,line).build().execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Log.i("HarryRobin",response.body().toString());
        }
    }


    /**
     * 搜索回掉
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LogUtil.logOutPut(JSONHelper.toJSONString(poiResult));
        packageData(poiResult.getAllPoi());
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        LogUtil.logOutPut(JSONHelper.toJSONString(poiDetailResult));
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        LogUtil.logOutPut(JSONHelper.toJSONString(poiIndoorResult));
    }

    private void setAdapter(List dataList){
        if(busStationListAdapter == null){
            busStationListAdapter = new HomePagerAdapter(mContext,dataList);
        }
        busStationListAdapter.setOnChildClickListener(this);
        listView.setAdapter(busStationListAdapter);
    }



}
