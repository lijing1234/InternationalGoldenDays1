package com.app.goldendays_android.ui.activity;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.goldendays_android.R;
import com.app.goldendays_android.base.BaseActivity;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import butterknife.OnPageChange;

import static java.lang.String.format;


public class PdfActivity extends Activity implements OnDrawListener, OnPageChangeListener,OnLoadCompleteListener{


    private int[] pageNumbers =new int[]{0,1,2,3,4,5};
    PDFView pdfView;


    public static final String SAMPLE_FILE = Environment.getExternalStorageDirectory()
            .getPath()+"/2.pdf";

    public static final String ABOUT_FILE = "about.pdf";








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
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
