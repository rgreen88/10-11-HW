package com.example.rynel.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static final String BASEURL = "https://github.com/rgreen88/";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void makingRestCalls(View view) {

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(BASEURL)
                .build();

        switch (view.getId()) {

            case R.id.btnNative:

                MyHttpThread myHttpThread = new MyHttpThread(BASEURL);
                Thread thread = new Thread(myHttpThread);
                thread.start();

                break;

            case R.id.btnOkhttpAsync:

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure" + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: Thread ");
                    }
                });

                break;

            case R.id.btnOkhttpSync:


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
                        Log.d(TAG, "Response: " + response);
                    }

                }).start();

                break;

            case R.id.btnRetrofitAsync: //3rd party library

                retrofit2.Call<MyResponse> myResponseCall = RetroFitHelper.getCall();

                myResponseCall.enqueue(new retrofit2.Callback<MyResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
                        Log.d(TAG, "onResponse: " + response.body().getName());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<MyResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure" + t.toString());

                    }
                });
        }
    }
}