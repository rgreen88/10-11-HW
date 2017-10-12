package com.example.rynel.github;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import com.example.rynel.github.model.MyResponse;
import com.example.rynel.github.model.Repo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by rynel on 10/12/2017.
 */

public class RepoRunnable extends Thread {

    private static final String TAG = "RepoRunnable";
    public static final String url = "https://api.github.com/users/rgreen88/repos";
    private OkHttpClient client;
    private Request request;
    RecyclerView rvRepoList;
    Handler handler = new Handler( Looper.getMainLooper() );
    List<Repo> repos;

    public RepoRunnable(RecyclerView rvRepoList) {
        this.rvRepoList = rvRepoList;
    }

    //retrieve url information via api
    @Override
    public void run() {
        client = new OkHttpClient();

        request = new Request.Builder()
                .url( url )
                .build();

        String response = null;

        request = new Request.Builder()
                .url( url )
                .build();

        try {
            response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //serialize the response to objects with GSON
        Gson gson = new Gson();
        MyResponse[] myResponse = gson.fromJson( response, MyResponse[].class );
        repos = new ArrayList<>();
        //Log.d(TAG, "run: " + myResponse[0].getName() );

        for (int i = 0; i < myResponse.length; i++) {
            repos.add( new Repo( myResponse[i].getName(),
                    myResponse[i].getCreated(),
                    myResponse[i].getDescription())
            );
        }

        //Use handler to avoid talking to main thread
        handler.post(new Runnable() {
            @Override
            public void run() {            //// FIXME: 10/12/2017 
                RepoListAdapter adapter = new RepoListAdapter( repos );
                rvRepoList.setAdapter( adapter );
            }
        });
    }
}