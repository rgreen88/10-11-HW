package com.example.rynel.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.rynel.github.model.MyResponse;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTag";
    private OkHttpClient client;
    private Request request;
    String url = "https://api.github.com/users/rgreen88";
    String loginName, avatarUrl, profileId, repoCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OkHttp

        client = new OkHttpClient();

        request = new Request.Builder()
                .url(url)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = null;

                try {
                    response = client.newCall(request)
                            .execute()
                            .body()
                            .string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try { //JSON object holding values from github API
                    JSONObject mainObject = new JSONObject( response );

                    loginName = mainObject.get("login").toString();
                    avatarUrl = mainObject.get("avatar_url").toString();
                    profileId = mainObject.get("id").toString();
                    repoCount = mainObject.get("public_repos").toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //repo call
                url = "https://api.github.com/users/rgreen88/id";
                request = new Request.Builder()
                        .url( url )
                        .build();

                try {//new response
                    response = client.newCall(request)
                            .execute()
                            .body()
                            .string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Log.d(TAG, "MyParser: " + response);
                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject mainObject = (JSONObject) array.get(0);
                    Log.d(TAG, "MyParser: " + mainObject.get( "name" ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //serialize the response to objects with GSON
                Gson gson = new Gson();
                MyResponse[] myResponse = gson.fromJson( response, MyResponse[].class );
                Log.d(TAG, "run: " + myResponse[0].getName() );



            }
        }).start();
    }
}