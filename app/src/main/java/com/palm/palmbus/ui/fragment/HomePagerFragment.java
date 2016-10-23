package com.palm.palmbus.ui.fragment;

import android.widget.ListView;

import com.palm.palmbus.R;
import com.palm.palmbus.adapter.HomePagerAdapter;
import com.palm.palmbus.config.ControlUrl;
import com.palm.palmbus.model.BusListModel;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.utils.JSONHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerFragment extends BaseFragment {
    @BindView(R.id.list_view)
    ListView listView;
    private HomePagerAdapter homePagerAdapter;

    public static HomePagerFragment newInstance(){
        HomePagerFragment fragment = new HomePagerFragment();
        return fragment;
    }

    @Override
    protected int initResource() {
        return R.layout.home_pager_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initOperation() {
         request();
    }

    private void request(){
        OkHttpUtils.get().url(ControlUrl.PALM_GET_BUS_LIST).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                BusListModel listModel = JSONHelper.fromJSONObject(response,BusListModel.class);
                if(homePagerAdapter == null){
                    homePagerAdapter = new HomePagerAdapter(mContext,listModel.getLineList());
                }
                listView.setAdapter(homePagerAdapter);
            }
        });
    }

}
