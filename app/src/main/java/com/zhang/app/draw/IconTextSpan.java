package com.zhang.app.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.util.TypedValue;

import com.zhang.app.R;

public class IconTextSpan extends ReplacementSpan {
	private Context mContext;
	private int     mBgColorResId; //Icon背景颜色
	private String  mText;  //Icon内文字
	private float   mBgHeight;  //Icon背景高度
	private float   mBgWidth;  //Icon背景宽度
	private float   mRadius;  //Icon圆角半径
	private float   mRightMargin; //右边距
	private float   mTextSize; //文字大小
	private int     mTextColorResId; //文字颜色

	private Paint mBgPaint; //icon背景画笔
	private Paint mTextPaint; //icon文字画笔

	private float mPaddingLeft;
	private float mPaddingRight;
	private float mPaddingTop;
	private float mPaddingBottom;

	public IconTextSpan(Context context, int bgColorResId, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		//初始化默认数值
		initDefaultValue(context, bgColorResId, text);
		//初始化画笔
		initPaint();

		this.mBgWidth = caculateBgWidth(text);
		this.mBgHeight = caculateBgHeight(mTextPaint, text);
		//计算背景的宽度
		if (text.length() == 1) {
			if (this.mBgHeight >= mBgWidth) {
				mBgWidth = mBgHeight;
			} else {
				mBgWidth = mBgHeight;
			}
		}

	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		//初始化背景画笔
		mBgPaint = new Paint();
		mBgPaint.setColor(mContext.getResources().getColor(mBgColorResId));
		mBgPaint.setStyle(Paint.Style.FILL);
		mBgPaint.setAntiAlias(true);

		//初始化文字画笔
		mTextPaint = new TextPaint();
		mTextPaint.setColor(mContext.getResources().getColor(mTextColorResId));
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	/**
	 * 初始化默认数值
	 *
	 * @param context
	 */
	private void initDefaultValue(Context context, int bgColorResId, String text) {
		this.mContext = context.getApplicationContext();
		this.mBgColorResId = bgColorResId;
		this.mText = text;
		this.mBgHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27f, mContext.getResources().getDisplayMetrics());
		this.mRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mContext.getResources().getDisplayMetrics());
		this.mRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mContext.getResources().getDisplayMetrics());
		this.mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, mContext.getResources().getDisplayMetrics());
		this.mTextColorResId = R.color.white;
		this.mTextColorResId = R.color.white;
		mPaddingBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mContext.getResources().getDisplayMetrics());
		mPaddingTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mContext.getResources().getDisplayMetrics());
		mPaddingLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, mContext.getResources().getDisplayMetrics());
		mPaddingRight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, mContext.getResources().getDisplayMetrics());
	}

	/**
	 * 计算icon背景宽度
	 *
	 * @param text icon内文字
	 */
	private float caculateBgWidth(String text) {
		Rect textRect = new Rect();
		Paint paint = new Paint();
		paint.setTextSize(mTextSize);
		paint.getTextBounds(text, 0, text.length(), textRect);
		return textRect.width() + mPaddingLeft + mPaddingRight;
	}

	private float caculateBgHeight(Paint paint, String text) {
		Paint.FontMetrics metrics = paint.getFontMetrics();
		float textHeight = metrics.descent - metrics.ascent;
		return textHeight + mPaddingTop + mPaddingBottom;
	}


	/**
	 * 设置右边距
	 *
	 * @param rightMarginDpValue
	 */
	public void setRightMarginDpValue(int rightMarginDpValue) {
		this.mRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightMarginDpValue, mContext.getResources().getDisplayMetrics());
	}

	/**
	 * 设置宽度，宽度=背景宽度+右边距
	 */
	@Override
	public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
		return (int) (mBgWidth + mRightMargin);
	}

	/**
	 * draw
	 *
	 * @param text   完整文本
	 * @param start  setSpan里设置的start
	 * @param end    setSpan里设置的start
	 * @param x      要绘制的image的左边框到textview左边框的距离。
	 * @param top    当前span所在行的上方y
	 * @param y      y其实就是metric里baseline的位置
	 * @param bottom 当前span所在行的下方y(包含了行间距)，会和下一行的top重合
	 * @param paint  使用此span的画笔
	 */
	@Override
	public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
		Log.e("TAG", "IconTextSpan draw x:" + x + " y=" + y + " text=" + text);
		Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
		float textHeight = metrics.descent - metrics.ascent;
		//算出背景开始画的y坐标
		float bgStartY = y + (textHeight - mBgHeight) / 2 + metrics.ascent;

		//画背景
		RectF bgRect = new RectF(x, bgStartY, x + mBgWidth, bgStartY + mBgHeight);
		canvas.drawRoundRect(bgRect, mRadius, mRadius, mBgPaint);

		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		float textRectHeight = fontMetrics.bottom - fontMetrics.top;
		canvas.drawText(mText, x + mBgWidth / 2, bgStartY + (mBgHeight - textRectHeight) / 2 - fontMetrics.top, mTextPaint);
	}
}
