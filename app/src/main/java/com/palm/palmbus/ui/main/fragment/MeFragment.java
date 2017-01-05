package com.palm.palmbus.ui.main.fragment;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Robin on 2016/11/19.
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.title_layout)
    LinearLayout titleLayout;
    @BindView(R.id.login_head_icon)
    ImageView loginHeadIcon;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.check_in)
    TextView checkIn;
    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.me_timely_reminders)
    LinearLayout meTimelyReminders;

    public static MeFragment newInstance() {
        MeFragment meFragment = new MeFragment();
        return meFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.me_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

}
