package com.app.goldendays_android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * 带滚动监听的scrollview
 *
 */
public class GradationScrollView extends ScrollView {

	public interface ScrollViewListener {

		void onScrollChanged(GradationScrollView scrollView, int x, int y,
							 int oldx, int oldy);

	}

	private ScrollViewListener scrollViewListener = null;

	public GradationScrollView(Context context) {
		super(context);
	}

	public GradationScrollView(Context context, AttributeSet attrs,
							   int defStyle) {
		super(context, attrs, defStyle);
	}

	public GradationScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = (ScrollViewListener) scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
	/**
	 * 滑动事件
	 */
	@Override
	public void fling(int velocityY) {
		super.fling(velocityY / 1000);//这里设置滑动的速度
	}

}