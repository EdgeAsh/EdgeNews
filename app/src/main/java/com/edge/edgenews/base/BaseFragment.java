package com.edge.edgenews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * 1,初始化布局 initView();
 * 2,初始化数据 initData();
 *
 * Created by edge on 25/02/2017.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;

    /**
     * fragment创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity(); // 得到fragment所在activity
    }

    /**
     * 初始化fragment布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    /**
     * activity创建结束
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化布局，子类必须实现
     * @return
     */
    public abstract View initView();
}
