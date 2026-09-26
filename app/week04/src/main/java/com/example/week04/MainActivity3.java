package com.example.week04;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    int redProgress = 0;
    int greenProgress = 0;
    int blueProgress = 0;
    int defaultColor = 0;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView1 = findViewById(R.id.textView1);  // red
        TextView textView2 = findViewById(R.id.textView2); // green
        TextView textView3 = findViewById(R.id.textView3); // blue
        textView4 = findViewById(R.id.resultView);


        textView1.setText(String.format("%d(%X)", 0, 0));
        textView2.setText(String.format("%d(%X)", 0, 0));
        textView3.setText(String.format("%d(%X)", 0, 0));

        SeekBar seekBar1 = findViewById(R.id.seekbar1);  // red
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redProgress = progress;
                textView1.setText(String.format("%d(%X)", redProgress, redProgress));
                changeColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBar2 = findViewById(R.id.seekbar2);   // green
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenProgress = progress;
                textView2.setText(String.format("%d(%X)", greenProgress, greenProgress));
                changeColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBar3 = findViewById(R.id.seekbar3);  // blue
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueProgress = progress;
                textView3.setText(String.format("%d(%X)", blueProgress, blueProgress));
                changeColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

    public void changeColor() {

        defaultColor = Color.rgb(redProgress, greenProgress, blueProgress);
        textView4.setText(( redProgress == 0 ? "00" : String.format("%2H", redProgress)) +
                (greenProgress == 0 ? "00" : String.format("%2H", greenProgress)) + (blueProgress == 0 ? "00" : String.format("%2H", blueProgress)));
        textView4.setBackgroundColor(defaultColor);
    }
}