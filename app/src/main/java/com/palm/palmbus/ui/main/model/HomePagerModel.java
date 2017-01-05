package com.palm.palmbus.ui.main.model;

import com.palm.palmbus.MyApplication;
import com.palm.palmbus.bean.HomeListBean;
import com.palm.palmbus.api.AppConstant;
import com.palm.palmbus.ui.main.contract.HomePagerContract;
import com.palm.palmbus.utils.ACache;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Robin on 2017/1/5.
 *
 *      return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
@Override
public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MINE);
if(newsChannelTableList==null){
newsChannelTableList= (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,newsChannelTableList);
}
subscriber.onNext(newsChannelTableList);
subscriber.onCompleted();
}
}).compose(RxSchedulers.<List<NewsChannelTable>>io_main());
 */

public class HomePagerModel implements HomePagerContract.Model {
    @Override
    public Observable<List<HomeListBean>> loadHomeListBen() {
        return Observable.create(new Observable.OnSubscribe<List<HomeListBean>>() {
            @Override
            public void call(Subscriber<? super List<HomeListBean>> subscriber) {
                LinkedList<HomeListBean> homeListBeanLinkedList = (LinkedList<HomeListBean>) ACache.get(MyApplication.getAppContext()).getAsObject(AppConstant.HOME_PAGER);
                if(homeListBeanLinkedList == null){
                }
            }
        });
    }
}
