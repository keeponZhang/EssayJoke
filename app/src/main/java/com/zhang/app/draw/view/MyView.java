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
public class MyView extends View {


	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int baseLineX = 0;
		int baseLineY = 200;

		//画基线
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

		//写文字
		paint.setColor(Color.GREEN);
		paint.setTextSize(120); //以px为单位
		canvas.drawText("harvic\'s blog", baseLineX, baseLineY, paint);
	}

}
