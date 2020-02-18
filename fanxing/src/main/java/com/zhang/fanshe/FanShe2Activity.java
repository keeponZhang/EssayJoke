package com.zhang.fanshe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhang.fanshe.bean.PointImpl;
import com.zhang.fanshe.interfaces.PointArrayImpl;
import com.zhang.fanshe.interfaces.PointGenericityImpl;
import com.zhang.fanshe.interfaces.PointImpl2;
import com.zhang.fanshe.interfaces.PointWildcardImpl;
import com.zhang.fanxing.R;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class FanShe2Activity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fan_she2);
	}
//	Type只有五种类型：
//	Class:所代表的是一个确定的类，比如Integer,String,Double等
//	ParameterizedType:ParameterizedType代表完整的泛型表达式
//	TypeVariable:TypeVariable代表泛型变量的符号即T,U等
//	WildcardType:WildcardType代表通配符,<? extends Integer>,<? super String>,或者<?>等
//	GenericArrayType:GenericArrayType代表数组类型


	private static final String TAG = "FanShe2Activity";

	public void getGenericClass(View view) {

		Class<?> clazz = PointImpl.class;
		//Type类型,Type是一个接口(Java所有类型都会继承这个接口，Class实现了这个接口)
		//所以说，这个Type类型是泛型所特有的
		//type:com.zhang.fanshe.bean.Point<java.lang.Integer>
		Type type = clazz.getGenericSuperclass();
		//ParameterizedType,代表一个泛型类型
		if (type instanceof ParameterizedType) {
			//parameterizedType:com.zhang.fanshe.bean.Point<java.lang.Integer>
			ParameterizedType parameterizedType = (ParameterizedType) type;
			//返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			for (Type parameterArgType : actualTypeArguments) {
				//parameterArgClass:class java.lang.Integer
				Class parameterArgClass = (Class) parameterArgType;
				Log.e(TAG,"填充类型为：" + parameterArgClass.getName());
			}

			//返回 Type 对象，表示声明此类型的类或接口。
			//type1:class com.zhang.fanshe.bean.Point
			Type type1 = parameterizedType.getRawType();
			Class class22 = (Class) type1;
			Log.e(TAG,"PointImpl的父类类型为："+class22.getName());

		}
	}

	public void getGenericInterface(View view) {
		Class<?> clazz = PointImpl2.class;
		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			//type:com.zhang.fanshe.interfaces.PointInterface<java.lang.String, java.lang.Double>
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//返回表示此类型实际类型参数的 Type 对象的数组
				//0:class java.lang.String 1:class java.lang.Double
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {
					Class parameterArgClass = (Class) parameterArgType;
					Log.e(TAG, "此接口的填充类型为：" + parameterArgClass.getName());
				}

				//返回 Type 对象，表示声明此类型的类或接口。
				//type1:interface com.zhang.fanshe.interfaces.PointInterface
				Type type1 = parameterizedType.getRawType();
				Class class22 = (Class) type1;
				Log.e(TAG,"声明此接口的类型为："+class22.getName());
			}
		}

	}

	public void getGenericInterface2(View view) {
		Class<?> clazz = PointGenericityImpl.class;


		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			if (type instanceof ParameterizedType) {
				//因为PointGenericityImpl覆写了PointInterface的泛型T
				//parameterizedType:com.zhang.fanshe.interfaces.PointInterface<T, java.lang.Integer>
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//返回表示此类型实际类型参数的 Type 对象的数组
				//actualTypeArguments
				//0: T       1:class java.lang.Integer
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

				for (Type parameterArgType : actualTypeArguments) {

					if(parameterArgType instanceof TypeVariable){
//						typeVariable:T
						TypeVariable typeVariable = (TypeVariable) parameterArgType;
						Log.e(TAG, "此接口的填充类型为：" + typeVariable.getName());

						//返回表示此类型变量上边界(extends 就是最大的父类定了)的 Type 对象的数组。
						//typebounds
						//0:class java.lang.Number  1:interface java.io.Serializable
						Type[] typebounds = typeVariable.getBounds();
						for (Type bound:typebounds){
							Class<?> boundClass = (Class)bound;
							//如果不写，则默认输出Object，如果写了，则输出对应的
							Log.e(TAG, "bound为：" + boundClass.getName());
						}
					}
					if (parameterArgType instanceof  Class){
						Class parameterArgClass = (Class) parameterArgType;
						Log.e(TAG, "此接口的填充类型为：" + parameterArgClass.getName());
					}
				}
			}
		}

	}

	public void getGenericInterface3(View view) {
		Class<?> clazz = PointArrayImpl.class;
		Type[] interfaces = clazz.getGenericInterfaces();
		for (Type type:interfaces){
			if (type instanceof ParameterizedType) {
				//pt:com.zhang.fanshe.interfaces.PointSingleInterface<java.lang.Integer[]>
				ParameterizedType pt = (ParameterizedType) type;
				//actualArgs
				//0:  java.lang.Integer[]
				Type[] actualArgs = pt.getActualTypeArguments();
				for (Type arg:actualArgs){
					if (arg instanceof GenericArrayType){
						GenericArrayType arrayType = (GenericArrayType)arg;
						//comType:class java.lang.Integer
						Type comType = arrayType.getGenericComponentType();
						Class<?> typeClass = (Class)comType;
						Log.d(TAG,"数组类型为："+typeClass.getName());
					}
				}
			}
		}

	}

	public void getGenericInterface4(View view) {

		Class<?> clazz = PointWildcardImpl.class;
		//此时的type对应PointSingleInterface<Comparable<? extends Number>>
		Type[] types = clazz.getGenericInterfaces();
		for (Type type:types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				//得到填充PointSingleInterface的具体参数，即：Comparable<? extends Number>，仍然是一个ParameterizedType
				Type[] actualTypes = parameterizedType.getActualTypeArguments();
				for (Type actualType : actualTypes) {
					if (actualType instanceof ParameterizedType) {
						ParameterizedType ComparableType = (ParameterizedType) actualType;
						//对Comparable<? extends Number>再取填充参数，得到的type对应<? extends Number>，这个就是WildcardType了
						Type[] compareArgs = ComparableType.getActualTypeArguments();
						for (Type Arg:compareArgs){
							if(Arg instanceof WildcardType){
								//将得到的对应WildcardType的type强转为WildcardType的变量
								//wt:? extends Number
								WildcardType wt = (WildcardType) Arg;

								//利用getLowerBounds得到下界，即派生自Super的限定，如果没有派生自super则为null
								Type[] lowerBounds = wt.getLowerBounds();
								for (Type bound:lowerBounds){
									Class<?> boundClass = (Class)bound;
									Log.e(TAG, "lowerBound为 Super：" + boundClass.getName());
								}

								//通过getUpperBounds得到上界，即派生自extends的限定，如果没有，默认是Object
								Type[] upperBounds = wt.getUpperBounds();
								for (Type bound:upperBounds){
									Class<?> boundClass = (Class)bound;
									//如果不写，则默认输出Object，如果写了，则输出对应的
									Log.e(TAG, "upperBound extends为：" + boundClass.getName());
								}

							}
						}
					}
				}

			}
		}

	}



	private void parseClass(Class<?> c){
		parseTypeParameters(c.getGenericInterfaces());
	}


	private void parseTypeParameters(Type[] types){
		for(Type type:types){
			parseTypeParameter(type);
		}
	}

	private void parseTypeParameter(Type type){
		if(type instanceof Class){
			Class<?> c = (Class<?>) type;
			//如果type是Class类型，则说明type是一个确切的类了，不会再有下一层的泛型参数填充了，
			// 所以直接打印出它的名字。
			Log.e(TAG, c.getSimpleName());
		} else if(type instanceof TypeVariable){
			TypeVariable<?> tv = (TypeVariable<?>)type;
			Log.e(TAG, tv.getName());
//			如果type是一个泛型变量，即类似于T,U这些代表泛型变量的字母，我们先打印出这个字母，
			// 然后由于泛型变量还有边界，tv.getBounds()可以得到它的所有上边界。
			// 所以利用parseTypeParameters(tv.getBounds());分析它的所有上边界type
			parseTypeParameters(tv.getBounds());
		} else if(type instanceof WildcardType){
			WildcardType wt = (WildcardType)type;
			Log.e(TAG, "?");
//			如果type类型是WildcardType，即上面最难的通配符，因为只要是WildcardType，
			// 肯定是有问号的，但WildcardType是没有getName（）函数来获取问号的标识的，
			// 所以我们只有手动输出一个问号标识了Log.d(TAG, “?”);同样通配符也是有上下边界的，
			// 比如<? extends xxx>,<? super xxx>,所以利用parseTypeParameters（）分别来分析它的上边界
			// type数组和下边界的type数组
			parseTypeParameters(wt.getUpperBounds());
			parseTypeParameters(wt.getLowerBounds());
		} else if(type instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType)type;
			Type t = pt.getOwnerType();
			//如果type的类型是ParameterizedType，那type代表的表达式肯定是一个泛型，类似于我们
			// 上面的Comparable<? extends Number>，我们利用pt.getRawType()得到声明这个类的类型，
			// 比如这里的Comparable<? extends Number>，得到的将是Comparable.Class。
			// 然后利用getActualTypeArguments()得到这个泛型的具体填充参数列表。
			// 同样利用parseTypeParameter（）和parseTypeParameters（）再次分析
			if(t != null) {
				parseTypeParameter(t);
			}
			parseTypeParameter(pt.getRawType());
			parseTypeParameters(pt.getActualTypeArguments());
		} else if (type instanceof GenericArrayType){
//			最后，是GenericArrayType类型，如果type代表的表达式是类似于String[],Integer[]这种数组的话，
			// 那type就是GenericArrayType类型。GenericArrayType只有一个函数getGenericComponentType()，
			// 得到这个数组的类型。同样将返回的type值传给parseTypeParameter(t)再次分析。
			GenericArrayType arrayType = (GenericArrayType)type;
			Type t = arrayType.getGenericComponentType();
			parseTypeParameter(t);
		}
	}

	public void parseClass(View view) {
		parseClass(PointWildcardImpl.class);
	}
}
