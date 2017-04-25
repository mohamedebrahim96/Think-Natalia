package com.example.home.think_natalia;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.think_natalia.Volly.Item;
import com.example.home.think_natalia.Volly.Json;
import com.example.home.think_natalia.Volly.VolleyNatalia;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.thinknatalia.com/wp-json/wp/v2/posts?page=10";
    final String insta = "https://www.instagram.com/thinknatalia/media/";


    VolleyNatalia vol ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vol =  new VolleyNatalia(MainActivity.this);
        vol.volley(insta);

    }
}
