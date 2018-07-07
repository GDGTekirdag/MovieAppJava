package com.example.enciyo.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enciyo.movieapp.MovieDetailScreen;
import com.example.enciyo.movieapp.R;
import com.example.enciyo.movieapp.Repo.NowPlayingRepo;
import com.example.enciyo.movieapp.Repo.Repo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<NowPlayingRepo> repoList;
    LayoutInflater inflater;
    Context context;

    public RecyclerAdapter(Context context,List<NowPlayingRepo> arrayList){
        inflater=LayoutInflater.from(context);
        this.repoList=arrayList;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=inflater.inflate(R.layout.movie_list,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);

            return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final NowPlayingRepo repo=repoList.get(position);

            holder.txt_Film_Adi.setText(repo.originalTitle);
            holder.txt_Film_imdp.setText(repo.voteAverage.toString());
            holder.txt_Film_tr.setText(repo.title);
            holder.film_poster.setClickable(true);
            holder.film_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ıntent=new Intent(context, MovieDetailScreen.class);
                    ıntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ıntent.putExtra("Dizi",repo);
                    context.startActivity(ıntent);
                }
            });


        Picasso.get()
                .load(repo.getPosterPath())
                .resize(130,175)
                .centerCrop()
                .into(holder.film_poster);
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txt_Film_Adi;
        public TextView txt_Film_tr;
        public TextView txt_Film_imdp;
        public ImageView film_poster;
        onItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Film_Adi=itemView.findViewById(R.id.txt_film_adi);
            txt_Film_imdp=itemView.findViewById(R.id.txt_film_imdp);
            txt_Film_tr=itemView.findViewById(R.id.txt_film_tr);
            film_poster=itemView.findViewById(R.id.img_poster);


        }
        void setOnClickListener(onItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(v,getAdapterPosition());
        }
    }



}
