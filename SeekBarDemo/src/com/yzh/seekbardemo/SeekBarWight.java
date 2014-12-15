package com.yzh.seekbardemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class SeekBarWight extends SeekBar {

	private Paint mThumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mThumbWidth, mThumbHeight;
	private Rect mTextRect = new Rect();

	@Override
	public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
		super.setOnSeekBarChangeListener(l);
	}

	public SeekBarWight(Context context) {
		super(context);
		init();
	}

	public SeekBarWight(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SeekBarWight(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.seekbar_thumb_bgblue);
		mThumbWidth = bmp.getWidth();
		mThumbHeight = bmp.getHeight();
		bmp.recycle();

		mThumbPaint.setStyle(Style.FILL);
		mThumbPaint.setColor(Color.WHITE);
		mThumbPaint.setTextSize(dip2px(18));

		setThumbOffset(0);
	}

	private int dip2px(float dpValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
    
	public int px2dip(float pxValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		String text = getProgress() + "%";

		mThumbPaint.getTextBounds(text, 0, text.length(), mTextRect);
		if (text.length() == 2) {
			canvas.drawText(text, getThumbPosX() + (mThumbWidth - mTextRect.width()) + dip2px(15)/2, (mThumbHeight + mTextRect.height()) / 2, mThumbPaint);
		} else if (text.length() == 3) {
			canvas.drawText(text, getThumbPosX() + (mThumbWidth - mTextRect.width()) + dip2px(20)/2, (mThumbHeight + mTextRect.height()) / 2, mThumbPaint);
		} else if (text.length() == 4) {
			canvas.drawText(text, getThumbPosX() + (mThumbWidth - mTextRect.width()) + dip2px(25)/2, (mThumbHeight + mTextRect.height()) / 2, mThumbPaint);
		}
	}

	private int getThumbPosX() {
		int max = getMax();
		float scale = max > 0 ? (float) getProgress() / (float) max : 0;

		int available = getWidth() - getPaddingLeft() - getPaddingRight();
		available -= mThumbWidth;

		available += getThumbOffset() * 2;

		int thumbPos = (int) (scale * available);

		return thumbPos;
	}
}
