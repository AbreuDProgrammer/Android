package com.example.myapplicationandroid2023.weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "https://api.openweather.org/img/w/";
    private static String KEY_URL = "&appid=6982674c31ec74257effdf914b517549";

    public String getWeatherData(String location) {
        /**
         * HttpURLConnection -> classe abstrata que contém os métodos utilizados
         * para efetuar uma conexão HTTP.
         * Herda atributos e comportamentos da classe URLConnection.
         */
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(BASE_URL + location + KEY_URL)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line = br.readLine()) != null)
                buffer.append(line+"\r\n");
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch(Throwable t) {
            t.printStackTrace();
        } finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }

    public byte[] getImage(String iconId) {
        /**
         * HttpURLConnection -> classe abstrata que contém os métodos utilizados
         * para efetuar uma conexão HTTP.
         * Herda atributos e comportamentos da classe URLConnection.
         */
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(IMG_URL + iconId + ".png")).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(is.read(buffer) != -1)
                baos.write(buffer);
            is.close();
            con.disconnect();
            return baos.toByteArray();
        } catch(Throwable t) {
            t.printStackTrace();
        } finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }
}
