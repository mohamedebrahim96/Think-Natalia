package com.example.home.think_natalia;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.home.think_natalia.Retrofit.Model.Natalia;
import com.example.home.think_natalia.Retrofit.NataliaInterface;
import com.example.home.think_natalia.Retrofit.RetroAdapter;
import com.example.home.think_natalia.Retrofit.RetrofitNatalia;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.thinknatalia.com/wp-json/wp/v2/posts?page=10";
    final String insta = "https://www.instagram.com/thinknatalia/media/";
    RecyclerView recyclerView;
    static String last_id;
    NataliaInterface anInterface;
    //VolleyNatalia vol =  new VolleyNatalia(MainActivity.this);
    Button loadmore;

    //////////////////////////////////
    Retrofit retrofit5;
    private static Context context;

    //////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vol.volley(insta);
        context = this;
         recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
         loadmore = (Button) findViewById(R.id.btn);




        retrofit5 = new Retrofit.Builder()
                .baseUrl("https://www.instagram.com/thinknatalia/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        anInterface = retrofit5.create(NataliaInterface.class);
        Call<Natalia> call = anInterface.getposts();
        call.enqueue(new Callback<Natalia>() {
            @Override
            public void onResponse(Call<Natalia> call, retrofit2.Response<Natalia> response) {
                last_id = response.body().getItems().get(19).getId().toString();
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                RetroAdapter adapter = new RetroAdapter(MainActivity.this, response.body().getItems());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Natalia> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erorr connection", Toast.LENGTH_SHORT).show();
            }
        });

        loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "max_id="+last_id,Toast.LENGTH_LONG).show();
                Call<Natalia> call = anInterface.loadmore(last_id);
                call.enqueue(new Callback<Natalia>() {
                    @Override
                    public void onResponse(Call<Natalia> call, Response<Natalia> response) {
                        last_id = response.body().getItems().get(19).getId().toString();
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                        RetroAdapter adapter = new RetroAdapter(MainActivity.this, response.body().getItems());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Natalia> call, Throwable t) {

                    }
                });
            }
        });
    }


    static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            if (isNetworkAvailable(context)) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
