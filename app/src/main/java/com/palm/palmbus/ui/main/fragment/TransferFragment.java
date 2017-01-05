package com.palm.palmbus.ui.main.fragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Robin on 2016/10/23.
 */

public class TransferFragment extends BaseFragment {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.location_me)
    TextView locationMe;
    @BindView(R.id.location_me_layout)
    LinearLayout locationMeLayout;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.location_from)
    TextView locationFrom;
    @BindView(R.id.location_from_layout)
    LinearLayout locationFromLayout;

    public static TransferFragment newInstance() {
        TransferFragment transferFragment = new TransferFragment();
        return transferFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.trans_fer_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
