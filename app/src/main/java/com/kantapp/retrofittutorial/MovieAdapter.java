package com.kantapp.retrofittutorial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vinayak on 11/6/2017.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> postList;

    Context mContext;
    public MovieAdapter(Context mContext, ArrayList<Movie> postList) {
        this.mContext = mContext;
        this.postList=postList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewlayout,parent,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_title.setText(postList.get(position).getFirstname());
        holder.txt_genre.setText(postList.get(position).getLastname());
        holder.txt_age.setText(postList.get(position).getage());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_genre,txt_age;
        public ViewHolder(View itemView) {
            super(itemView);
           txt_title = itemView.findViewById(R.id.txttitle);
           txt_genre = itemView.findViewById(R.id.txtgenre);
           txt_age = itemView.findViewById(R.id.txtage);
        }
    }
}
