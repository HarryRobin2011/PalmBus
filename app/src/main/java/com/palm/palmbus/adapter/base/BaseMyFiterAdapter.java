package com.palm.palmbus.adapter.base;

import android.content.Context;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 2015/1/28.
 */
public class BaseMyFiterAdapter<T> extends BaseMyAdapter implements Filterable {
    private ArrayList<T> mOriginalValues;
    private BaseFilter mFilter;
    private final Object mLock = new Object();
    private String mFilterTag; // 过滤字段，反射获取值


    public BaseMyFiterAdapter(Context context, List dataList, String filterTag) {
        super(context, dataList);
        this.mFilterTag = filterTag;
    }

    @Override
    protected View createCellView() {
        return null;
    }

    @Override
    public BusinessHolder createCellHolder(View cellView) {
        return null;
    }

    @Override
    protected View buildData(int position, View cellView, BusinessHolder cellHolder) {
        return null;
    }

    @Override
    public Filter getFilter() {
        if(mFilter == null){
            mFilter = new BaseFilter();
        }
        return mFilter;
    }

    private class BaseFilter extends Filter {
        Field field = null;
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<T>(dataList);
                }
            }

            if (constraint == null || constraint.length() == 0) {
                ArrayList<T> list;
                synchronized (mLock) {
                    list = new ArrayList<T>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = constraint.toString().toLowerCase();

                ArrayList<T> values;
                synchronized (mLock) {
                    values = new ArrayList<T>(mOriginalValues);
                }

                final int count = values.size();
                final ArrayList<T> newValues = new ArrayList<T>();

                for (int i = 0; i < count; i++) {
                    final T value = values.get(i);
                    try {
                        field = value.getClass().getDeclaredField(mFilterTag);
                        field.setAccessible(true);
                        final String valueText = field.get(value).toString().toLowerCase();

                        // First match against the whole, non-splitted value
                        if (valueText.indexOf(prefixString)!=-1) {
                            newValues.add(value);
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList = (List<T>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
