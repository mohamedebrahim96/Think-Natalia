package com.example.home.think_natalia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Home on 4/17/2017.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

        String[] values;
        Context context1;

        public MyRecyclerViewAdapter(Context context2,String[] values2){

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
        public void onBindViewHolder(ViewHolder Vholder, int position){
            Vholder.textView.setText(values[position]);

        }

        @Override
        public int getItemCount(){
            return values.length;
        }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v){

            super(v);

            textView = (TextView) v.findViewById(R.id.info_text);

        }
    }
}
