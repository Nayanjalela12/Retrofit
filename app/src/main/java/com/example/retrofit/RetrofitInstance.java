package com.example.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public  static String BASE_url = "https://jsonplaceholder.typicode.com/";
    public  static Retrofit retrofit;
    public  static Retrofit getRetroClient(){

        if (retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
