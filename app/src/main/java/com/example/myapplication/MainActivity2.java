package com.example.myapplication;

import static android.widget.Toast.LENGTH_SHORT;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setSelected(true);
        textView1.startAnimation(anim);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setTextColor(Color.RED);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    button.setTextColor(Color.RED);
                    Toast.makeText(getBaseContext(), "Button을 클릭", LENGTH_SHORT).show();

                } else {
                    button.setTextColor(Color.BLACK);
                    Snackbar.make(v, "Button을 클릭", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                flag = !flag;
            }
        });

    }

    public void textViewClicked(View view) {
        Toast.makeText(getBaseContext(), "textView를 클릭", LENGTH_SHORT).show();
    }
}