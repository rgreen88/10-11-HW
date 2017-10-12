package com.example.rynel.github;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by rynel on 10/11/2017.
 */

public class RetroFitHelper {

    private static final String BASEURL = "https://github.com/rgreen88/";


    public static Retrofit create()

    {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<MyResponse> getCall(){

        Retrofit retrofit = create();
        RetroFitService service = retrofit.create(RetroFitService.class);
        return service.getResponse();
    }


    public interface RetroFitService{

        @GET("rgreen88")
        Call<MyResponse> getResponse();


    }
}
