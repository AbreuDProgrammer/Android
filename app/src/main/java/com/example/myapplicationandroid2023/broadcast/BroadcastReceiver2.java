package com.example.myapplicationandroid2023.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Leo Script", "BroadcastReceiver2");
        Toast.makeText(context, "BroadcastReceiver2", Toast.LENGTH_LONG).show();
    }
}
