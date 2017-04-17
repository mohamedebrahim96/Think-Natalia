package com.example.home.think_natalia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Home on 4/17/2017.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

        List values;
        Context context1;

        public MyRecyclerViewAdapter(Context context2,List values2){

            values = values2;
            context1 = context2;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

            View view1 = LayoutInflater.from(context1).inflate(R.layout.recyclerview_item,parent,false);
            ViewHolder viewHolder1 = new ViewHolder(view1);

            return viewHolder1;
        }

        @Override
        public void onBindViewHolder(ViewHolder v, int position){
            //Vholder.textView.setText(values[position]);
            Glide.with(context1)
                    .load(values.get(position))
                    .into(v.imageView);
        }

        @Override
        public int getItemCount(){
            return values.size();
        }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View v){

            super(v);

            imageView = (ImageView) v.findViewById(R.id.img);

        }
    }
}
