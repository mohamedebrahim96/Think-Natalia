package com.example.home.think_natalia.Volly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.home.think_natalia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-04-25.
 */

public class VolleyNatalia {

    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog mProgressDialog;
    List<Item> imgs;
    Json json =new Json();
    Context context;
    Button button;
    static String url;
    //VolleyNatalia  natalia =  new VolleyNatalia();


    public VolleyNatalia(Context baseContext) {
        context = baseContext;

    }
    public VolleyNatalia() {
    }

    public  void volley(final String url)
    {
        this.url = url;
        recyclerView = (RecyclerView)((Activity) context).findViewById(R.id.rvNumbers);
        button = (Button) ((Activity) context).findViewById(R.id.btn);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Android Basic JSoup Tutorial");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();


        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //json= new Json();
                        imgs = new ArrayList<>();

                        json.jsonshow(response);

                        imgs = json.full_object;
                        adapter = new MyRecyclerViewAdapter(context, imgs);
                        recyclerView.setAdapter(adapter);
                        mProgressDialog.dismiss();


                        for (int i=0;i<20;i++) {
                            Log.i("Main Activity id:",imgs.get(i).getId());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error Connection", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "max_id="+imgs.get(imgs.size()-1).getId(),Toast.LENGTH_LONG).show();
                volley(url+"?max_id="+imgs.get(imgs.size()-1).getId());
            }
        });

    }



}
