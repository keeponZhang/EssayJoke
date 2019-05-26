package com.angeldevil.customstyle.hongyang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.angeldevil.customstyle.R;

public class MyTextView2 extends View {

    private static final String TAG = MyTextView2.class.getSimpleName();
    //把attr封装成数组，再定义一些常量方便获取
    private static final int[] mAttr = { android.R.attr.text, R.attr.testAttr2 };
    private static final int ATTR_ANDROID_TEXT = 0;
    private static final int ATTR_TESTATTR = 1;

    public MyTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, mAttr);

        String text = ta.getString(ATTR_ANDROID_TEXT);
        int textAttr = ta.getInteger(ATTR_TESTATTR, -1);
        Log.e(TAG, "text = " + text + " , textAttr2 = " + textAttr);

        ta.recycle();
    }

//    android在其内部也会这么做，按照传统的写法，它会在R.java生成如下代码：
//    public static final class attr {
//        public static final int testAttr=0x7f0100a9;
//    }
//    public static final class styleable {
//        public static final int test_android_text = 0;
//        public static final int test_testAttr = 1;
//        public static final int[] test = {
//                0x0101014f, 0x7f0100a9
//        };
//    }

}
