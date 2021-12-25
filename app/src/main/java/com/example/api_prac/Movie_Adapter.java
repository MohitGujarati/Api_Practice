package com.example.api_prac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.ViewHolder> {

    private Context context;
    private List<Movie_Model> movieList;

    public Movie_Adapter(Context context,List<Movie_Model> movies ) {
        this.context = context;
        this.movieList = movies;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
         return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie_Model movie= movieList.get(position);
        holder.overview_tv.setText(movie.getOverview());
        holder.title_tv.setText(movie.getTitle());
        holder.rating.setText(movie.getRating().toString());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Detail_Act.class);
                Bundle bundle =new Bundle();
                bundle.putString("title",movie.getTitle());
                bundle.putString("overview",movie.getOverview());
                bundle.putString("poster",movie.getPoster());
                bundle.putDouble("rating",movie.getRating());

                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title_tv,rating,overview_tv;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            title_tv=itemView.findViewById(R.id.title_tv);
            overview_tv=itemView.findViewById(R.id.overview_tv);
            rating=itemView.findViewById(R.id.rating_tv);
            constraintLayout =itemView.findViewById(R.id.main_layout);

        }
    }
}
