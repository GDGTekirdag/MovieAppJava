package com.example.enciyo.movieapp.RetrofitClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static Retrofit retrofit=null;
    private static String BASE_URL="https://api.themoviedb.org/3/";

    public static Retrofit getClient(){
        if(retrofit==null){
          retrofit=new Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .client(new OkHttpClient())
                  .build();
          return retrofit;
        }
        return retrofit;
    }
}
