package com.palm.palmbus.ui.main.contract;

import com.palm.palmbus.bean.HomeListBean;
import com.palm.palmbus.ui.base.BaseModel;
import com.palm.palmbus.ui.base.BasePresenter;
import com.palm.palmbus.ui.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Robin on 2017/1/5.
 *    interface View extends BaseView {
 void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
 }
 abstract static class Presenter extends BasePresenter<View, Model> {
 public abstract void lodeMineChannelsRequest();
 }
 */

public interface HomePagerContract {

    interface Model extends BaseModel{
        Observable< List<HomeListBean> > loadHomeListBen();
    }

    interface View extends BaseView{
         void returnHomePagerListBean(List<HomeListBean> homeListBeanList);
    }

    abstract static class Presenter extends BasePresenter<View,Model>{
        public abstract void loadHomeListBeanRequest();
    }
}
