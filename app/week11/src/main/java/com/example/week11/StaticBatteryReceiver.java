package com.example.week11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StaticBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null) {
            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                BatteryFragment.textView.setText(BatteryFragment.textView.getText()
                        + "\n [정적] 전원 연결됨");
            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                BatteryFragment.textView.setText(BatteryFragment.textView.getText()
                        + "\n [정적] 전원 해제됨");
            }
        }
    }
}