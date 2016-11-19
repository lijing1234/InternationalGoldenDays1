package com.app.goldendays_android.ui.activity;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import butterknife.OnPageChange;

import static java.lang.String.format;


public class PdfActivity extends Activity implements OnDrawListener, OnPageChangeListener,OnLoadCompleteListener{



    PDFView pdfView;


    public static final String SAMPLE_FILE = "sample.pdf";

    public static final String ABOUT_FILE = "about.pdf";








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView = (PDFView) findViewById(R.id.pdfview);
        pdfView.fromAsset(SAMPLE_FILE)
                .pages(0, 1, 2, 4, 5, 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onDraw(this)
                .onLoad(this)
                .onPageChange(this)
                .load();



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
