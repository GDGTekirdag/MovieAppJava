package com.example.enciyo.movieapp.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyInterface {

    @GET("movie/now_playing?api_key=6433f805fdcce0b9dab579cf58caff9e&language=tr-TR&page=1")
    Call<Repo> getNowPlayingRepo();

    @GET("https://api.themoviedb.org/3/search/movie?api_key=6433f805fdcce0b9dab579cf58caff9e&language=tr-TR")
    Call<Repo> getSearch(@Query("query") String query);

    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=6433f805fdcce0b9dab579cf58caff9e&language=tr-TR")
    Call<Repo> getMovieDetails(@Path("movide_id") int id);

}
