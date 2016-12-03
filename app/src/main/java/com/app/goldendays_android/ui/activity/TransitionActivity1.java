package com.app.goldendays_android.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;

import com.app.goldendays_android.R;
import com.app.goldendays_android.databinding.ActivityTransition1Binding;
import com.app.goldendays_android.ui.activity.BaseDetailActivity;
import com.app.goldendays_android.ui.activity.TransitionActivity2;
import com.app.goldendays_android.ui.activity.TransitionActivity3;
import com.app.goldendays_android.utils.Sample;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

public class TransitionActivity1 extends BaseDetailActivity implements OnDrawListener, OnPageChangeListener,OnLoadCompleteListener {

    private Sample sample;
    private int[] pageNumbers =new int[]{0,1,2,3,4,5};
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityTransition1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition1);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }


    private void setupLayout() {
        pdfView = (PDFView) findViewById(R.id.pdfview);
        pdfView.fromFile(new File(Environment.getExternalStorageDirectory()
                .getPath()+"/2.pdf"))
                .pages(pageNumbers)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onDraw(this)
                .onLoad(this)
                .onPageChange(this)
                .load();
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // This view will not be affected by enter transition animation
        enterTransition.excludeTarget(R.id.square_red, true);
        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }
    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
