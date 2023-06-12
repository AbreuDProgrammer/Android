package com.example.myapplicationandroid2023.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationandroid2023.Main2;
import com.example.myapplicationandroid2023.R;

import java.io.IOException;

public class MainBroadCast extends Activity implements View.OnClickListener {

    // Registro via API
    private BroadcastReceiver mybroadcast2 = new BroadcastReceiver2();

    private Button btn, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainbroadcast);

        this.btn = (Button) findViewById(R.id.btn);
        this.btn2 = (Button) findViewById(R.id.btn2);
        this.btn3 = (Button) findViewById(R.id.btn3);
        this.btn.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);

        this.startIntentFilter();
    }

    private void startIntentFilter() {
        IntentFilter fi = new IntentFilter();
        fi.addAction("BROADCAST_RECEIVER_API");
        fi.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(this.mybroadcast2, fi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.startIntentFilter();
        Toast.makeText(this, "Voltei", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.mybroadcast2);
        Log.i("MainBroadCast onStop", "Não volta mais :(");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.mybroadcast2);
        } catch(Exception e) {
            Log.i("MainBroadCast onDestroy", e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        Intent intent = new Intent(btn.getText().toString());
        sendBroadcast(intent);
    }
}
