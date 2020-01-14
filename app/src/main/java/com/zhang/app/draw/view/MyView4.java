package com.zhang.app.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * createBy	 keepon
 */
public class MyView4 extends View {


	public MyView4(Context context) {
		super(context);
	}

	public MyView4(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		String text = "harvic\'s blog";
		int center = 100;
		int baseLineX = 0 ;

		//设置paint
		Paint paint = new Paint();
		paint.setTextSize(120); //以px为单位
		paint.setTextAlign(Paint.Align.LEFT);

		//画center线
		paint.setColor(Color.YELLOW);
		canvas.drawLine(baseLineX, center, 3000, center, paint);

		//计算出baseLine位置
		Paint.FontMetricsInt fm = paint.getFontMetricsInt();
		int baseLineY = center + (fm.bottom - fm.top)/2 - fm.bottom;

		//画基线
		paint.setColor(Color.RED);
		canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

		//写文字
		paint.setColor(Color.GREEN);
		canvas.drawText(text, baseLineX, baseLineY, paint);


	}
//	baseline = center + (FontMetrics.bottom - FontMetrics.top)/2 - FontMetrics.bottom;
}
