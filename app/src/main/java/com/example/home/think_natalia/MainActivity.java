package com.example.home.think_natalia;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.thinknatalia.com/wp-json/wp/v2/posts?page=10";
    String insta = "https://www.instagram.com/thinknatalia/media/";
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
    List<String> imgs = new ArrayList<String>();
    String img = "https://scontent-cai1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/17932644_1291959330881402_7509993923306061824_n.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        for(int i=0;i<20;i++)
        {
            imgs.add(i,img);
        }

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));
        adapter = new MyRecyclerViewAdapter(this, imgs);
        recyclerView.setAdapter(adapter);
    }
}
