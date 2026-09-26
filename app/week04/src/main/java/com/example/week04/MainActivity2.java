package com.example.week04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    int progress = 0;
    int max = 100;
    int min = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView1 = findViewById(R.id.textView1);
        ProgressBar progressBar1  = findViewById(R.id.progress1);
        TextView textView2 = findViewById(R.id.textView2);
        ProgressBar progressBar2  = findViewById(R.id.progress2);

        TextView textView3 = findViewById(R.id.textView3);
        ProgressBar progressBar3  = findViewById(R.id.progress3);

        SeekBar seekBar = findViewById(R.id.seekbar);

        textView1.setText(String.format("%d %%", progress));
        progressBar1.setProgress(progress);
        textView2.setText(String.format("%d %%", progress));
        progressBar2.setProgress(progress);
        textView3.setText(String.format("%d %%", progress));
        progressBar3.setProgress(progress);


        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress += 10;
                if (progress >= max)
                    progress = max;
                textView1.setText(String.format("%d %%", progress));
                progressBar1.setProgress(progress);
                textView2.setText(String.format("%d %%", progress));
                progressBar2.setProgress(progress);
                textView3.setText(String.format("%d %%", progress));
                progressBar3.setProgress(progress);


            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress -= 10;
                if (progress <= min)
                    progress = min;
                textView1.setText(String.format("%d %%", progress));
                progressBar1.setProgress(progress);
                textView2.setText(String.format("%d %%", progress));
                progressBar2.setProgress(progress);
                textView3.setText(String.format("%d %%", progress));
                progressBar3.setProgress(progress);

            }
        });

    }
}