package com.example.budget_tracker.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class MiAPI {
    private static  Retrofit  instancia = null;
    private static final String URL_API = "https://apex.oracle.com/pls/apex/personal_db/apirestusuariooracle/";
    public static Retrofit getInstancia(){
        if (instancia == null ){
        instancia = new Retrofit.Builder().baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return instancia;
    }
}
