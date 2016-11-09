package com.app.goldendays_android.ui.view;

import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lijing on 2016/11/9.
 */

public class MeasureView {
    public void measureLv(ListView lv, ListAdapter adapter) {
        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View itemView = adapter.getView(i, null, null);
            itemView.measure
                    (View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            height += itemView.getMeasuredHeight();
        }
        height = height +
                lv.getDividerHeight() * (adapter.getCount() - 1)
                + lv.getPaddingTop() + lv.getPaddingBottom();
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv.getLayoutParams();
        lp.height = height;
        lv.setLayoutParams(lp);
//        requestLayout();
    }

    public int measureGv(GridView lv, ListAdapter adapter) {
        int height = 0;
        int height1=0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View itemView = adapter.getView(i, null, null);
            itemView.measure
                    (View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            height += itemView.getMeasuredHeight();
            height1=itemView.getMeasuredHeight();
        }
        if(adapter.getCount()%2==0){
            height = height/2 +lv.getPaddingTop() + lv.getPaddingBottom()+20;
        }else{
            height = (height+height1)/2 +lv.getPaddingTop() + lv.getPaddingBottom()+20;
        }



        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv.getLayoutParams();
        lp.height = height;
        lv.setLayoutParams(lp);
        return height;
//        requestLayout();
    }
}
