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
import com.example.home.think_natalia.Retrofit.Caching;
import com.example.home.think_natalia.Retrofit.Model.Natalia;
import com.example.home.think_natalia.Retrofit.NataliaInterface;
import com.example.home.think_natalia.Retrofit.RetroAdapter;
import com.example.home.think_natalia.Retrofit.RetrofitNatalia;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.thinknatalia.com/wp-json/wp/v2/posts?page=10";
    final String insta = "https://www.instagram.com/thinknatalia/media/";
    RecyclerView recyclerView;
    static String last_id;
    NataliaInterface anInterface;
    //VolleyNatalia vol =  new VolleyNatalia(MainActivity.this);
    Button loadmore;

    //////////////////////////////////
    private static final String CACHE_CONTROL = "Cache-Control";


    //////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        loadmore = (Button) findViewById(R.id.btn);



        Caching caching = new Caching(MainActivity.this);
        anInterface = RetrofitNatalia.getClient().create(NataliaInterface.class);
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
}
