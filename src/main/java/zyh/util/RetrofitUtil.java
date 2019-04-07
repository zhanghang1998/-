package zyh.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private String USERWIKI = "http://172.17.8.100/";
    private Retrofit retrofit;
    private static RetrofitUtil netWorkHttp;

    private RetrofitUtil() {
        init();
    }

    public static RetrofitUtil instance(){

        if (netWorkHttp==null) {
            synchronized (RetrofitUtil.class){
                if (netWorkHttp==null) {
                    netWorkHttp = new RetrofitUtil();
                }
            }
        }

        return netWorkHttp;
    }

    public void init(){

        //打印log日志
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(USERWIKI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
