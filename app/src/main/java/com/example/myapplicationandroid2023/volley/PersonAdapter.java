package com.example.myapplicationandroid2023.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.myapplicationandroid2023.R;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    static final int LAYOUT = R.layout.item_list;
    public PersonAdapter(Context context, List<Person> people) {
        super(context, LAYOUT, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(LAYOUT, null);
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.img);
        TextView txt = (TextView) convertView.findViewById(R.id.txtName);
        Person person = getItem(position);
        txt.setText(person.name);
        img.setImageUrl(
            person.imageUrl,
            VolleySingleton.getInstance(getContext()).getImageLoader()
        );
        return convertView;
    }
}
