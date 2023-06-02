package com.example.myapplicationandroid2023.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainWeather extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AsyncTask<String, Void, String>
        new GetMethodWeather().execute("https://api.openweathermap.org/data/2.5/weather?q=orlando&appid=6982674c31ec74257effdf914b517549");
    }

    public class GetMethodWeather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;


            return null;
        }
    }
}
