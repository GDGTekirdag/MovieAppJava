package com.example.enciyo.movieapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.enciyo.movieapp.Adapter.RecyclerAdapter;
import com.example.enciyo.movieapp.Repo.MyInterface;
import com.example.enciyo.movieapp.Repo.NowPlayingRepo;
import com.example.enciyo.movieapp.Repo.Repo;
import com.example.enciyo.movieapp.RetrofitClient.Client;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity.class";
    MyInterface myInterface;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    EditText searchText;
    Button searchButton;
    MaterialSearchView searchView;
    android.support.v7.widget.Toolbar toolbar;

    String text;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        searchView=(MaterialSearchView )findViewById(R.id.search_view);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#F4F4F4"));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myInterface = Client.getClient().create(MyInterface.class);
                Call<Repo> repoCall= myInterface.getSearch(query.toString());

                repoCall.enqueue(new Callback<Repo>() {
                    @Override
                    public void onResponse(Call<Repo> call, Response<Repo> response) {
                        List<NowPlayingRepo> repoList=response.body().results;
                        adapter=new RecyclerAdapter(getBaseContext(),repoList);
                        recyclerView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<Repo> call, Throwable t) {
                        Log.d(TAG, "onFailure: repoCall");
                    }
                });
               return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                setSupportActionBar(toolbar);
            }
        });


        myInterface = Client.getClient().create(MyInterface.class);
        Call<Repo> repoCall= myInterface.getNowPlayingRepo();

        repoCall.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                List<NowPlayingRepo> repoList=response.body().results;
                adapter=new RecyclerAdapter(getBaseContext(),repoList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.d(TAG, "onFailure: repoCall");
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

}
