package com.camps.homework.http;

import android.content.Context;

import com.camps.frame.http.BaseRetrofit;
import com.camps.homework.BuildConfig;
import com.camps.homework.MomentsApplication;
import com.camps.homework.ServerUrls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求配置类
 */
public class MomentsRetrofit extends BaseRetrofit {

    private static OkHttpClient HTTP_CLIENT;

    private static Retrofit RETROFIT;

    public static <T> T getTargetService(Class<T> serviceClass)
    {
        //初始化HTTP_CLIENT;
        if(null == HTTP_CLIENT)
        {
            synchronized (MomentsRetrofit.class)
            {
                if(null == HTTP_CLIENT)
                {
                    HTTP_CLIENT = createHttpClient();
                }
            }
        }

        //初始化retrofit
        if(null == RETROFIT)
        {
            synchronized (MomentsRetrofit.class)
            {
                if(null == RETROFIT)
                {
                    RETROFIT = new Retrofit.Builder().baseUrl(ServerUrls.BASE_URL)
                            .client(HTTP_CLIENT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return RETROFIT.create(serviceClass);
    }


    /**
     * 创建OKHttpClient
     * @return
     */
    protected static OkHttpClient createHttpClient()
    {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//日志级别
        if (BuildConfig.DEBUG) {
              builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new Interceptor()
        {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException
            {
                Request request = appendParams(chain.request(), getDefaultParams());
                request.newBuilder().addHeader("Cookie", "add cookies here")
                        .addHeader("Content-Type","application/json; charset=UTF-8");
                return chain.proceed(request);
            }
        });

        return  builder.retryOnConnectionFailure(true).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS).connectTimeout(20, TimeUnit.SECONDS).build();
    }



    /**
     * 获取默认参数
     * @return
     */
    protected static HashMap<String, String> getDefaultParams()
    {
        HashMap<String, String> originalParams = new HashMap<>();
        Context context = MomentsApplication.getContext();
//        if(PackageManager.PERMISSION_GRANTED == context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE"))
//        {
//            originalParams.put("deviceId", Commons.getLocalDeviceID(context));
//            originalParams.put("version", PackageUtils.getPackageInfoByName(
//                    context, context.getPackageName()).versionName);
//        }

//        originalParams.put("channel", PackageUtils.getMetaData(context, "channel"));
//        originalParams.put("client_id", "c6a52bee1a55dc49fba06e3a928aaafa");
//        originalParams.put("client_secret", "lhou23dbk1muszpelhvuoxs7t6wdskh1tewpo");
//        originalParams.put("phoneSystem", Build.VERSION.RELEASE);
//        originalParams.put("platform", "android");
//        originalParams.put("access_token", MikaAuthorization.getAccessToken(context));
//        originalParams.put("versionCode", AppSettings.APP_VISION_CODE + "");
//        originalParams.put("curAppVersionCode", AppSettings.APP_VISION_CODE + "");

        return originalParams;
    }










}
