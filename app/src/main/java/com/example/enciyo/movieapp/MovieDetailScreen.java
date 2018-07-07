package com.example.enciyo.movieapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.enciyo.movieapp.Repo.NowPlayingRepo;
import com.example.enciyo.movieapp.Repo.Repo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailScreen extends AppCompatActivity {
    TextView orginalAd,turkceAd,detay,imdp;
    ImageView poster;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_screen);

        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Screen Details");
        toolbar.setTitleTextColor(Color.parseColor("#f4f4f4"));

        Intent ıntent=getIntent();
        NowPlayingRepo repo=(NowPlayingRepo) ıntent.getSerializableExtra("Dizi");

        orginalAd=(TextView)findViewById(R.id.txt_film_adi_details);
        turkceAd=(TextView)findViewById(R.id.txt_film_tr_details);
        detay=(TextView)findViewById(R.id.txt_film_detail);
        poster=(ImageView)findViewById(R.id.img_poster_details);
        imdp=(TextView)findViewById(R.id.txt_film_imdp_details);

        orginalAd.setText(repo.originalTitle);
        turkceAd.setText(repo.title);
        detay.setText(repo.overview);
        imdp.setText(""+repo.voteAverage);

        int width=poster.getWidth();
        Picasso.get()
                .load(repo.getBackdropPath())
                .resize(width,200)
                .centerCrop()
                .into(poster);


    }
}
