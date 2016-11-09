package com.app.goldendays_android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.goldendays_android.R;
import com.app.goldendays_android.adapter.MeFragmentAdapter;
import com.app.goldendays_android.base.BaseFragment;
import com.app.goldendays_android.ui.view.CyclerImg;
import com.app.goldendays_android.ui.view.GradationScrollView;
import com.app.goldendays_android.ui.view.MeasureView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment  {
    @Bind(R.id.fragment_me_message)
    LinearLayout fragmentMeMessage;
    @Bind(R.id.fragment_me_setting)
    LinearLayout fragmentMeSetting;
    @Bind(R.id.fragment_me_username)
    TextView fragmentMeUsername;
    @Bind(R.id.fragment_me_member)
    TextView fragmentMeMember;
    @Bind(R.id.fragment_me_privilege)
    TextView fragmentMePrivilege;
    @Bind(R.id.fragment_me_accountmanagement)
    LinearLayout fragmentMeAccountmanagement;
    @Bind(R.id.fragment_me_userhead)
    CyclerImg fragmentMeUserhead;
    RelativeLayout fragmentMeTopRelativelayout;
    @Bind(R.id.fragment_me_obligation)
    LinearLayout fragmentMeObligation;
    @Bind(R.id.fragment_me_receive)
    LinearLayout fragmentMeReceive;
    @Bind(R.id.fragment_me_tobedelivered)
    LinearLayout fragmentMeTobedelivered;
    @Bind(R.id.fragment_me_returned)
    LinearLayout fragmentMeReturned;
    @Bind(R.id.fragment_me_orders)
    LinearLayout fragmentMeOrders;
    @Bind(R.id.fragment_me_gold)
    LinearLayout fragmentMeGold;
    @Bind(R.id.fragment_me_coupon)
    LinearLayout fragmentMeCoupon;
    @Bind(R.id.fragment_me_shopattention)
    LinearLayout fragmentMeShopattention;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.fragment_me_wallet)
    LinearLayout fragmentMeWallet;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.fragment_me_gridview)
    GridView fragmentMeGridview;
    @Bind(R.id.meiyou)
    LinearLayout meiyou;
    @Bind(R.id.fragment_me_out_linearlayout)
    LinearLayout fragmentMeOutLinearlayout;
    @Bind(R.id.scrollview)
    GradationScrollView scrollview;
    @Bind(R.id.textview)
    TextView textview;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    int height;
    int gvheight;

    public static MeFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        isPrepared = true;
        ButterKnife.bind(this, view);
        fragmentMeTopRelativelayout= (RelativeLayout) view.findViewById(R.id.fragment_me_top_relativelayout);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentMeTopRelativelayout.setFocusable(true);
        fragmentMeTopRelativelayout.setFocusableInTouchMode(true);
        fragmentMeTopRelativelayout.requestFocus();
        initListeners();
        initData();

    }



    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {
        ViewTreeObserver vto = fragmentMeTopRelativelayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = fragmentMeTopRelativelayout.getHeight();
//                scrollview.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
//                    @Override
//                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
//                        if (y <= 0) {   //设置标题的背景颜色
//                            textView.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
//
//                        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//                            float scale = (float) y / height;
//                            float alpha = (255 * scale);
//                            textView.setTextColor(Color.argb((int) alpha, 255, 255, 255));
//                            textView.setBackgroundColor(Color.argb((int) alpha, 144, 151, 166));
//                        } else {    //滑动到banner下面设置普通颜色
//                            textView.setBackgroundColor(Color.argb((int) 255, 144, 151, 166));
//                        }
//                    }
//                });
            }
        });
    }

    private void initData() {
        MeFragmentAdapter adapter = new MeFragmentAdapter(null, getActivity());
        fragmentMeGridview.setAdapter(adapter);
        gvheight = new MeasureView().measureGv(fragmentMeGridview, adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
