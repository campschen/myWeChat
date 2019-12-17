package com.camps.frame.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Looper;

import com.camps.frame.utils.log.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Locale;

public class Commons
{

	private static final Logger LOG = Logger.getLogger(Commons.class);


	/**
	 * 该方法的作用:获取系统语言
	 *
	 * @date 2014年6月10日
	 * @param context
	 * @return
	 */
	public static String getSystemLanguage(Context context)
	{
		Resources resource = context.getResources();
		Configuration configuration = resource.getConfiguration();
		Locale locale = configuration.locale;
		return locale.getLanguage();
	}



	public static Class getClassOfGenericType(final Class clazz, final int index)
	{
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType))
		{
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0)
		{
			return Object.class;
		}

		String className = params[index].toString();

		try
		{
			if(className.indexOf("class ") > -1)
			{
				//若为类，去除“class ”(注意class后面有一个空格)
				className = className.replace("class ", "").trim();
				return Class.forName(className);
			}
			else if (className.indexOf("[]") > -1)
			{
				//若为数组类型，去除“[]”符号
				className = className.replace("[]", "");
				return Array.newInstance(Class.forName(className), 0).getClass();
			}
		}
		catch (ClassNotFoundException e)
		{
			LOG.e( "[Method:getClassOfGenericType] "  + e);
		}

		return Object.class;

	}

	public static String getClassNameOfGenericType(final Class clazz, final int index)
	{
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType))
		{
			return Object.class.getName();
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0)
		{
			return Object.class.getName();
		}

		String className = params[index].toString();
		//若为数组类型，去除“[]”符号
		if(className.indexOf("class ") > -1)
		{
			//若为类，去除“class ”(注意class后面有一个空格)
			className = className.replace("class ", "").replace("$",".").trim();
			return className;
		}
		else if(className.indexOf("<") > -1 && className.indexOf(">") > -1)
		{
			className = className.substring(0, className.indexOf("<"));
			return className;
		}
		else
		{
			return className;
		}
	}

    /**
     * 判断是否在主线程
     * @return
     */
	public static boolean isOnMainThread()
	{
		return Looper.myLooper() == Looper.getMainLooper();
	}
}
