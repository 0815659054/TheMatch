package com.example.administrator.thematch;

import android.app.Application;

import com.google.firebase.FirebaseApp;


public class ApplicationDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

}
