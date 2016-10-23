package com.palm.palmbus.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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

    private String[] titles = new String[]{"首页", "换乘", "生活", "我的"};
    private int[] titleIcons = new int[]{R.mipmap.home, R.mipmap.line, R.mipmap.life, R.mipmap.me};


    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    /**
     *  bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
     bottomNavigationBar
     .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
     );
     bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange))
     .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
     .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue))
     .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
     .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "Games").setActiveColorResource(R.color.grey))
     .setFirstSelectedPosition(0)
     .initialise();

     */
    @Override
    protected void initData() {
        buttomBar.setMode(BottomNavigationBar.MODE_FIXED);
        buttomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        for (int i = 0; i < titles.length; i++) {
            int resId = titleIcons[i];
            buttomBar.addItem(new BottomNavigationItem(resId,titles[0]));
        }
        buttomBar.setFirstSelectedPosition(0).initialise();
        initFragment();
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        currentFragment = (HomePagerFragment) fm.findFragmentByTag(titles[0]);
        if (currentFragment == null) {
            currentFragment = HomePagerFragment.newInstance();
        }
        transaction.add(R.id.content, currentFragment, titles[0]).commitAllowingStateLoss();
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
       if(currentFragment.isAdded()){
           transaction.show(currentFragment).commitAllowingStateLoss();
       }else{
           transaction.add(currentFragment,titles[position]).commitAllowingStateLoss();
       }
    }

    @Override
    public void onTabUnselected(int position) {
        currentFragment = (BaseFragment) fm.findFragmentByTag(titles[position]);
        if(currentFragment != null){
            transaction.hide(currentFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
