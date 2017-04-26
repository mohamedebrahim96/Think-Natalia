package com.example.home.think_natalia.Retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.home.think_natalia.R;
import com.example.home.think_natalia.Retrofit.Model.Item;

import java.util.List;

/**
 * Created by Home on 2017-04-26.
 */

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.MovieViewHolder> {

    List<Item> posts;
    Context context;



    public RetroAdapter( Context context,List<Item> posts) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public RetroAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        MovieViewHolder viewHolder1 = new MovieViewHolder(view1);

        return viewHolder1;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        /*holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());*/
        Log.i("Adapter id: ",posts.get(position).getId());

        Glide.with(context)
                .load(posts.get(position).getImages().getStandardResolution().getUrl())
                .into(holder.imageView);
        //holder.textView.setText(posts.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public MovieViewHolder(View v){
            super(v);

            imageView = (ImageView) v.findViewById(R.id.img);
            //textView= (TextView) v.findViewById(R.id.text);
        }
    }
}
