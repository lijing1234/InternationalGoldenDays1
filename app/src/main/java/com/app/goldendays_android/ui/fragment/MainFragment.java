package com.app.goldendays_android.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseFragment;
import com.app.goldendays_android.ui.view.BottomBar;
import com.app.goldendays_android.ui.view.BottomBarTab;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    private BottomBar mBottomBar;

    public static MainFragment newInstance() {


        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = NearbyFragment.newInstance();
            mFragments[THIRD] = DiscoverFragment.newInstance();
            mFragments[FOURTH] = ShopCartFragment.newInstance();
            mFragments[FIFTH] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findChildFragment(HomeFragment.class);
            mFragments[SECOND] = findChildFragment(NearbyFragment.class);
            mFragments[THIRD] = findChildFragment(DiscoverFragment.class);
            mFragments[FOURTH] = findChildFragment(ShopCartFragment.class);
            mFragments[FIFTH] = findChildFragment(MeFragment.class);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_none, "首页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_nearby_none, "附近"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_discover_none, "发现"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_shopcat_none, "购物车"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_me_none, "我"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}