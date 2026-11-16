package com.example.week11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class CustomReceiver extends BroadcastReceiver {
    private TextView textView;


    public CustomReceiver() {
    }

    public CustomReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String message = intent.getStringExtra("message");
        if (action != null) {
            switch (action) {
                case CustomConst.CUSTOM1:
                    textView.setText("수신됨(일반 1) : " + message);
                    break;
                case CustomConst.CUSTOM2:
                    textView.setText("수신됨(일반 2) : " + message);
                    break;
            }
        }
    }
}
