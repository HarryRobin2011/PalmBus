package com.palm.palmbus.ui.base;

/**
 * Created by Robin on 2017/1/5.
 */

public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
