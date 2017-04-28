package com.example.home.think_natalia.Retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;

import com.example.home.think_natalia.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import timber.log.Timber;

/**
 * Created by Home on 2017-04-28.
 */

public class Caching {


    private static final String CACHE_CONTROL = "Cache-Control";
    static Context context;


    public Caching(Context context)
    {
        this.context = context;
    }

    public static OkHttpClient provideOkHttpClient ()
    {

        return new OkHttpClient.Builder()
                .addInterceptor( provideOfflineCacheInterceptor() )
                .addNetworkInterceptor( provideCacheInterceptor() )
                .cache( provideCache() )
                .build();
    }



    private static Cache provideCache ()
    {
        Cache cache = null;
        try
        {
            cache = new Cache( new File(context.getCacheDir(), "http-cache" ),10 * 1024 * 1024 ); // 10 MB
        }
        catch (Exception e)
        {
            Timber.e( e, "Could not create Cache!" );
        }
        return cache;
    }

    public static Interceptor provideCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public okhttp3.Response intercept (Chain chain) throws IOException
            {
                okhttp3.Response response = chain.proceed( chain.request() );

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge( 2, TimeUnit.MINUTES )
                        .build();

                return response.newBuilder()
                        .header( CACHE_CONTROL, cacheControl.toString() )
                        .removeHeader("Pragma")
                        .build();

                /*return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control",
                                String.format("max-age=%d", 60))
                        .build();*/
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public okhttp3.Response intercept (Chain chain) throws IOException
            {
                Request request = chain.request();

                if ( !Caching.isNetworkAvailable(context) )
                {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale( 10, TimeUnit.MINUTES )
                            .build();

                    request = request.newBuilder()
                            .cacheControl( cacheControl )
                            .build();
                }

                return chain.proceed( request );
            }
        };
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
