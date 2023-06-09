package com.example.myapplicationandroid2023.volley;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActVolley extends ListActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static final String TAG = "JsonPerson";
    List<Person> people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://www.dropbox.com/s/mpd5v96we9por6p/pessoas_.json?dl=1";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            this,
            this
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        if(response.length() == 0) return;
        this.people = new ArrayList<Person>();
        try {
            JSONObject jsonPeople = response.getJSONObject("pessoas");
            JSONArray jsonPerson = jsonPeople.getJSONArray("pessoa");
            for (int i = 0; i < jsonPerson.length(); i++) {
                JSONObject jsonPersonItem = jsonPerson.getJSONObject(i);
                String name = jsonPersonItem.getString("nome");
                String thumbnail = jsonPersonItem.getString("url_foto");
                Person person = new Person(name, thumbnail);
                people.add(person);
            }
        } catch(Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage().toString());
        }
        setListAdapter(new PersonAdapter(this, people));
        registerForContextMenu(getListView());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, people.get(position).name, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "error: "+error, Toast.LENGTH_LONG).show();
    }
}
