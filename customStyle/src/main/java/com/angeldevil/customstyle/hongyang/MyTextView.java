package com.angeldevil.customstyle.hongyang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.angeldevil.customstyle.R;

public class MyTextView extends View {

    private static final String TAG = MyTextView.class.getSimpleName();

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.test);

        String text = ta.getString(R.styleable.test_text);
        int textAttr = ta.getInteger(R.styleable.test_testAttr, -1);

        Log.d(TAG, "text = " + text + " , textAttr = " + textAttr);

//，如果布局中的属性的值是引用类型（比如：@dimen/dp100），如果使用AttributeSet去获得最终的像素值，那么需要第一步拿到id，第二步再去解析id
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            Log.e(TAG, " AttributeSet attrName = " + attrName + " , AttributeSet attrVal = " + attrVal);
        }

        ta.recycle();
    }

}
