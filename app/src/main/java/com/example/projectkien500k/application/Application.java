package com.example.projectkien500k.application;

import com.google.gson.Gson;

public class Application extends android.app.Application {
    private static Application instance;
    private Gson gson;
    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gson=new Gson();
    }

    public Gson getGson() {
        return gson;
    }
}
