package com.app.goldendays_android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseFragment;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.common.collect.Maps;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener {

    @Bind(R.id.home_slider)
    SliderLayout homeSlider;
    @Bind(R.id.home_indicator)
    PagerIndicator homeIndicator;

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

        initSlider();
        return view;
    }

    private void initSlider() {
        HashMap<String, String> url_maps = Maps.newHashMap();
        url_maps.put("Hannibal", "http://i0.hdslb.com/bfs/archive/9bffe5986953bcd1894faf23626c5d7f0a9a9336.jpg");
        url_maps.put("Big Bang Theory", "http://imgbdb3.bendibao.com/weixinbdb/201610/8/2016108161812767.jpg");
        url_maps.put("House of Cards", "http://5.1015600.com/2014/pic/000/350/e25c4207a1e2bc5f0626877998cd10e1.jpg");
        url_maps.put("Game of Thrones", "http://www.people.com.cn/mediafile/pic/20141204/12/6577380608561484372.jpg");

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
//            homeSlider.addOnPageChangeListener(this);
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
}
