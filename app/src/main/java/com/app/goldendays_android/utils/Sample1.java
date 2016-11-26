package com.app.goldendays_android.utils;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lijing on 2016/11/25.
 */

@SuppressWarnings("ResourceAsColor")
public class Sample1 implements Serializable {

    int color = 0;
    String name="";

    public Sample1(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }
    @BindingAdapter("bind:colorTint")
    public static void setColorTint(ImageView view, @ColorRes int color) {
//int c;
        DrawableCompat.setTint(view.getDrawable(),color);
//        DrawableCompat.setTint(,c);
//        view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
