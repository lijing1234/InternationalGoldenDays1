package com.app.goldendays_android.ui.fragment;

import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseFragment;
import com.app.goldendays_android.ui.view.StatusBarCompat;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.common.collect.Maps;
import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, ObservableScrollViewCallbacks {

    @Bind(R.id.home_slider)
    SliderLayout homeSlider;
    @Bind(R.id.home_indicator)
    PagerIndicator homeIndicator;
    Toolbar toolbar;
    @Bind(R.id.toolbar)
    View mToolbarView;
    @Bind(R.id.scroll)
    ObservableScrollView mScrollView;
    @Bind(R.id.home_search_tv)
    TextView homeSearchTv;
    @Bind(R.id.home_news_img)
    ImageView homeNewsImg;
    @Bind(R.id.home_news_text)
    TextView homeNewsText;
    @Bind(R.id.home_logo)
    ImageView homeLogo;

    private int mParallaxImageHeight;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        initSlider();
        return view;
    }

    private void initView() {
        toolbar = (Toolbar) mToolbarView;
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
    }

    private void initSlider() {
        HashMap<String, String> url_maps = Maps.newHashMap();
        url_maps.put("the eye of cat", "https://img1.doubanio.com/view/photo/photo/public/p1495098999.jpg");
        url_maps.put("bicycle", "https://img3.doubanio.com/view/photo/photo/public/p1495096511.jpg");
        url_maps.put("Hourglass", "https://img3.doubanio.com/view/photo/photo/public/p1498739261.jpg");
        url_maps.put("camera", "https://img1.doubanio.com/view/photo/photo/public/p1498739119.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            homeSlider.addSlider(textSliderView);
            homeSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            homeSlider.setCustomAnimation(new DescriptionAnimation());
            homeSlider.setDuration(4000);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        homeSlider.stopAutoCycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeSlider.startAutoCycle();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int white = ContextCompat.getColor(getContext(),R.color.white);
        int black = ContextCompat.getColor(getContext(), R.color.black);
        int colorAccent = ContextCompat.getColor(getContext(),R.color.colorAccent);

        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, white));
        homeSearchTv.setTextColor(ScrollUtils.getColorWithAlpha(alpha, black));
        homeLogo.setColorFilter(ScrollUtils.getColorWithAlpha(alpha, colorAccent));
        homeNewsImg.setColorFilter(ScrollUtils.getColorWithAlpha(alpha, black));
        homeNewsText.setTextColor(ScrollUtils.getColorWithAlpha(alpha,black));
        StatusBarCompat.compat(getActivity(),ScrollUtils.getColorWithAlpha(alpha,white));
        if (scrollY <= mParallaxImageHeight / 2) {
            homeSearchTv.setTextColor(ContextCompat.getColor(getContext(),R.color.grey));
            homeLogo.setColorFilter(white);
            homeNewsImg.setColorFilter(white);
            homeNewsText.setTextColor(white);
            StatusBarCompat.compat(getActivity(),black);
        }
        ViewHelper.setTranslationY(homeSlider, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}
