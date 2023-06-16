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
    private BroadcastReceiver mybroadcast3 = new BroadcastReceiver3();
    private BroadcastReceiver mybroadcastAlarm = new BroadcastReceiverAlarm();

    private BroadcastReceiver currentBroadcast = null;

    private Button btn, btnAlarm, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainbroadcast);

        this.btn = (Button) findViewById(R.id.btn);
        this.btnAlarm = (Button) findViewById(R.id.btnAlarm);
        this.btn2 = (Button) findViewById(R.id.btn2);
        this.btn3 = (Button) findViewById(R.id.btn3);

        this.btn.setOnClickListener(this);
        this.btnAlarm.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
    }

    private void startIntentFilter(String action, BroadcastReceiver mybroadcast) {
        IntentFilter fi = new IntentFilter();
        fi.addAction(action);
        fi.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcast, fi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.startIntentFilter("BROADCAST_RECEIVER_START_ACTIVITY", this.currentBroadcast);
        Toast.makeText(this, "Voltei", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(this.currentBroadcast);
        Log.i("Leo", "Não volta mais :(");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.currentBroadcast);
        } catch(Exception e) {
            Log.i("MainBroadCast onDestroy", e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        if(v == this.btn) {
            this.startIntentFilter("BROADCAST_RECEIVER_API", this.mybroadcast2);
            this.currentBroadcast = this.mybroadcast2;
        }
        else if(v == this.btnAlarm){
            this.startIntentFilter("ALARM_EXECUTE", this.mybroadcastAlarm);
            Intent it = new Intent(MainBroadCast.this, AlarmActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
            return;
        }
        else if(v == this.btn2) {
            this.startIntentFilter("BROADCAST_RECEIVER_START_ACTIVITY", this.mybroadcast3);
            this.currentBroadcast = this.mybroadcast3;
        }
        else if(v == this.btn3) {
            this.startIntentFilter("BROADCAST_RECEIVER_START_ACTIVITY", this.mybroadcast3);
            this.currentBroadcast = this.mybroadcast3;
        }

        Button btn = (Button) v;
        Intent intent = new Intent(btn.getText().toString());
        sendBroadcast(intent);
    }
}
