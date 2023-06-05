package com.example.myapplicationandroid2023.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.example.myapplicationandroid2023.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainWeather2 extends Activity implements View.OnClickListener {

    EditText editCity;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView imageWeather;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weather);
        this.editCity = (EditText) findViewById(R.id.editTextWeatherCity);
        this.tv1 = (TextView) findViewById(R.id.textView1);
        this.tv2 = (TextView) findViewById(R.id.textView2);
        this.tv3 = (TextView) findViewById(R.id.textView3);
        this.tv4 = (TextView) findViewById(R.id.textView4);
        this.tv5 = (TextView) findViewById(R.id.textView5);
        this.imageWeather = (ImageView) findViewById(R.id.imageWeather);
        this.submitButton = (Button) findViewById(R.id.buttonSubmit);
        this.submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == submitButton)
            this.checkWeather(view);
    }

    private void checkWeather(View view) {
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute();
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... strings) {
            Weather weather = new Weather();
            JsonObject jsonObject = JsonObject.readFrom(loadJSONFromAsset());
            JsonArray weatherArray = jsonObject.get("weather").asArray();
            weather.setId(weatherArray.get(0).asObject().get("id").asInt());
            weather.setIcon(weatherArray.get(0).asObject().get("icon").asString());
            //weather.iconData =
            weather.setTemp(jsonObject.get("main").asObject().get("temp").asFloat());
            weather.setHumidity(jsonObject.get("main").asObject().get("humidity").asFloat());
            weather.setTemp_min(jsonObject.get("main").asObject().get("temp_min").asFloat());
            weather.setTemp_max(jsonObject.get("main").asObject().get("temp_max").asFloat());
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            if(weather.getIcon() != null &&weather.getIcon().length() > 0) {

            }
            tv1.setText("Description: "+weather.getDescription());
            tv2.setText("Temperature: "+toCelsious(weather.getTemp()));
            tv3.setText("Humidity: "+weather.getHumidity());
            tv4.setText("Temp Min: "+toCelsious(weather.getTemp_min()));
            tv5.setText("Temp Max: "+toCelsious(weather.getTemp_max()));
        }

        private String toCelsious(Float temp) {
            return (temp - 273.15) + "º";
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("weather.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
