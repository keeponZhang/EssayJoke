package com.zhang.app.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * createBy	 keepon
 */
public class MyView2 extends View {


	public MyView2(Context context) {
		super(context);
	}

	public MyView2(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int baseLineY = 200;
		int baseLineX = 0;

		Paint paint = new Paint();
		//写文字
		paint.setColor(Color.GREEN);
		paint.setTextSize(120); //以px为单位
		paint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText("harvic\'s blog2", baseLineX, baseLineY, paint);

		//计算各线在位置
		//		ascent线Y坐标 = baseline线的y坐标 + fontMetric.ascent；
		//		descent线Y坐标 = baseline线的y坐标 + fontMetric.descent；
		//		top线Y坐标 = baseline线的y坐标 + fontMetric.top；
		//		bottom线Y坐标 = baseline线的y坐标 + fontMetric.bottom；

		Paint.FontMetrics fm = paint.getFontMetrics();
		float ascent = baseLineY + fm.ascent;
		float descent = baseLineY + fm.descent;
		float top = baseLineY + fm.top;
		float bottom = baseLineY + fm.bottom;

		Log.e("TAG", "MyView2 onDraw fm.ascent:" + fm.ascent + " fm.descent=" + fm.descent + " fm.top=" + fm.top + " fm.bottom=" + fm.bottom);
		//画基线
		paint.setColor(Color.RED);
		canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

		//画top
		paint.setColor(Color.BLUE);
		canvas.drawLine(baseLineX, top, 3000, top, paint);

		//画ascent
		paint.setColor(Color.GREEN);
		canvas.drawLine(baseLineX, ascent, 3000, ascent, paint);

		//画descent
		paint.setColor(Color.YELLOW);
		canvas.drawLine(baseLineX, descent, 3000, descent, paint);

		//画bottom
		paint.setColor(Color.RED);
		canvas.drawLine(baseLineX, bottom, 3000, bottom, paint);

	}

}

//		FontMetrics::ascent;
//		FontMetrics::descent;
//		FontMetrics::top;
//		FontMetrics::bottom;
//       ascent=ascent线的y坐标-baseline线的y坐标；
//		descent=descent线的y坐标-baseline线的y坐标；
//		top=top线的y坐标-baseline线的y坐标；
//		bottom=bottom线的y坐标-baseline线的y坐标；
//		————————————————
//		版权声明：本文为CSDN博主「启舰」的原创文章，遵循 CC4.0BY-SA 版权协议，转载请附上原文出处链接及本声明。
//		原文链接：https://blog.csdn.net/harvic880925/article/details/50423762