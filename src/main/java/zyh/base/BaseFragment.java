package zyh.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mumu on 2019/3/29.
 */

public abstract class BaseFragment extends Fragment {

    //是否是第一次加载数据
    protected boolean mIsFirstGetData = true;
    //fragment对用户是否可见
    protected boolean mIsVisibleToUser;
    //根布局
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //只有为空的时候加载布局，
        // 因为在viewpager中fragment对象不会被销毁，成员变量也不会销毁
        if (mRootView == null) {
            mRootView = inflater.inflate(provideLayoutId(), container, false);
            initView();
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //只要加载过数据，下次再回到该fragment中，肯定会走setUserVisibleHint（true）
        //所以onActivityCreated得地方只要之前加载过数据，就没有机会再次加载
        //否则会重复加载     ps：fragment3直接跳转回fragment1
        if (!mIsFirstGetData) {
            return;
        }
        //懒加载数据
        lazyLoadData();
    }

    //fragment是否需要实时刷新，如果需要返回true，不需要返回false
    protected abstract boolean setIsRealTimeRefresh();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int provideLayoutId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //设置可视状态
        mIsVisibleToUser = isVisibleToUser;

        //懒加载数据
        lazyLoadData();
    }

    public void lazyLoadData() {
        //如果不是每次刷新数据，且如果已经加载过数据，那么不执行initData
        if (!setIsRealTimeRefresh() && !mIsFirstGetData) {
            return;
        }
        //可见并且rootView不为空的时候才能加载数据
        if (mIsVisibleToUser && mRootView != null) {
            mIsFirstGetData = false;
            Log.d("zyh", "initData");
            initData();
        }
    }

}
