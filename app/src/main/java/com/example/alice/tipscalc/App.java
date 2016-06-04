package com.example.alice.tipscalc;

import android.app.Application;

/**
 * Created by alice on 6/3/16.
 */

public class App extends Application {
    private final static String ABOUT_URL ="https://co.linkedin.com/in/aliciabeltran";

    public  String getAboutUrl() {
        return ABOUT_URL;
    }
}
