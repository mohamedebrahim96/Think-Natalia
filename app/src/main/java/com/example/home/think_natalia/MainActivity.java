package com.example.home.think_natalia;

import android.app.ProgressDialog;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.thinknatalia.com/wp-json/wp/v2/posts?page=10";
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    Button button;
    ProgressDialog mProgressDialog;
    List<Item> imgs ;
    Json json = new Json();


    Item item= new Item();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        button = (Button) findViewById(R.id.btn);
        final String insta = "https://www.instagram.com/thinknatalia/media/";


        //int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        volley(insta);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "max_id="+imgs.get(imgs.size()-1).getId(),Toast.LENGTH_LONG).show();
                volley(insta+"?max_id="+imgs.get(imgs.size()-1).getId());
            }
        });
    }
    public void volley(String url)
    {

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Android Basic JSoup Tutorial");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        json.jsonshow(response);
                        imgs = json.full_object;
                        adapter = new MyRecyclerViewAdapter(getApplicationContext(), imgs);
                        recyclerView.setAdapter(adapter);
                        mProgressDialog.dismiss();

                        for (int i=0;i<20;i++) {
                            Log.i("Main Activity",imgs.get(i).getImage_Url());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error Cnnection", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
