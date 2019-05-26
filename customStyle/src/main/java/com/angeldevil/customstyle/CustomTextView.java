package com.angeldevil.customstyle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CustomTextView extends TextView {
//    https://www.cnblogs.com/angeldevil/p/3479431.html
    private static final String TAG = CustomTextView.class.getSimpleName();

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,  R.attr.CustomizeStyle);
        Log.e("TAG", "CustomTextView CustomTextView R.attr.CustomizeStyle:" +R.attr.CustomizeStyle);

    }
//        　set：属性值的集合
//　　　　attrs：我们要获取的属性的资源ID的一个数组，如同ContextProvider中请求数据库时的Projection数组，就是从一堆属性中我们希望查询什么属性的值
//　　　　defStyleAttr：这个是当前Theme中的一个attribute，是指向style的一个引用，当在layout xml中和style中都没有为View指定属性时，会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle的意思，如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值
//　　　　defStyleRes：这个也是指向一个Style的资源ID，但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
//　　链接中对这个函数说明勉强过得去，这里简要概括一下。对于一个属性可以在多个地方指定它的值，如XML直接定义，style，Theme，而这些位置定义的值有一个优先级，按优先级从高到低依次是：
//    1直接在XML中定义>style定义>由defStyleAttr和defStyleRes指定的默认值>直接在Theme中指定的值
//    2. defStyleAttr（即defStyle）不为0且在当前Theme中可以找到这个attribute的定义时，defStyleRes不起作用，所以attr_four虽然在defStyleRes（DefaultCustomizeStyle）中定义了，但取到的值仍为null。
//    defStyleRes
//    1. defStyleAtrtr即defStyle为0或Theme中没有定义defStyle时defStyleRes才起作用
//    2. defStyleRes>在Theme中直接定义
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Customize, defStyle,
                R.style.DefaultCustomizeStyle);
        String one = a.getString(R.styleable.Customize_attr_one);
        String two = a.getString(R.styleable.Customize_attr_two);
        String three = a.getString(R.styleable.Customize_attr_three);
        String four = a.getString(R.styleable.Customize_attr_four);
        Log.e(TAG, "one:" + one);
        Log.e(TAG, "two:" + two);
        Log.e(TAG, "three:" + three);
        Log.e(TAG, "four:" + four);
        a.recycle();
    }

//    从代码中可以看到，R.attr.CustomizeStyle就是前面提到的defStyleAttr，R.style.DefaultCustomizeStyle就是defStyleRes。
//     在Styles.xml中我们为App定义了一个Theme：AppTheme，在这个Theme中直接为attr_one、attr_two、attr_three定义了属性值，这个就是前面关系式中说的直接在Theme中指定的值。
}

//        attr_one同时在xml、style、defStyleAttr、defStyleRes与Theme中直接定义了值，但起作用的是在xml中的定义
//        attr_two同时在style、defStyleAttr、defStyleRes与Theme中直接定义了值，但起用的是在style中的定义
//        attr_three同时在defStyleAttr、defStyleRes与Theme中直接定义了值，但起作用的是defStyleAttr
//        attr_four在defStyleRes中定义了，但结果仍是0。（defStyleAttr不为0，此时会去Theme的style搜索默认值，再去Theme的attr搜索默认值）



//        从前面的说明可以得到以下结论：
//        要为自定义View自定义属性，可以通过declare-styleable声明需要的属性
//        为了在Theme设置View的默认样式，可以同时定义一个format为reference的属性att_a，在定义Theme时为这个attribute指定一个Style，并且在View的第二个构造函数中将R.attr.attr_a 作为第三个参数调用第三个构造函数
//        可以定义一个Style作为Theme中没有定义attr_a时View属性的默认值。
//        可以在Theme中直接为属性赋值，但优先级最低
//        当defStyleAttr（即View的构造函数的第三个参数）不为0且在Theme中有为这个attr赋值时，defStyleRes（通过obtainStyledAttributes的第四个参数指定）不起作用
//        属性值定义的优先级：xml>style>Theme中的默认Sytle>默认Style（通过obtainStyledAttributes的第四个参数指定）>在Theme中直接指定属性值
