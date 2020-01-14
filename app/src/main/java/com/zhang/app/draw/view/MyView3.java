package com.zhang.app.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * createBy	 keepon
 */
public class MyView3 extends View {


	public MyView3(Context context) {
		super(context);
	}

	public MyView3(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onDraw(Canvas canvas) {

		String text = "harvic\'s blog3";
		int baseLineY = 200;
		int baseLineX = 0 ;

		//设置paint
		Paint paint = new Paint();
		paint.setTextSize(120); //以px为单位
		paint.setTextAlign(Paint.Align.LEFT);

		//画text所占的区域
		Paint.FontMetricsInt fm = paint.getFontMetricsInt();
		int top = baseLineY + fm.top;
		int bottom = baseLineY + fm.bottom;
		int width = (int)paint.measureText(text);
		Rect rect = new Rect(baseLineX,top,baseLineX+width,bottom);

		paint.setColor(Color.GREEN);
		canvas.drawRect(rect,paint);

		//画最小矩形
		Rect minRect = new Rect();
		paint.getTextBounds(text,0,text.length(),minRect);

		//相对于当前控件来说
		Log.e("TAG","minRect.toShortString():"+minRect.toShortString());
		minRect.top = baseLineY + minRect.top;
		minRect.bottom = baseLineY + minRect.bottom;
		paint.setColor(Color.RED);
		canvas.drawRect(minRect,paint);
		Log.e("TAG","after minRect.toShortString():"+minRect.toShortString());

		//写文字
		paint.setColor(Color.BLACK);
		canvas.drawText(text, baseLineX, baseLineY, paint);

	}

}
