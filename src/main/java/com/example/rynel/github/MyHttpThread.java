package com.example.rynel.github;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rynel on 10/11/2017.
 */

public class MyHttpThread implements Runnable {

    private static final String TAG = "HTTP Thread";
    String BaseURL;
    HttpURLConnection urlConnection;

    public MyHttpThread(String URL){

        this.BaseURL = URL;

    }

    @Override
    public void run() {

        try {
            //convert string url to url object
            URL url = new URL(BaseURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                Log.d(TAG, "run " + scanner.nextLine());


            }

            urlConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
