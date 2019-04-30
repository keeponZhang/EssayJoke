package com.zhang.fanshe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhang.fanshe.bean.Animal;
import com.zhang.fanshe.bean.AnimalImpl;
import com.zhang.fanshe.bean.Person;
import com.zhang.fanxing.R;

import java.lang.reflect.Modifier;

public class FanSheActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fan_she);
	}
//	https://blog.csdn.net/harvic880925/article/details/50072739
	public void getClazz(View view) {
		//方法一：
		Person person = new Person();
		Class a = person.getClass();
		//方法二：
		//下面3种是一样的
		Class class1 = Animal.class;
		Class<Animal> class2= Animal.class;
		Class<?> class3 = Animal.class;
		//方法三：
//		Class c = Class.forName(String ClassName);
		//方法四：（不建议使用）
//		Class d = context.getClassLoader().loadClass(String ClassName);
	}

	private static final String TAG = "FanSheActivity";
	public void getClazz2(View view) throws Exception{
//		loadClass（String className）只是将类加载出来，并没有链接与初始化的步骤。所以这就是它们的区别
		Class<?> class1 = Class.forName("com.zhang.fanshe.bean.Animal");
		Log.e(TAG,"通过Class.forName获得的类名："+class1.getName());

		class1 = getClassLoader().loadClass("com.zhang.fanshe.bean.Animal");
		Log.e(TAG,"通过ClassLoader获得的类名："+class1.getName());
//		最后，我们总结一下，Class.forName(String className)不仅会将类加载进来，而且会对其进行初始化，而ClassLoader.loadClass(String ClassName)则只是将类加载进来，
		// 而没有对类进行初始化。一般来讲，他们两个是通用的，但如果你加载类依赖初始化值的话，那ClassLoader.loadClass(String ClassName)将不再适用。

	}

	public void getClassPackage(View view) {
		Class<?> class1 = Animal.class;
		Package package1 = class1.getPackage();

		Log.d(TAG,"完整的类名："+class1.getName());
		Log.d(TAG,"仅获取类名："+class1.getSimpleName());
		Log.d(TAG,"包名："+package1.getName());


		Class<?> class2 = null;
		try {
			class2 = Class.forName("com.zhang.fanshe.bean.AnimalImpl");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Class<?> parentClass = class2.getSuperclass();
		Log.d(TAG, "父类：" + parentClass.getName());

	}

	public void getClassInfo(View view) {
		//获取Animal类的接口列表
		Class<?> class3 = Animal.class;
		//getInterfaces()只能获取类直接继承的接口列表
		Class<?>[] interfaces = class3.getInterfaces();
		for (Class interItem:interfaces){
			Log.e(TAG, "Animal继承的接口：" + interItem.getName());
		}

		//获取AnimalImpl的接口列表
		class3 = AnimalImpl.class;
		interfaces = class3.getInterfaces();
		if (interfaces.length >0) {
			for (Class interItem : interfaces) {
				Log.e(TAG, "AnimalImpl继承的接口：" + interItem.getName());
			}
		}else {
			Log.e(TAG, "AnimalImpl无继承的接口");
		}

	}




	/**
	 * 获取所传类类型的所有继承的接口列表
	 * @param clazz
	 * @return
	 */
	public Class<?>[] getAllInterface(Class<?> clazz) {

		//获取自身的所有接口
		Class<?>[] interSelf = clazz.getInterfaces();
		//递规调用getAllInterface获取超类的所有接口
		Class<?> superClazz = clazz.getSuperclass();
		Class<?>[] interParent = null;
		if (null != superClazz) {
			interParent = getAllInterface(superClazz);
		}

		//返回值
		if (interParent == null && interSelf != null) {
			return interSelf;
		} else if (interParent == null && interSelf == null) {
			return null;
		} else if (interParent != null && interSelf == null) {
			return interParent;
		} else {
			final int length = interParent.length + interSelf.length;
			Class<?>[] result = new Class[length];
			System.arraycopy(interSelf, 0, result, 0, interSelf.length);
			System.arraycopy(interParent, 0, result, interSelf.length, interParent.length);
			return result;
		}
	}

	public void getClassInfo2(View view) throws ClassNotFoundException {
		Class<?> clazz = getClassLoader().loadClass(InnerClass.class.getName());
		//通过clazz.getModifiers()得到一个整型变量，由于访问修饰符有很多，所以这些修饰符被打包成一个int，对应的二进制中，每个修饰符是一个标志位，可以被置位或清零。
		int modifiers = clazz.getModifiers();
		String retval = Modifier.toString(modifiers);
		boolean isFinal = Modifier.isFinal(modifiers);
		Log.e(TAG, "InnerClass的定义修饰符:" + retval);
		Log.e(TAG, "is Final:" + isFinal);

	}
	public static final class InnerClass{
	}
	public void getClassInfo3(View view) {
		//使用
		Class<?> clazz2 = InnerInteface.class;
		int modifiers = clazz2.getModifiers();
		String retval = Modifier.toString(modifiers);
		boolean isInteface = Modifier.isInterface(modifiers);
		Log.d(TAG, "InnerClass的定义修饰符:" + retval);
		Log.d(TAG, "isInteface:" + isInteface);
	}
	//定义一个类部接口
	public static interface  InnerInteface{
	}

}
