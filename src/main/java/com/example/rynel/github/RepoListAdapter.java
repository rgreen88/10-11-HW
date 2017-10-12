package com.example.rynel.github;

/**
 * Created by rynel on 10/12/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

class RepoActivity extends AppCompatActivity {

    public static final String TAG = "RepoActivityTag";

    //fixed recyclerView in maven finally; creating rv layoutManager and itemAnimator object
    private RecyclerView rvRepoList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);


        //binding all ids to objects to retrieve data from call
        rvRepoList = findViewById( R.id.rvRepoList );
        layoutManager = new LinearLayoutManager( this );
        itemAnimator = new DefaultItemAnimator();
        rvRepoList.setLayoutManager( layoutManager );
        rvRepoList.setItemAnimator( itemAnimator );

        //Runnable Thread for list
        RepoRunnable runnable = new RepoRunnable( rvRepoList );
        Thread t = new Thread( runnable );
        t.start();
    }
}