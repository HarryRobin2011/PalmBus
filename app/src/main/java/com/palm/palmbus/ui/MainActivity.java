package com.palm.palmbus.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.ui.base.BasePalmActivity;
import com.palm.palmbus.ui.fragment.HomePagerFragment;

import butterknife.BindView;

public class MainActivity extends BasePalmActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.buttom_bar)
    BottomNavigationBar buttomBar;
    private BaseFragment currentFragment;
    private FragmentTransaction transaction;
    private FragmentManager fm;

    private String[] titles = new String[]{"实时", "换乘","我的"};
    private int[] titleIcons = new int[]{R.mipmap.home, R.mipmap.line, R.mipmap.me};


    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        buttomBar.setMode(BottomNavigationBar.MODE_FIXED);
        buttomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );
        for (int i = 0; i < titles.length; i++) {
            int resId = titleIcons[i];
            buttomBar.addItem(new BottomNavigationItem(resId, titles[i]));
        }
        buttomBar.setFirstSelectedPosition(0).initialise();
        initFragment();
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        if (currentFragment == null) {
            currentFragment = HomePagerFragment.newInstance();
        }
        transaction.add(R.id.content, currentFragment, titles[0]).commit();
    }

    @Override
    protected void initOperation() {

    }

    @Override
    public void onTabSelected(int position) {
        currentFragment = (BaseFragment) fm.findFragmentByTag(titles[position]);
        switch (position) {
            case 0:
                if (currentFragment == null) {
                    currentFragment = new HomePagerFragment();
                }
                break;
            case 1:
                if (currentFragment == null) {
                    currentFragment = new HomePagerFragment();
                }
                break;
            case 2:
                if (currentFragment == null) {
                    currentFragment = new HomePagerFragment();
                }
                break;
        }
        if (currentFragment.isAdded()) {
            transaction.show(currentFragment).commitAllowingStateLoss();
        } else {
            transaction.add(currentFragment, titles[position]).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {
        currentFragment = (BaseFragment) fm.findFragmentByTag(titles[position]);
        if (currentFragment != null) {
            transaction.hide(currentFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
