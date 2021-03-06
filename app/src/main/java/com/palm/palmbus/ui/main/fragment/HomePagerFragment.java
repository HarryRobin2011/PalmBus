package com.palm.palmbus.ui.main.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Message;
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
import com.palm.palmbus.bean.HomeLineInfoBean;
import com.palm.palmbus.bean.HomeListBean;
import com.palm.palmbus.bean.LineInfoBean;
import com.palm.palmbus.config.ControlUrl;
import com.palm.palmbus.config.LogUtil;
import com.palm.palmbus.config.ParamsKey;
import com.palm.palmbus.service.LocationService;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.ui.base.BasePalmActivity;
import com.palm.palmbus.utils.JSONHelper;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Response;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerFragment extends BaseFragment implements BaseMyAdapter.OnChildClickListener {
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




   // @Override
//    protected void initOperation() {
//        if (bannerViewPagerAdapter == null) {
//            bannerViewPagerAdapter = new BannerViewPagerAdapter(getContext(), new int[]{R.mipmap.banner_02});
//        }
//        mRollViewPager.setAdapter(bannerViewPagerAdapter);
//        listView.addHeaderView(bannerView);
//        listView.setAdapter(null);
//        locationService.registerListener(mBdLocationListener);
//        locationService.start();
//        //  request();
//    }


    /**
     * 检索公交站牌
     */
    private void searchPoi(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
        searchOption.keyword("公交站");
        searchOption.location(latLng);
        searchOption.radius(1000);
        poiSearch.setOnGetPoiSearchResultListener(new LocationOnGetPoiSearchResultListener());
        poiSearch.searchNearby(searchOption);
    }


    /**
     * 检索公交线路
     */
    private void searchInCityPoi(String busLine) {
        PoiCitySearchOption operation = new PoiCitySearchOption();
        operation.city(((BasePalmActivity) getActivity()).bdLocation.getCity());
        operation.keyword(busLine);
        poiSearch.searchInCity(operation);
    }

    /**
     * 详情检索
     *
     * @param uid
     */
    private void searchDetailPoi(String uid) {
        PoiDetailSearchOption option = new PoiDetailSearchOption();
        option.poiUid(uid);
        poiSearch.searchPoiDetail(option);
    }


    @Override
    public void OnChildClick(View view) {
        switch (view.getId()) {
            case R.id.home_page_item_layout:
//                Intent intent = new Intent(mContext, StationInfoActivity.class);
//                HomeListBean listBean = (HomeListBean) busStationListAdapter.dataList.get((Integer) view.getTag());
//                intent.putExtra(IntentFlag.KEY, listBean.getStationInfo().uid);
//                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.home_pager_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        locationService = ((MyApplication) getActivity().getApplication()).getmLocationService();
        mBdLocationListener = new BdListener();
        // 实例化搜索对象
        poiSearch = PoiSearch.newInstance();

        bannerView = mInflater.inflate(R.layout.home_head_view_layout, null);
        mRollViewPager = (RollPagerView) bannerView.findViewById(R.id.view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));
    }

    /**
     * 定位回掉
     */
    private class BdListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            searchPoi(bdLocation.getLatitude(), bdLocation.getLongitude());
            ((BasePalmActivity) getActivity()).saveBdLocation(bdLocation);
            Log.i("HarryRobin", "==========" + JSONHelper.toJSONString(bdLocation));
        }
    }

    /**
     * 组装列表数据
     */
    private List<HomeListBean> packageData(List<PoiInfo> poiInfoList) throws IOException {
        if (poiInfoList == null || poiInfoList.size() == 0) {
            Message msg = Message.obtain();
            msg.what = 0;
            msg.obj = R.string.current_no_station;
           // mHandler.sendMessage(msg);
            return null;
        }
        List<HomeListBean> homeListBeanList = new LinkedList<>();
        for (int position = 0; position < poiInfoList.size(); position++) {
            HomeListBean homeListBean = new HomeListBean();
            PoiInfo poiInfo = poiInfoList.get(position);
            if (position == 0) {
                List stationLineList = new LinkedList();
                int lineNum = 0;
                String[] lineName = poiInfo.address.split(";");
                lineNum = lineName.length > 3 ? 3 : lineName.length;
                for (int i = 0; i < lineNum; i++) {
                    //站点包含的线路
                    HomeLineInfoBean homeLineInfoBean = new HomeLineInfoBean();
                    homeLineInfoBean.setSxx("0");// 默认方向
                    Response lineInfoResponse = OkHttpUtils.post().url(ControlUrl.PALM_GET_LINE_STATION).addParams(ParamsKey.LINE_CODE, lineName[i].replace("路", "")).build().execute();
                    LineInfoBean lineInfoBean = JSONHelper.fromJSONObject(lineInfoResponse.body().string(), LineInfoBean.class);
                    homeLineInfoBean.setInfoBean(lineInfoBean);


//                    Response busInfoResponse = OkHttpUtils.post().url(ControlUrl.PALM_GET_LINE_BUS_LIST).
//                            addParams(ParamsKey.LINE_CODE, lineName[i].replace("路", "")).
//                            addParams(ParamsKey.SXX, homeLineInfoBean.getSxx()).
//                            build().execute();
//                    List<BusInfoBean> busInfoBeanList = JSONHelper.fromJSONObject(busInfoResponse.body().string(), new TypeToken<List<BusInfoBean>>() {
//                    }.getType());
//                    homeLineInfoBean.setBusInfoBeanList(busInfoBeanList);
                    stationLineList.add(homeLineInfoBean);
                }
                homeListBean.setHomeLineInfoBeanList(stationLineList);
            }
            homeListBean.setStationInfo(poiInfo);
            homeListBeanList.add(homeListBean);
        }
        return homeListBeanList;
    }


    /**
     * 组装Adapter dataList
     */
    private class MyTask extends AsyncTask<Void, Void, List<HomeListBean>> {
        List<PoiInfo> poiInfoList;

        public MyTask(List<PoiInfo> poiInfoList) {
            this.poiInfoList = poiInfoList;
        }

        @Override
        protected List<HomeListBean> doInBackground(Void... params) {
            try {
                return packageData(poiInfoList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<HomeListBean> homeListBeen) {
            super.onPostExecute(homeListBeen);
            if (homeListBeen != null) {
                setAdapter(homeListBeen);
            } else {
                showLongToast(R.string.current_no_station);
            }
        }
    }

    private class LocationOnGetPoiSearchResultListener implements OnGetPoiSearchResultListener {

        /**
         * 搜索回掉
         *
         * @param poiResult
         */
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            LogUtil.logOutPut(JSONHelper.toJSONString(poiResult));
              new MyTask(poiResult.getAllPoi()).execute();
//            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
//                searchPoiForBus(poiResult.getAllPoi().get(0).address.split(";")[0]);
//            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            LogUtil.logOutPut(JSONHelper.toJSONString(poiDetailResult));
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            LogUtil.logOutPut(JSONHelper.toJSONString(poiIndoorResult));
        }
    }

    /**
     * 搜索公交车详情
     */
//    private class BusInfoOnGetPoiSearchResultListener implements OnGetPoiSearchResultListener {
//
//        @Override
//        public void onGetPoiResult(PoiResult poiResult) {
//            if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
//                return;
//            }
//            //遍历所有POI，找到类型为公交线路的POI
//            for (PoiInfo poi : poiResult.getAllPoi()) {
//                if (poi.type == PoiInfo.POITYPE.BUS_LINE || poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
//                    //说明该条POI为公交信息，获取该条POI的UID
//                    String busLineId = poi.uid;
//                    BusLineSearch busLineSearch = BusLineSearch.newInstance();
//                    busLineSearch.setOnGetBusLineSearchResultListener(new GetBusLineSearchResultListener());
//                    //如下代码为发起检索代码，定义监听者和设置监听器的方法与POI中的类似
//                    busLineSearch.searchBusLine((new BusLineSearchOption()
//                            .city(activity.bdLocation.getCity())
//                            .uid(busLineId)));
//                    break;
//                }
//            }
//        }
//
//    @Override
//    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//
//    }
//
//    @Override
//    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//    }
//
//}
//
//    private class GetBusLineSearchResultListener implements OnGetBusLineSearchResultListener{
//
//        @Override
//        public void onGetBusLineResult(BusLineResult busLineResult) {
//            LogUtil.logOutPut(busLineResult.getUid());
//        }
//    }


    private void setAdapter(List dataList) {
        if (busStationListAdapter == null) {
            busStationListAdapter = new HomePagerAdapter(getContext(), dataList);
        }
        busStationListAdapter.setOnChildClickListener(this);
        listView.setAdapter(busStationListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationService.stop();
    }
}
