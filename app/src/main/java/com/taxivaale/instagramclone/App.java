package com.taxivaale.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KvTfjTZ2egtNuStilCT93kHXntOHUhpYK4HD6tRE")
                // if defined
                .clientKey("XRAJx5LqpUapJHJ49pTxi96Ss8QfhoCtN8mw7S9z")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
