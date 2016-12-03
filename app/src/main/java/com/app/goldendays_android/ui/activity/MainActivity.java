package com.app.goldendays_android.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.goldendays_android.R;
import com.app.goldendays_android.adapter.SamplesRecyclerAdapter;
import com.app.goldendays_android.base.BaseActivity;
import com.app.goldendays_android.ui.view.GradationScrollView;
import com.app.goldendays_android.utils.Sample;
import com.app.goldendays_android.utils.Sample1;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.yanzhenjie.album.Album;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;


@SuppressWarnings("ResourceType")
public class MainActivity extends BaseActivity implements GradationScrollView.ScrollViewListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener {
    private List<Sample> samples;
    private GradationScrollView scrollView;
    private TextView textView;
    private int imageHeight;
    private SliderLayout mDemoSlider;
    private LinearLayout movie;
    private LinearLayout pdf;
    private LinearLayout alum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        Timber.tag("MainActivity");
        Timber.w("Activity Created");
        setupWindowAnimations();
        setupSamples();
        setupLayout();


    }

    private void setupSamples() {
        samples = Arrays.asList(

                new Sample(ContextCompat.getColor(this, R.color.sample_red), "pdf"),
                new Sample(ContextCompat.getColor(this, R.color.material_animations_primary_dark), "视频"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "图库"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
        );
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter samplesRecyclerAdapter = new SamplesRecyclerAdapter(this, samples);
        recyclerView.setAdapter(samplesRecyclerAdapter);
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity

        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private void initView() {
        scrollView = (GradationScrollView) findViewById(R.id.scrollview);
        textView = (TextView) findViewById(R.id.textview);
        mDemoSlider = (SliderLayout) findViewById(R.id.slideshowView);
        movie = (LinearLayout) findViewById(R.id.activity_main_movin);
        pdf = (LinearLayout) findViewById(R.id.activity_main_pdf);
        alum=(LinearLayout) findViewById(R.id.activity_main_alum);
        movie.setOnClickListener(this);
        pdf.setOnClickListener(this);
        alum.setOnClickListener(this);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.hannibal);
        file_maps.put("Big Bang Theory", R.drawable.bigbang);
        file_maps.put("House of Cards", R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setFocusable(true);
        mDemoSlider.setFocusableInTouchMode(true);
        mDemoSlider.requestFocus();
        initListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDemoSlider.startAutoCycle();
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {
        ViewTreeObserver vto = mDemoSlider.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDemoSlider.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = mDemoSlider.getHeight();

                scrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    /**
     * 滑动监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144, 151, 166));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 255, 144, 151, 166));
        }
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_movin:
                Intent intent = new Intent(this, MovieActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_pdf:
                Intent intent1 = new Intent(this, PdfActivity.class);
                startActivity(intent1);
                break;
            case R.id.activity_main_alum:
                Album.startAlbum(this, 1
                        , 9                                                         // 指定选择数量。
                        , ContextCompat.getColor(this, R.color.colorAccent)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(this, R.color.tab_select));  // 指定状态栏的颜色。
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // 判断是否成功。
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(data);
            } else if (resultCode == RESULT_CANCELED) { // 用户取消选择。
                // 根据需要提示用户取消了选择。
            }
        }
    }

}
