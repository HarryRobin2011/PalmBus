package com.palm.palmbus.ui.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseActivity;
import com.palm.palmbus.ui.base.BaseFragment;
import com.palm.palmbus.ui.main.fragment.FineFragment;
import com.palm.palmbus.ui.main.fragment.HomePagerFragment;
import com.palm.palmbus.ui.main.fragment.MeFragment;
import com.palm.palmbus.ui.main.fragment.TransferFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.buttom_bar)
    BottomNavigationBar buttomBar;
    @BindView(R.id.content)
    FrameLayout content;
    private BaseFragment currentFragment;

    private String[] titles = new String[]{"实时", "换乘", "美食", "我的"};
    private int[] titleIcons = new int[]{R.mipmap.bus_tab1, R.mipmap.bus_tab2, R.mipmap.bus_tab3, R.mipmap.bus_tab4};

    private String permissionInfo;

    private final int SDK_PERMISSION_REQUEST = 127;


    FragmentManager fm = getSupportFragmentManager();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getPersimmions();
        buttomBar.setMode(BottomNavigationBar.MODE_FIXED);
        buttomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );
        buttomBar.setTabSelectedListener(this);
        for (int i = 0; i < titles.length; i++) {
            int resId = titleIcons[i];
            buttomBar.addItem(new BottomNavigationItem(resId, titles[i]));
        }
        buttomBar.setFirstSelectedPosition(0).initialise();

    }

    @Override
    protected void initOperation() {
        initFragment();
    }


    private void initFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        if (currentFragment == null) {
            currentFragment = HomePagerFragment.newInstance();
        }
        transaction.add(R.id.content, currentFragment, titles[0]).commitAllowingStateLoss();
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void onTabSelected(int position) {
        Fragment currentFragment = fm.findFragmentByTag(titles[position]);
        switch (position) {
            case 0:
                if (currentFragment == null) {
                    currentFragment = HomePagerFragment.newInstance();
                }
                break;
            case 1:
                if (currentFragment == null) {
                    currentFragment = TransferFragment.newInstance();
                }
                break;
            case 2:
                if (currentFragment == null) {
                    currentFragment = FineFragment.newInstance();
                }
                break;
            case 3:
                if (currentFragment == null) {
                    currentFragment = MeFragment.newInstance();
                }
                break;
        }
        if (currentFragment.isAdded()) {
            fm.beginTransaction().show(currentFragment).commitAllowingStateLoss();
        } else {
            fm.beginTransaction().add(R.id.content, currentFragment, titles[position]).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {
        Fragment currentFragment = fm.findFragmentByTag(titles[position]);
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
