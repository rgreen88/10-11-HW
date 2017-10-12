package com.example.rynel.github;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by rynel on 10/12/2017.
 */

//// FIXME: 10/12/2017 
public class ProfileRunnable extends Thread {

    private static final String TAG = "ProfileRunnable";
    private Handler handler = new Handler( Looper.getMainLooper() );
    private ImageView imageView;
    private TextView nameTextView, URLTextView, repoCountTextView;
    private Bitmap bitmap;
    private String loginName;
    private String avatarUrl;
    private String profileId;
    private String repoCount;

    public ProfileRunnable(ImageView imageView, TextView nameTextView, TextView URLTextView, TextView repoCountTextView) {
        this.imageView = imageView;
        this.nameTextView = nameTextView;
        this.URLTextView = URLTextView;
        this.repoCountTextView = repoCountTextView;
    }
    
    

    @Override
    public void run() {

        OkHttpClient client = new OkHttpClient();

        String url = "https://api.github.com/users/jarrett-adkins";
        Request request = new Request.Builder()
                .url(url)
                .build();

        String response = null;

        try {
            response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject mainObject = new JSONObject( response );

            loginName = mainObject.get( "login" ).toString();
            avatarUrl = mainObject.get( "avatar_url" ).toString();
            profileId = mainObject.get( "url" ).toString();
            repoCount = mainObject.get( "public_repos" ).toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //bind views
        try {
            URL newurl = new URL( avatarUrl );
            bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap( bitmap );
                nameTextView.setText( "Profile: " + loginName );
                URLTextView.setText( "URL: " + profileId );
                repoCountTextView.setText( "Repo Count: " + repoCount );
            }
        });
    }
}